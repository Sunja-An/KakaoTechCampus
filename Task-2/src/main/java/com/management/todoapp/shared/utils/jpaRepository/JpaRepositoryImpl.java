package com.management.todoapp.shared.utils.jpaRepository;

import com.management.todoapp.shared.annotation.Id;
import com.management.todoapp.shared.annotation.JoinColumn;
import com.management.todoapp.shared.utils.StringUtils.SQLMapper;
import com.management.todoapp.shared.utils.StringUtils.StringUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

import static com.management.todoapp.shared.utils.StringUtils.SQLMapper.isWrapperClass;

public class JpaRepositoryImpl<T, U> implements JpaRepository<T, U> {
    @Value("${database-path}")
    private String dbPath="jdbc:h2:tcp://localhost/~/Database/kakaotech";

    @Value("${database-user}")
    private String dbUser="sa";

    @Value("${database-password}")
    private String dbPassword="test";

    @Value("${database-driver-class-name}")
    private String dbClassName = "org.h2.Driver";

    private Connection conn;

    private PreparedStatement stmt;

    @Setter
    private Class<T> tableObject;

    private String tableName;

    /*
    EntityManager entityManager;
    */
    public JpaRepositoryImpl(Class<T> tableObject) {
        this.tableObject = tableObject;
        init();
    }

    private void init() {
        try{
            if(dbPath == null || dbPath.isEmpty()) {
                throw new RuntimeException("[ERROR] Database path is empty");
            }
            if(dbUser == null || dbUser.isEmpty()) {
                throw new RuntimeException("[ERROR] Database user is empty");
            }
            if(dbPassword == null || dbPassword.isEmpty()) {
                throw new RuntimeException("[ERROR] Database password is empty");
            }

            Class.forName(dbClassName);

            this.conn = DriverManager.getConnection(dbPath, dbUser, dbPassword);

        }catch(ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
        createEntity();
    }

    @Override
    public Optional<T> findById(U id) throws SQLException {
        T instance;
        String query = "SELECT * FROM " + tableName + " WHERE " + tableName + "_id" +"=?";
        this.stmt = conn.prepareStatement(query);
        if(id instanceof Long){
            stmt.setLong(1, (Long) id);
        }
        try(ResultSet rs = stmt.executeQuery();){
            instance = mapResultSetToObject(rs, this.tableObject);
            if(instance != null){
                return Optional.of(instance);
            }
            throw new RuntimeException("[ERROR] No such author");
        }
    }

    @Override
    public Optional<T> findByAuthorName(String authorName) throws SQLException {
        T instance;

        String query = "SELECT * FROM " + tableName + " WHERE " + "author_name" +"=?";
        this.stmt = conn.prepareStatement(query);
        stmt.setString(1, authorName);

        try(ResultSet rs = stmt.executeQuery();){
            instance = mapResultSetToObject(rs, this.tableObject);
            if(instance != null){
                return Optional.of(instance);
            }
            throw new RuntimeException("[ERROR] No such author");
        }
    }

    @Override
    public List<T> findAll() throws SQLException {
        List<T> resultList = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;
        this.stmt = conn.prepareStatement(query);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                T instance = mapResultSetToObject(rs, this.tableObject);
                if (instance != null) {
                    resultList.add(instance);
                }
            }
        }
        return resultList;
    }

    @Override
    public void save(Object object) throws SQLException {
        List<Object> fieldValues = new ArrayList<>();
        try{
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(object);

                if (value != null && !field.getType().isPrimitive()
                        && !SQLMapper.isWrapperClass(field.getType())
                        && !field.getType().equals(String.class)
                        && !field.getType().equals(LocalDateTime.class)) {

                    Field idField = value.getClass().getDeclaredField("authorId");
                    idField.setAccessible(true);
                    value = idField.get(value); // 참조 객체의 id 값 추출
                }
                fieldValues.add(value);
            }
        }catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        String query = SQLMapper.insertSQLMapper(
                object.getClass().getDeclaredFields(),
                tableName,
                fieldValues
        );
        this.stmt = conn.prepareStatement(query);
        for(Object value : fieldValues){
            System.out.println("value = " + value);
            if(value == null){
                continue;
            }

            if(value instanceof String){
                stmt.setString(fieldValues.indexOf(value), (String) value);
            }else if(value instanceof Integer){
                stmt.setInt(fieldValues.indexOf(value), (Integer) value);
            }else if(value instanceof LocalDateTime){
                stmt.setTimestamp(fieldValues.indexOf(value), Timestamp.valueOf((LocalDateTime) value));
            }
        }
        stmt.execute();
    }

    @Override
    public T update(Object object) throws SQLException {
        String query = "UPDATE " + tableName + " SET " + "WHERE";
        return null;
    }

    @Override
    public void deleteById(U id) throws SQLException {
        String query = "DELETE FROM " + tableName + " WHERE "+ tableName + "_id=?";
        this.stmt = conn.prepareStatement(query);
        if(id instanceof Integer){
            stmt.setInt(1, (Integer) id);
        }
        stmt.executeQuery();
    }

    @Override
    public void close() {
        try{
            stmt.close();
            conn.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createEntity(){
        this.tableName = this.tableObject.getSimpleName().toLowerCase();
        Field[] fields = this.tableObject.getDeclaredFields();
        String sql = SQLMapper.defineColumns(fields, this.tableName);
        try{
            printEntityInfo(sql);
            this.stmt = conn.prepareStatement(sql);
            stmt.execute();
        }catch(SQLException e){
            throw new RuntimeException("[WARN] SQL Error: " + e.getMessage());
        }
    }

    private void printEntityInfo(String sql){
        System.out.println(sql);
    }

    private <T> T mapResultSetToObject(ResultSet rs, Class<T> clazz) throws SQLException {
        try {
            List<T> resultList = new ArrayList<>();

            while (rs.next()) {
                T instance = clazz.getDeclaredConstructor().newInstance();

                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    field.setAccessible(true);

                    if (field.isAnnotationPresent(JoinColumn.class)) {
                        JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);

                        String columnName = joinColumn.name(); // FK 컬럼 이름

                        Object fkValue = rs.getObject(columnName); // FK 값 조회
                        if (fkValue != null) {
                            Object referenceObject = field.getType().getDeclaredConstructor().newInstance();

                            Field idField = field.getType().getDeclaredField("authorId");
                            idField.setAccessible(true);
                            idField.set(referenceObject, fkValue);

                            // 참조 객체를 엔터티 필드에 설정
                            field.set(instance, referenceObject);
                        }
                    } else {
                        // 일반 필드 처리
                        String columnName = field.getName();
                        Object value = rs.getObject(StringUtils.makeSnakeCase(columnName));
                        if(value == null){
                            continue;
                        }
                        if (value instanceof java.sql.Timestamp) {
                            value = ((java.sql.Timestamp) value).toLocalDateTime();
                        }
                        field.set(instance, value);
                    }
                }
                resultList.add(instance);
            }
            return resultList.isEmpty() ? null : resultList.get(0);
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }
}
