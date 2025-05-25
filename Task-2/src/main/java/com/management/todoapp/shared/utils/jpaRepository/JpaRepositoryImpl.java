package com.management.todoapp.shared.utils.jpaRepository;

import com.management.todoapp.shared.annotation.Id;
import com.management.todoapp.shared.annotation.JoinColumn;
import com.management.todoapp.shared.domain.Pageable;
import com.management.todoapp.shared.utils.StringUtils.SQLMapper;
import com.management.todoapp.shared.utils.StringUtils.StringUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class JpaRepositoryImpl<T, U> implements JpaRepository<T, U> {
    // @Value 어노테이션이 생성자가 주입되고 난 이후에 실행되어, properties 에 있는 값을 불러들이지 못하는
    // 문제가 발생하여, 하드코딩으로 작성하였습니다.

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
        String query = "SELECT * FROM " + tableName + " WHERE " + StringUtils.makeSnakeCaseId(tableName) +"=?";
        this.stmt = conn.prepareStatement(query);
        if(id instanceof Integer){
            stmt.setInt(1, (Integer) id);
        }
        try(ResultSet rs = stmt.executeQuery()){
            rs.next();
            instance = mapResultSetToObject(rs, this.tableObject);
            return Optional.of(instance);
        }
    }

    @Override
    public Optional<T> findByAuthorName(String authorName) throws SQLException {
        T instance;

        String query = "SELECT * FROM " + tableName + " WHERE " + "author_name" +"=?";
        this.stmt = conn.prepareStatement(query);
        stmt.setString(1, authorName);

        try(ResultSet rs = stmt.executeQuery()){
            rs.next();
            instance = mapResultSetToObject(rs, this.tableObject);
            return Optional.of(instance);
        }
    }

    @Override
    public List<T> findAll() throws SQLException {
        List<T> resultList = new ArrayList<>();
        T instance;
        String query = SQLMapper.findAllSQLMapper(tableName);
        this.stmt = conn.prepareStatement(query);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                instance = mapResultSetToObject(rs, this.tableObject);
                resultList.add(instance);
            }
        }
        return resultList;
    }

    @Override
    public List<T> findAll(Pageable pageable) throws SQLException {
        List<T> resultList = new ArrayList<>();
        List<Object> fieldValues = new ArrayList<>();
        T instance;
        String query = SQLMapper.findAllSQLMapper(pageable, tableName);

        try{
            Field[] fields = pageable.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(pageable);
                if(field.getName().equals("page")){
                    fieldValues.add(pageable.getPage()* pageable.getSize());
                }else{
                    fieldValues.add(value);
                }
            }
        }catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        this.stmt = conn.prepareStatement(query);
        int paramterIndex = 1;
        for(Object value : fieldValues){
            if(value == null){
                continue;
            }
            if(value instanceof String){
                stmt.setString(paramterIndex++, (String) value);
            }else if(value instanceof Integer){
                stmt.setInt(paramterIndex++, (Integer) value);
            }
        }

        System.out.println("query = " + query);

        try (ResultSet rs = stmt.executeQuery()) {
            System.out.println("rs = " + rs);
            while (rs.next()) {
                instance = mapResultSetToObject(rs, this.tableObject);
                resultList.add(instance);
            }
            System.out.println("resultList = " + resultList);
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

                    // HardCoding for 과제#2
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
    public void update(Object object) throws SQLException {
        int idValue = 0;
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
                    // HardCoding for 과제#2
                    Field idField = value.getClass().getDeclaredField("authorId");
                    idField.setAccessible(true);

                    // 참조 객체의 id 값 추출
                    value = idField.get(value);
                }
                if(value == null){
                    continue;
                }
                if(field.isAnnotationPresent(Id.class)){
                    idValue = (Integer) value;
                    continue;
                }
                fieldValues.add(value);
            }
        }catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        String query = SQLMapper.updateSQLMapper(object, tableName, StringUtils.makeSnakeCaseId(tableName));
        this.stmt = conn.prepareStatement(query);
        System.out.println("query = " + query);
        for(Object value : fieldValues){
            if(value == null){
                continue;
            }
            if(value instanceof String){
                stmt.setString(fieldValues.indexOf(value) + 1, (String) value);
            }else if(value instanceof Integer){
                stmt.setInt(fieldValues.indexOf(value) + 1, (Integer) value);
            }else if(value instanceof LocalDateTime){
                stmt.setTimestamp(fieldValues.indexOf(value) + 1, Timestamp.valueOf((LocalDateTime) value));
            }
        }
        System.out.println("fieldValues = " + fieldValues.size() + 1 + " " + idValue);
        stmt.setInt((fieldValues.size() + 1), idValue);
        stmt.execute();
    }

    @Override
    public void deleteById(U id) throws SQLException {
        String query = "DELETE FROM " + tableName + " WHERE "+ StringUtils.makeSnakeCaseId(tableName) +"=?";
        this.stmt = conn.prepareStatement(query);
        if(id instanceof Integer){
            stmt.setInt(1, (Integer) id);
        }
        stmt.execute();
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
            T instance = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                System.out.println("[mapping] field = " + field);
                // private Field 에 대한 접근 가능하도록 설정
                field.setAccessible(true);

                if (field.isAnnotationPresent(JoinColumn.class)) {
                    JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);

                    assert joinColumn != null;
                    String columnName = joinColumn.name();

                    Object fkValue = rs.getObject(columnName);
                    if (fkValue != null) {
                        Object referenceObject = field.getType().getDeclaredConstructor().newInstance();

                        // Hard Coding for 과제#2
                        Field idField = field.getType().getDeclaredField("authorId");
                        idField.setAccessible(true);
                        idField.set(referenceObject, fkValue);

                        field.set(instance, referenceObject);
                    }
                } else {
                    String columnName = field.getName();
                    Object value = rs.getObject(StringUtils.makeSnakeCase(columnName));
                    if (value == null) {
                        continue;
                    }
                    if (value instanceof java.sql.Timestamp) {
                        value = ((java.sql.Timestamp) value).toLocalDateTime();
                    }
                    field.set(instance, value);
                }
            }

            return instance;
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }
}
