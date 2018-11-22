package com.entity;

import java.sql.*;
import java.util.ArrayList;

public class Exercise {
    private int id;
    private String title;
    private String description;

    public Exercise(String title, String description, int id) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    public Exercise() {}

    public int getId() {
        return id;
    }

    public Exercise setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void saveOrUpdateToDb(Connection conn) throws SQLException {
        if(this.id==0) {
            String sql = "insert into exercise(title, description) values(?, ?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement prepStat = conn.prepareStatement(sql, generatedColumns);
            prepStat.setString(1, this.title);
            prepStat.setString(2, this.description);
            prepStat.executeUpdate();
            ResultSet rs = prepStat.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
            System.out.println("Exercise save in database!");
        }else{
            String update = "update exercise set title=?, description=? where id=?;";
            PreparedStatement preStat = conn.prepareStatement(update);
            preStat.setString(1, this.title);
            preStat.setString(2, this.description);
            preStat.setInt(3, this.id);
            preStat.executeUpdate();
            System.out.println("Exercise update to W2");

        }
    }
    public void delete(Connection conn) throws SQLException {
        if(this.id !=0) {
            String sql = "DELETE FROM exercise WHERE id=?;";
            PreparedStatement prepStat = conn.prepareStatement(sql);
            prepStat.setInt(1, this.id);
            prepStat.executeUpdate();
            this.id = 0;
            System.out.println("Exercise deleted compleet!");
        }
    }
    public Exercise loadExerciseById(Connection conn, int id) throws SQLException {
        //wczytaj jednego uzytkownika
        String sql = "SELECT * FROM exercise WHERE id=?;";
        PreparedStatement prepStat = conn.prepareStatement(sql);
        prepStat.setInt(1, id);
        ResultSet resultSet = prepStat.executeQuery();
        if(resultSet.next()) {
            Exercise loadedEx = new Exercise();
            loadedEx.id = resultSet.getInt("id");
            loadedEx.title = resultSet.getString("title");
            loadedEx.description = resultSet.getString("description");

            return loadedEx;
        }
        return null;
    }
    public static Exercise[] loadAllExercise(Connection conn) throws SQLException {
        //wczytaj wszystkich
        ArrayList<Exercise> exercises = new ArrayList<>();
        String sql = "SELECT * FROM exercise;";
        PreparedStatement prepStat = conn.prepareStatement(sql);
        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            Exercise loadedEx = new Exercise();
            loadedEx.id = resultSet.getInt("id");
            loadedEx.title = resultSet.getString("title");
            loadedEx.description = resultSet.getString("description");
            exercises.add(loadedEx);
        }
        Exercise[] eArray = new Exercise[exercises.size()];
        eArray = exercises.toArray(eArray);
        return eArray;
    }
    @Override
    public String toString() {
        return String.format("ID: %s, Title: %s, Description: %s", id, title, description);
    }
}
