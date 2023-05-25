package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Salary;


public class SalaryDao extends Dao<Salary> {

    @Override
    public ArrayList<Salary> getAll() throws SQLException {
        ArrayList<Salary> salarys = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `salary`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Salary salary = Salary.getFromResultSet(rs);
            salarys.add(salary);
        }
        return salarys;
    }

    @Override
    public Salary get(Long id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `salary` WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Salary salary = Salary.getFromResultSet(rs);
            return salary;
        }
        return null;
    }

    @Override
    public void save(Salary t) throws SQLException {
//        if (t == null) {
//            throw new SQLException("salary rỗng");
//        }
//        String query = "INSERT INTO `salary` (`phoneNumber`, `name`, `address`, `birthday`) VALUES (?, ?, ?, ?)";
//
//        PreparedStatement stmt = conn.prepareStatement(query);
//        stmt.setNString(1, t.getPhoneNumber());
//        stmt.setNString(2, t.getName());
//        stmt.setNString(3, t.getAddress());
//        stmt.setTimestamp(4, t.getBirthday());
//        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Salary t) throws SQLException {
        if (t == null) {
            throw new SQLException("Customer rỗng");
        }
        String query = "UPDATE `salary` SET `phoneNumber` = ?, `name` = ?, `address` = ?, `birthday` = ? WHERE `id` = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
//        stmt.setNString(1, t.getPhoneNumber());
//        stmt.setNString(2, t.getName());
//        stmt.setNString(3, t.getAddress());
//        stmt.setTimestamp(4, t.getBirthday());
//        stmt.setInt(5, t.getId());
//        int row = stmt.executeUpdate();

    }

    @Override
    public void delete(Salary t) throws SQLException {
//        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `salary` WHERE `id` = ?");
//        stmt.setInt(1, t.getId());
//        stmt.executeUpdate();

    }

    @Override
    public void deleteById(Long id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `salary` WHERE `id` = ?");
        stmt.setLong(1, id);
        stmt.executeUpdate();
    }

    public ArrayList<Salary> searchByKey(String key, String word) throws SQLException {
        ArrayList<Salary> salarys = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `salary` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Salary salary = Salary.getFromResultSet(rs);
            salarys.add(salary);
        }
        return salarys;
    }

}
