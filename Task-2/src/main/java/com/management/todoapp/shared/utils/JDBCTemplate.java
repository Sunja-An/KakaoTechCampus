package com.management.todoapp.shared.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class JDBCTemplate {
    private final Connection conn;

    @Setter
    private PreparedStatement stmt;

    @Getter
    private ResultSet rs;

    public JDBCTemplate() {
        init();
        conn = connectH2Database();
    }

    private void init() {
        try{
            Class.forName("h2.driver.h2Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    private Connection connectH2Database() {
        try{
            return DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void executeSelectQuery(String sql) throws SQLException {
        String query = "SELECT * FROM employees WHERE department = ?";
        setStmt(conn.prepareStatement(query));
        stmt.execute();

        this.rs = stmt.executeQuery();
    }

    public void executeInsertQuery(String sql) throws SQLException {
        String query = "SELECT * FROM employees WHERE department = ?";
        setStmt(conn.prepareStatement(query));
        stmt.execute();

        this.rs = stmt.executeQuery();
    }

    public void close() throws SQLException {
        rs.close();
        stmt.close();
        conn.close();
    }
}
