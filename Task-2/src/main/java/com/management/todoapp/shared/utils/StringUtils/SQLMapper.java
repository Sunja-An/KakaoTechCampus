package com.management.todoapp.shared.utils.StringUtils;

import com.management.todoapp.shared.annotation.Id;
import com.management.todoapp.shared.annotation.JoinColumn;
import com.management.todoapp.shared.domain.Pageable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class SQLMapper {
    public static String defineColumns(Field[] fields, String tableName){
        StringBuilder sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(tableName)
                .append("(");

        for (Field field: fields) {
            sql.append("\n");

            Optional<String> foreignKey = defineForeignKey(field);
            if(foreignKey.isPresent()){
                sql.append(foreignKey.get());
                continue;
            }
            Optional<String> primaryKey = definePrimaryKey(field);
            if(primaryKey.isPresent()){
                sql.append(primaryKey.get());
                continue;
            }
            sql.append(defineColumn(field));
        }

        // Final (,) delete for SQL grammar.
        sql.deleteCharAt(sql.length() - 1);
        sql.append(");");
        return sql.toString();
    }

    private static Optional<String> definePrimaryKey(Field field){
        Annotation annotation = field.getDeclaredAnnotation(Id.class);
        if(annotation != null && annotation.annotationType().equals(Id.class)){
            return Optional.of(
                    StringUtils.makeSnakeCase(field.getName())
                            + " " + defineColumnType(field)
                            + " PRIMARY KEY AUTO_INCREMENT,"
            );
        }
        return Optional.empty();
    }

    private static Optional<String> defineForeignKey(Field field){
        JoinColumn annotation = field.getDeclaredAnnotation(JoinColumn.class);
        if(annotation != null){
            return Optional.of(defineColumn(field) + "\nFOREIGN KEY ("
                    + annotation.name()
                    +") REFERENCES "
                    + field.getType().getSimpleName().toLowerCase()
                    + "(" + annotation.name() + "),"
            );
        }
        return Optional.empty();
    }

    private static String defineColumn(Field field){
        Class<?> fieldType = field.getType();
        if (!fieldType.isPrimitive()
                && !isWrapperClass(fieldType)
                && !fieldType.equals(String.class)
                && !fieldType.equals(LocalDateTime.class)
                && !fieldType.equals(LocalDate.class)
                && !fieldType.equals(LocalTime.class)) {
            String newFieldName = field.getName() + "_id";
            return newFieldName + " INT,";
        }
        return StringUtils.makeSnakeCase(field.getName()) + " " + defineColumnType(field) + ",";
    }

    private static String defineColumnType(Field field){
        String fieldType = field.getType().getCanonicalName();
        System.out.println("fieldType = " + fieldType);
        fieldType = switch (fieldType) {
            case "java.lang.String" -> "VARCHAR(255)";
            case "java.lang.Integer" -> "INT";
            case "java.lang.Long" -> "BIGINT";
            case "java.lang.Boolean" -> "BOOLEAN";
            case "java.lang.Double" -> "DOUBLE";
            case "java.lang.Float" -> "FLOAT";
            case "java.lang.Character" -> "CHAR";
            case "java.lang.Byte" -> "TINYINT";
            case "java.lang.Short" -> "SMALLINT";
            case "java.time.LocalDateTime" -> "DATETIME";
            case "java.time.LocalDate" -> "DATE";
            case "java.time.LocalTime" -> "TIME";
            default -> throw new IllegalArgumentException("Unsupported field type: " + fieldType);
        };
        return fieldType;
    }

    public static boolean isWrapperClass(Class<?> clazz) {
        return clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Double.class)
                || clazz.equals(Float.class) || clazz.equals(Boolean.class) || clazz.equals(Character.class)
                || clazz.equals(Byte.class) || clazz.equals(Short.class);
    }

    public static String insertSQLMapper(Field[] fields, String tableName, List<Object> values){
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO ")
                .append(tableName)
                .append(" (");
        for(Field field : fields){
            if(field.getAnnotation(Id.class) == null){
                if(ReferenceChecker.isReferenceObject(field.getType())){
                    String newFieldName = field.getName() + "_id";
                    sql.append(newFieldName).append(",");
                }else{
                    sql.append(StringUtils.makeSnakeCase(field.getName())).append(",");
                }
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(") VALUES (");
        for (Object value : values) {
            if(value == null){
                continue;
            }
            sql.append("?, ");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(");");
        return sql.toString();
    }

    public static String updateSQLMapper(Object dto, String tableName, String whereClause){
        StringBuilder sql = new StringBuilder()
                .append("UPDATE ")
                .append(tableName)
                .append(" SET ");
        Field[] fields = dto.getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null) {
                    if(field.isAnnotationPresent(Id.class)){
                        continue;
                    }
                    if(ReferenceChecker.isReferenceObject(field.getType())){
                        String newFieldName = field.getName() + "_id";
                        sql.append(newFieldName).append(" = ? ,");
                    }else{
                        sql.append(StringUtils.makeSnakeCase(field.getName())).append(" = ?,");
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access field value", e);
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" WHERE ")
                .append(whereClause)
                .append("=?");
        return sql.toString();
    }

    public static String findAllSQLMapper(String tableName){
        return "SELECT * FROM " + tableName + " ORDER BY updated_at DESC";
    };

    public static String findAllSQLMapper(Pageable pageable, String tableName){
        StringBuilder sql = new StringBuilder();
        boolean hasWhereClause = false;

        sql.append("SELECT * FROM ")
            .append(tableName);

        if(pageable.getAuthorId() != null){
            sql.append(" WHERE author_id=?");
            hasWhereClause = true;
        }

        if (pageable.getUpdatedAt() != null) {
            if (hasWhereClause) {
                sql.append(" AND");
            } else {
                sql.append(" WHERE");
            }
            sql.append(" DATE(updated_at)=?");
        }

        sql.append(" ORDER BY updated_at DESC");

        if(pageable.getSize() != null && pageable.getPage() != null){
            sql.append(" LIMIT ?")
                .append(" OFFSET ?");
        }

        return sql.toString();
    }
}
