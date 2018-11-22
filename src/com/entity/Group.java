package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {
    private int id;
    private String nameGroup;

    public Group(String nameGroup, int id) {
        this.id = id;
        this.nameGroup = nameGroup;
    }
    public Group(){}

    public void saveOrUpdateToDb(Connection conn) throws SQLException {
        if(this.id==0) {
            String sql = "insert into user_group(name) values(?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement prepStat = conn.prepareStatement(sql, generatedColumns);
            prepStat.setString(1, this.nameGroup);
            prepStat.executeUpdate();
            ResultSet rs = prepStat.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
            System.out.println("Group save in database!");
        }else{
            String update = "update user_group set name=?, where id=?;";
            PreparedStatement preStat = conn.prepareStatement(update);
            preStat.setString(1, this.nameGroup);
            preStat.setInt(2, this.id);
            preStat.executeUpdate();
            System.out.println("Group update to W2");

        }
    }
    public Group loadGroupById(Connection conn, int id) throws SQLException {
        //wczytaj jednego uzytkownika
        String sql = "SELECT * FROM user_group WHERE id=?;";
        PreparedStatement prepStat = conn.prepareStatement(sql);
        prepStat.setInt(1, id);
        ResultSet resultSet = prepStat.executeQuery();
        if(resultSet.next()) {
            Group loadedGroup = new Group();
            loadedGroup.id = resultSet.getInt("id");
            loadedGroup.nameGroup = resultSet.getString("name");
            return loadedGroup;
        }
        return null;
    }
    public static Group[] loadAllGroup(Connection conn) throws SQLException {
        //wczytaj wszystkich
        ArrayList<Group> groups = new ArrayList<>();
        String sql = "SELECT * FROM user_group;";
        PreparedStatement prepStat = conn.prepareStatement(sql);
        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            Group loadGroup = new Group();
            loadGroup.id = resultSet.getInt("id");
            loadGroup.nameGroup = resultSet.getString("name");
            groups.add(loadGroup);
        }
        Group[] gArray = new Group[groups.size()];
        gArray = groups.toArray(gArray);
        return gArray;
    }

}
