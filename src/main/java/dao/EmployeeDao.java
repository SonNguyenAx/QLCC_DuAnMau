package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Employee;


public class EmployeeDao extends Dao<Employee> {
    SalaryDao salaryDao = new SalaryDao();
    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM service_employee;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Employee employee = Employee.getFromResultSet(rs);
            employee.setSalary(salaryDao.get((int) employee.getSalaryId()));
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public Employee get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM service_employee WHERE service_employee.id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Employee employee = Employee.getFromResultSet(rs);
            return employee;
        }
        return null;
    }

    @Override
    public void save(Employee t) throws SQLException {
        if (t == null) {
            throw new SQLException("Employee rỗng");
        }
        String query = "INSERT INTO `service_employee` (`name`, `phone`, `date`, `gender`, `position`, `salary_id`, `created`)"
                + " VALUES (?, ?, ?, ?, ?, ?, current_timestamp())";

        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setNString(1, t.getName());
        stmt.setNString(2, t.getPhone());
        stmt.setTimestamp(3, t.getDate());
        stmt.setBoolean(4, t.isGender());
         stmt.setNString(5, t.getPosition());
        stmt.setLong(6, t.getSalaryId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Employee t) throws SQLException {
        if (t == null) {
            throw new SQLException("Employee rong");
        }
        String query = "UPDATE `service_employee` SET `name` = ?, `phone` = ?, `date` = ?, `gender` = ?, `position` = ?, `salary_id` = ?, = ? WHERE `id` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
       stmt.setNString(1, t.getName());
        stmt.setNString(2, t.getPhone());
        stmt.setTimestamp(3, t.getDate());
        stmt.setBoolean(4, t.isGender());
         stmt.setNString(5, t.getPosition());
        stmt.setLong(6, t.getSalaryId());   
        stmt.setInt(7, t.getId());
        int row = stmt.executeUpdate();
        stmt.executeUpdate();
    }
    
    @Override
    public void delete(Employee t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `service_employee` WHERE `service_employee`.`id` = ?");
        stmt.setInt(1, t.getId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `service_employee` WHERE `service_employee`.`id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public Employee findByUsername(String name) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM employee WHERE service_employee.name = '" + name + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Employee employee = Employee.getFromResultSet(rs);
            return employee;
        }
        return null;
    }

    public ArrayList<Employee> searchByKey(String key, String word) throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `service_employee` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Employee employee = Employee.getFromResultSet(rs);
            employees.add(employee);
        }
        return employees;
    }

    public Employee getRandom() throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM service_employee ORDER BY RAND() LIMIT 1";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Employee employee = Employee.getFromResultSet(rs);
            return employee;
        }
        return null;
    }
 
}
