package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Resident;


public class ResidentDao extends Dao<Resident> {

    @Override
    public ArrayList<Resident> getAll() throws SQLException {
        ArrayList<Resident> residents = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `resident`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Resident resident = Resident.getFromResultSet(rs);
            residents.add(resident);
        }
        return residents;
    }

    @Override
    public Resident get(Long id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `resident` WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Resident resident = Resident.getFromResultSet(rs);
            return resident;
        }
        return null;
    }

    @Override
    public void save(Resident t) throws SQLException {
        if (t == null) {
            throw new SQLException("Customer rỗng");
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
            throw new SQLException("Customer rỗng");
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
    public void deleteById(Long id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `resident` WHERE `id` = ?");
        stmt.setLong(1, id);
        stmt.executeUpdate();
    }

    public ArrayList<Resident> searchByKey(String key, String word) throws SQLException {
        ArrayList<Resident> residents = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `resident` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Resident resident = Resident.getFromResultSet(rs);
            residents.add(resident);
        }
        return residents;
    }

}
