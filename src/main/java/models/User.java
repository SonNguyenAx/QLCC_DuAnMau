package models;


import java.sql.ResultSet;
import java.sql.SQLException;
import utils.UserRole;

public class User extends Model {

    protected Long id;
    protected String name, password;
    protected UserRole role;


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return name;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public static User getFromResultSet(ResultSet rs) throws SQLException {
        User e = new User();
        e.setId(rs.getLong("id"));
         e.setName(rs.getNString("name"));
        e.setPassword(rs.getNString("password"));
        e.setRole(UserRole.getById(rs.getNString("role")));
       
        return e;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{
            id, name, password,
            role.getName(),
     
        };
    }

   

}
