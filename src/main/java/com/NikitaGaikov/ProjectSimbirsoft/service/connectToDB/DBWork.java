package com.NikitaGaikov.ProjectSimbirsoft.service.connectToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBWork {

    private Connection conn = null;

    public DBWork() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Mistake driver: "+e.getMessage());
        }

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/newDB?serverTimezone=UTC",
                    "root", "1234");
        }catch (Exception e){
            System.out.println("Mistake connection "+e.getMessage());
        }

    }

    public Connection getConn() {
        return conn;
    }
}
