/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import utils.ShipmentStatus;

/**
 *
 * @author ADMIN
 */
public class Salary extends Model{
     protected Long id;
    protected double salary;
    protected int salaryLevel;
    protected Long serviceId;
    protected Timestamp created;

    public Salary() {
    }

    public Long getId() {
        return id;
    }
    
    public double getSalary() {
        return salary;
    }

    public int getSalaryLevel() {
        return salaryLevel;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSalaryLevel(int salaryLevel) {
        this.salaryLevel = salaryLevel;
    }


    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
    
      public static Salary getFromResultSet(ResultSet rs) throws SQLException {
        Salary salary = new Salary();
        salary.setId(rs.getLong("id"));
        salary.setSalary(rs.getDouble("salary"));
        salary.setSalaryLevel(rs.getInt("salary_level"));
        salary.setServiceId(rs.getLong("service_id"));
        salary.setCreated(rs.getTimestamp("created"));
        return salary;
    }
    
    @Override
    public String toString() {
          return "Salary{" + "id=" + salary + ",}";
    }

    @Override
    public Object[] toRowTable() {
         return new Object[]{
            this.getSalary(),this.getSalaryLevel()
        };
    }
    
}
