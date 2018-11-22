package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Solution {
    private int id;
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password, int id) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.id = id;
    }
    public User() {}

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        //this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void saveOrUpdateToDb(Connection conn) throws SQLException {
        if(this.id==0) {
            String sql = "insert into users(username, email, password) values(?, ?, ?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement prepStat = conn.prepareStatement(sql, generatedColumns);
            prepStat.setString(1, this.username);
            prepStat.setString(2, this.email);
            prepStat.setString(3, this.password);
            prepStat.executeUpdate();
            ResultSet rs = prepStat.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
            System.out.println("User save in database!");
        }else{
            String update = "update users set username=?, email=?, password=? where id=?;";
            PreparedStatement preStat = conn.prepareStatement(update);
            preStat.setString(1, this.username);
            preStat.setString(2, this.email);
            preStat.setString(3, this.password);
            preStat.setInt(4, this.id);
            preStat.executeUpdate();
            System.out.println("User update to W2");

        }
    }

    public void delete(Connection conn) throws SQLException {
        if(this.id !=0) {
            String sql = "DELETE FROM users WHERE id=?;";
            PreparedStatement prepStat = conn.prepareStatement(sql);
            prepStat.setInt(1, this.id);
            prepStat.executeUpdate();
            this.id = 0;
            System.out.println("User deleted compleet!");
        }
    }

    public User loadUserById(Connection conn, int id) throws SQLException {
        //wczytaj jednego uzytkownika
        String sql = "SELECT * FROM users WHERE id=?;";
        PreparedStatement prepStat = conn.prepareStatement(sql);
        prepStat.setInt(1, id);
        ResultSet resultSet = prepStat.executeQuery();
        if(resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");

            return loadedUser;
        }
        return null;
    }

    public static User[] loadAllUsers(Connection conn) throws SQLException {
        //wczytaj wszystkich
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users;";
        PreparedStatement prepStat = conn.prepareStatement(sql);
        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            User loadUser = new User();
            loadUser.id = resultSet.getInt("id");
            loadUser.username = resultSet.getString("username");
            loadUser.password = resultSet.getString("password");
            loadUser.email = resultSet.getString("email");
            users.add(loadUser);
        }
        User[] uArray = new User[users.size()];
        uArray = users.toArray(uArray);
        return uArray;
    }
    public static User[] loadAllByGroupId(Connection conn) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user_group;";
        PreparedStatement prepStat = conn.prepareStatement(sql);
        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            User loadUser = new User();
            loadUser.id = resultSet.getInt("id");
            loadUser.username = resultSet.getString("username");
            loadUser.password = resultSet.getString("password");
            loadUser.email = resultSet.getString("email");
            users.add(loadUser);
        }
        User[] uArray = new User[users.size()];
        uArray = users.toArray(uArray);
        return uArray;
    }
    @Override
    public String toString() {
        return String.format("id: %s, username: %s, password: %s, email: %s",id, username, password, email);
    }


    }

