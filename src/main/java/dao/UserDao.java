package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.User;


public class UserDao extends Dao<User> {

    @Override
    public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM users;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            User user = User.getFromResultSet(rs);
            users.add(user);
        }
        return users;
    }

    @Override
    public User get(Long id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM users WHERE users.id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            User users = User.getFromResultSet(rs);
            return users;
        }
        return null;
    }
     public User get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM users WHERE users.id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            User users = User.getFromResultSet(rs);
            return users;
        }
        return null;
    }

    @Override
    public void save(User t) throws SQLException {
        if (t == null) {
            throw new SQLException("User rỗng");
        }
        String query = "INSERT INTO `users` (`name`, `password`, `name` `role`, `salary`)"
                + " VALUES (?, ?, ?, ?, current_timestamp(), ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setLong(0, t.getId());
        stmt.setNString(1, t.getName());
        stmt.setNString(2, t.getPassword());
        stmt.setNString(3, t.getRole().getId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(User t) throws SQLException {
        if (t == null) {
            throw new SQLException("User rong");
        }
        String query = "UPDATE `users` SET `username` = ?, `password` = ?, `name` = ?, `role` = ? WHERE `id` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
   stmt.setNString(1, t.getName());
        stmt.setNString(2, t.getPassword());
        stmt.setNString(3, t.getRole().getId());
        stmt.setLong(4, t.getId());
        stmt.executeUpdate();
    }

    @Override
    public void delete(User t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `users` WHERE `users`.`id` = ?");
        stmt.setLong(1, t.getId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `users` WHERE `users`.`id` = ?");
        stmt.setLong(1, id);
        stmt.executeUpdate();
    }

    public User findByUsername(String name) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM users WHERE users.name = '" + name + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            User users = User.getFromResultSet(rs);
            return users;
        }
        return null;
    }

    public ArrayList<User> searchByKey(String key, String word) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `users` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            User user = User.getFromResultSet(rs);
            users.add(user);
        }
        return users;
    }

    public User getRandom() throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM users ORDER BY RAND() LIMIT 1";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            User users = User.getFromResultSet(rs);
            return users;
        }
        return null;
    }

   
}
