package com.management.todoapp.shared.utils.jpaRepository;

import com.management.todoapp.shared.utils.StringUtils.SQLMapper;
import com.management.todoapp.shared.utils.StringUtils.StringUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    private final Map<String, Object> entitiesInfo = new HashMap<>();

    private String tableName;

    private U dbIndex;

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
        String query = "SELECT * FROM " + tableName + " WHERE " + tableName + "_id" +"=?";
        this.stmt = conn.prepareStatement(query);
        if(id instanceof Long){
            stmt.setLong(1, (Long) id);
        }
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
        }
        rs.close();
        return Optional.empty();
    }

    @Override
    public List<T> findAll() throws SQLException {
        String query = "SELECT * FROM " + tableName;
        this.stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){

        }
        rs.close();
        return List.of();
    }

    @Override
    public T save(Object object) throws SQLException {
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO ")
                .append(tableName)
                .append(" (");
        for (Map.Entry<String, Object> entry : entitiesInfo.entrySet()) {
            sql.append(entry.getKey()).append(",");
        }
        sql.deleteCharAt(sql.length() - 1)
                .append(") VALUES(");
        this.stmt = conn.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){

        }
        rs.close();
        return null;
    }

    @Override
    public boolean deleteById(U id) throws SQLException {
        String query = "DELETE FROM " + tableName + " WHERE id=?";
        this.stmt = conn.prepareStatement(query);
        if(id instanceof Long){
            stmt.setLong(1, (Long) id);
        }
        stmt.executeQuery();
        return false;
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

        for (Field field : fields) {
            convertEntityPropertyToSql(field);
        }

        String sql = SQLMapper.defineColumns(fields, this.tableName);
        try{
            printEntityInfo(sql);
            this.stmt = conn.prepareStatement(sql);
            stmt.execute();
        }catch(SQLException e){
            throw new RuntimeException("[WARN] SQL Error: " + e.getMessage());
        }
    }

    private void convertEntityPropertyToSql(Field field){
        this.entitiesInfo.put(
                StringUtils.makeSnakeCase(field.getName()),
                field.getType()
        );
    }

    private void printEntityInfo(String sql){
        System.out.println(sql);
    }
}
