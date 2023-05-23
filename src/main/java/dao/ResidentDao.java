package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Resident;
import models.User;

public class ResidentDao extends Dao<Resident> {

    @Override
    public ArrayList<Resident> getAll() throws SQLException {
        ArrayList<Resident> customers = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `resident`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Resident customer = Resident.getFromResultSet(rs);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public Resident get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `resident` WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Resident customer = Resident.getFromResultSet(rs);
            return customer;
        }
        return null;
    }
    
    @Override
    public void save(Resident t) throws SQLException {
        if (t == null) {
            throw new SQLException(" rỗng");
        }
        String query = "INSERT INTO `resident` (`phoneNumber`, `name`, `address`, `birthday`) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
//        stmt.setNString(1, t.getPhoneNumber());
//        stmt.setNString(2, t.getName());
//        stmt.setNString(3, t.getAddress());
//        stmt.setTimestamp(4, t.getBirthday());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Resident t) throws SQLException {
        if (t == null) {
            throw new SQLException(" rỗng");
        }
        String query = "UPDATE `resident` SET `phoneNumber` = ?, `name` = ?, `address` = ?, `birthday` = ? WHERE `id` = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
//        stmt.setNString(1, t.getPhoneNumber());
//        stmt.setNString(2, t.getName());
//        stmt.setNString(3, t.getAddress());
//        stmt.setTimestamp(4, t.getBirthday());
//        stmt.setInt(5, t.getId());
        int row = stmt.executeUpdate();

    }

    @Override
    public void delete(Resident t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `resident` WHERE `id` = ?");
        stmt.setLong(1, t.getId());
        stmt.executeUpdate();

    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `resident` WHERE `id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
    
    public ArrayList<Resident> searchByKey(String key, String word) throws SQLException {
        ArrayList<Resident> customers = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `resident` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Resident customer = Resident.getFromResultSet(rs);
            customers.add(customer);
        }
        return customers;
    }
    public Resident findByUsername(String name) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM resident WHERE users.name = '" + name + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
           Resident resident = Resident.getFromResultSet(rs);
            return resident;
        }
        return null;
    }
     
}
