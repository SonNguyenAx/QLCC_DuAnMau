package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public abstract class Dao<T> {

    Connection conn = Database.getInstance().getConnection();

    public abstract ArrayList<T> getAll() throws SQLException;

    public abstract T get(Long id) throws SQLException;

    public abstract void save(T t) throws SQLException;

    public abstract void update(T t) throws SQLException;

    public abstract void delete(T t) throws SQLException;

    public abstract void deleteById(Long id) throws SQLException;
}
