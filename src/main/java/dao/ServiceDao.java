package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import models.Service;


public class ServiceDao extends Dao<Service> {
  
    @Override
    public ArrayList<Service> getAll() throws SQLException {
        ArrayList<Service> services = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM service;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Service service = Service.getFromResultSet(rs);
          
            services.add(service);
        }
        return services;
    }

    @Override
    public Service get(Long id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM service WHERE service.id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Service service = Service.getFromResultSet(rs);
            return service;
        }
        return null;
    }

    @Override
    public void save(Service t) throws SQLException {
        if (t == null) {
            throw new SQLException("Service rỗng");
        }
        String query = "INSERT INTO `service` (`name`, `phone`, `date`, `gender`, `position`, `salary_id`, `created`)"
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
    public void update(Service t) throws SQLException {
        if (t == null) {
            throw new SQLException("Service rong");
        }
        String query = "UPDATE `service` SET `name` = ?, `phone` = ?, `date` = ?, `gender` = ?, `position` = ?, `salary_id` = ?, = ? WHERE `id` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
//       stmt.setNString(1, t.getName());
//        stmt.setNString(2, t.getPhone());
//        stmt.setTimestamp(3, t.getDate());
//        stmt.setBoolean(4, t.isGender());
//         stmt.setNString(5, t.getPosition());
//        stmt.setLong(6, t.getSalaryId());   
//        stmt.setInt(7, t.getId());
        int row = stmt.executeUpdate();
        stmt.executeUpdate();
    }
    
    @Override
    public void delete(Service t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `service` WHERE `service`.`id` = ?");
        stmt.setLong(1, t.getId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `service` WHERE `service`.`id` = ?");
        stmt.setLong(1, id);
        stmt.executeUpdate();
    }

    public Service findByUsername(String name) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM service WHERE service.name = '" + name + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Service service = Service.getFromResultSet(rs);
            return service;
        }
        return null;
    }

    public ArrayList<Service> searchByKey(String key, String word) throws SQLException {
        ArrayList<Service> services = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `service` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Service service = Service.getFromResultSet(rs);
            services.add(service);
        }
        return services;
    }

    public Service getRandom() throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM service ORDER BY RAND() LIMIT 1";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Service service = Service.getFromResultSet(rs);
            return service;
        }
        return null;
    }
 
}
