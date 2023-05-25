package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import models.Month;


public class MonthDao extends Dao<Month> {
  
    @Override
    public ArrayList<Month> getAll() throws SQLException {
        ArrayList<Month> months = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM month;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Month month = Month.getFromResultSet(rs);
            months.add(month);
        }
        return months;
    }

    @Override
    public Month get(Long id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM month WHERE month.id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Month month = Month.getFromResultSet(rs);
            return month;
        }
        return null;
    }
   

    @Override
    public void save(Month t) throws SQLException {
        if (t == null) {
            throw new SQLException("Month rỗng");
        }
        String query = "INSERT INTO `month` (`name`, `phone`, `date`, `gender`, `position`, `salary_id`, `created`)"
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
    public void update(Month t) throws SQLException {
        if (t == null) {
            throw new SQLException("Month rong");
        }
        String query = "UPDATE `month` SET `name` = ?, `phone` = ?, `date` = ?, `gender` = ?, `position` = ?, `salary_id` = ?, = ? WHERE `id` = ?";
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
    public void delete(Month t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `month` WHERE `month`.`id` = ?");
//        stmt.setInt(1, t.getId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `month` WHERE `month`.`id` = ?");
        stmt.setLong(1, id);
        stmt.executeUpdate();
    }

    public Month findByUsername(String name) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM month WHERE month.name = '" + name + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Month month = Month.getFromResultSet(rs);
            return month;
        }
        return null;
    }

    public ArrayList<Month> searchByKey(String key, String word) throws SQLException {
        ArrayList<Month> months = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `month` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Month month = Month.getFromResultSet(rs);
            months.add(month);
        }
        return months;
    }

    public Month getRandom() throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM month ORDER BY RAND() LIMIT 1";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Month month = Month.getFromResultSet(rs);
            return month;
        }
        return null;
    }
 
}
