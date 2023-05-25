package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Apartment;

import models.Apartment;


public class ApartmentDao extends Dao<Apartment> {
//    Đầu tiên tạo model -> dao
    SalaryDao salaryDao = new SalaryDao();
    @Override
    public ArrayList<Apartment> getAll() throws SQLException {
        ArrayList<Apartment> apartments = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM apartment;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Apartment apartment = Apartment.getFromResultSet(rs);
            apartments.add(apartment);
        }
        return apartments;
    }

    @Override
    public Apartment get(Long id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM apartment WHERE apartment.id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Apartment apartment = Apartment.getFromResultSet(rs);
            return apartment;
        }
        return null;
    }

    @Override
    public void save(Apartment t) throws SQLException {
        if (t == null) {
            throw new SQLException("Apartment rỗng");
        }
        String query = "INSERT INTO `apartment` (`name`, `phone`, `date`, `gender`, `position`, `salary_id`, `created`)"
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
    public void update(Apartment t) throws SQLException {
        if (t == null) {
            throw new SQLException("Apartment rong");
        }
        String query = "UPDATE `apartment` SET `name` = ?, `phone` = ?, `date` = ?, `gender` = ?, `position` = ?, `salary_id` = ?, = ? WHERE `id` = ?";
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
    public void delete(Apartment t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `apartment` WHERE `apartment`.`id` = ?");
        stmt.setLong(1, t.getId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `apartment` WHERE `apartment`.`id` = ?");
        stmt.setLong(1, id);
        stmt.executeUpdate();
    }

    public Apartment findByUsername(String name) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM apartment WHERE apartment.apartment_code = '" + name + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Apartment apartment = Apartment.getFromResultSet(rs);
            return apartment;
        }
        return null;
    }

    public ArrayList<Apartment> searchByKey(String key, String word) throws SQLException {
        ArrayList<Apartment> apartments = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `apartment` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Apartment apartment = Apartment.getFromResultSet(rs);
            apartments.add(apartment);
        }
        return apartments;
    }

    public Apartment getRandom() throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM apartment ORDER BY RAND() LIMIT 1";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Apartment apartment = Apartment.getFromResultSet(rs);
            return apartment;
        }
        return null;
    }

 
}
