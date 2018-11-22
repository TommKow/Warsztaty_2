package com.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB  {

    private static DB database = new DB();
    private Connection connection;

    public DB() {}

    public static DB getInstance() {
        return database;
    }

    public Connection getConnection() {

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/W2?useSSL=false&characterEncoding=utf8&serverTimezone=UTC",
                    "root", "coderslab");
            return this.connection;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void closeConnection() {
        try{
            connection.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

}


