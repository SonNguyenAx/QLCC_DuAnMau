package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Resident;

/**
 * @createAt Nov 25, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class ResidentDao extends Dao<Resident> {

    @Override
    public ArrayList<Resident> getAll() throws SQLException {
        ArrayList<Resident> customers = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `customer`";
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
        String query = "SELECT * FROM `customer` WHERE id = " + id;
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
            throw new SQLException("Customer rỗng");
        }
        String query = "INSERT INTO `customer` (`phoneNumber`, `name`, `address`, `birthday`) VALUES (?, ?, ?, ?)";

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
            throw new SQLException("Customer rỗng");
        }
        String query = "UPDATE `customer` SET `phoneNumber` = ?, `name` = ?, `address` = ?, `birthday` = ? WHERE `id` = ?";

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
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `customer` WHERE `id` = ?");
        stmt.setLong(1, t.getId());
        stmt.executeUpdate();

    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `customer` WHERE `id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public ArrayList<Resident> searchByKey(String key, String word) throws SQLException {
        ArrayList<Resident> customers = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `customer` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Resident customer = Resident.getFromResultSet(rs);
            customers.add(customer);
        }
        return customers;
    }

}
