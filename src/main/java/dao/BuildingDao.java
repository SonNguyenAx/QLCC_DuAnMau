package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Building;

import models.Building;


public class BuildingDao extends Dao<Building> {
  
    @Override
    public ArrayList<Building> getAll() throws SQLException {
        ArrayList<Building> buildings = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM building;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Building building = Building.getFromResultSet(rs);
            buildings.add(building);
        }
        return buildings;
    }

    @Override
    public Building get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM building WHERE building.id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Building building = Building.getFromResultSet(rs);
            return building;
        }
        return null;
    }

    @Override
    public void save(Building t) throws SQLException {
        if (t == null) {
            throw new SQLException("Building rỗng");
        }
        String query = "INSERT INTO `building` (`name`, `phone`, `date`, `gender`, `position`, `salary_id`, `created`)"
                + " VALUES (?, ?, ?, ?, ?, ?, current_timestamp())";

        PreparedStatement stmt = conn.prepareStatement(query);

//        stmt.setNString(1, t.getName());
//        stmt.setNString(2, t.getPhone());
//        stmt.setTimestamp(3, t.getDate());
//        stmt.setBoolean(4, t.isGender());
//         stmt.setNString(5, t.getPosition());
//        stmt.setLong(6, t.getSalaryId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Building t) throws SQLException {
        if (t == null) {
            throw new SQLException("Building rong");
        }
        String query = "UPDATE `building` SET `name` = ?, `phone` = ?, `date` = ?, `gender` = ?, `position` = ?, `salary_id` = ?, = ? WHERE `id` = ?";
//        PreparedStatement stmt = conn.prepareStatement(query);
//       stmt.setNString(1, t.getName());
//        stmt.setNString(2, t.getPhone());
//        stmt.setTimestamp(3, t.getDate());
//        stmt.setBoolean(4, t.isGender());
//         stmt.setNString(5, t.getPosition());
//        stmt.setLong(6, t.getSalaryId());   
//        stmt.setInt(7, t.getId());
//        int row = stmt.executeUpdate();
//        stmt.executeUpdate();
    }

    @Override
    public void delete(Building t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `building` WHERE `building`.`id` = ?");
//        stmt.setInt(1, t.getId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `building` WHERE `building`.`id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public Building findByUsername(String name) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM building WHERE building.name = '" + name + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Building building = Building.getFromResultSet(rs);
            return building;
        }
        return null;
    }

    public ArrayList<Building> searchByKey(String key, String word) throws SQLException {
        ArrayList<Building> buildings = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `building` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Building building = Building.getFromResultSet(rs);
            buildings.add(building);
        }
        return buildings;
    }

    public Building getRandom() throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM building ORDER BY RAND() LIMIT 1";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Building building = Building.getFromResultSet(rs);
            return building;
        }
        return null;
    }
 
}
