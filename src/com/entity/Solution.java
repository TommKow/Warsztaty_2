package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Solution {
    private int id;
    private Date created;
    private Date updated;
    private String description;
    private int exercise_id;
    private int users_id;

    public Solution(int id, Date created, Date updated, String description, int exercise_id, int users_id) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }
    public Solution(int exercise_id, int users_id) {
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }
    public Solution(){}

    public void saveOrUpdateToDb(Connection conn) throws SQLException {
        if(this.id==0) {
            String sql = "insert into solution(username, email, password) values(?, ?, ?)";
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
            String sql = "DELETE FROM solution WHERE id=?;";
            PreparedStatement prepStat = conn.prepareStatement(sql);
            prepStat.setInt(1, this.id);
            prepStat.executeUpdate();
            this.id = 0;
            System.out.println("Solution deleted compleet!");
        }
    }

    public Solution loadSolutionById(Connection conn, int id) throws SQLException {
        //wczytaj jednego uzytkownika
        String sql = "SELECT * FROM solution WHERE id=?;";
        PreparedStatement prepStat = conn.prepareStatement(sql);
        prepStat.setInt(1, id);
        ResultSet resultSet = prepStat.executeQuery();
        if(resultSet.next()) {
            Solution loadSol = new Solution();
            loadSol.id = resultSet.getInt("id");
            loadSol.created = resultSet.getDate("created");
            loadSol.updated = resultSet.getDate("updated");
            loadSol.description = resultSet.getString("desciption");

            return loadSol;
        }
        return null;
    }

    public static Solution[] loadAllSolution(Connection conn) throws SQLException {
        //wczytaj wszystkich
        ArrayList<Solution> solutions = new ArrayList<>();
        String sql = "SELECT * FROM solution;";
        PreparedStatement prepStat = conn.prepareStatement(sql);
        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            Solution loadSol = new Solution();
            loadSol.id = resultSet.getInt("id");
            loadSol.created = resultSet.getDate("created");
            loadSol.updated = resultSet.getDate("updated");
            loadSol.description = resultSet.getString("desciption");
            solutions.add(loadSol);
        }
        Solution[] uArray = new Solution[solutions.size()];
        uArray = solutions.toArray(uArray);
        return uArray;
    }

}
