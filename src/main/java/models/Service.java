package models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class Service extends Model {
    protected Long id;
    protected String name;
    protected double price;
    protected int required,trash,status;
    protected Timestamp created;
    
    public Service() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getRequired() {
        return required;
    }

    public int getTrash() {
        return trash;
    }

    public int getStatus() {
        return status;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRequired(int required) {
        this.required = required;
    }

    public void setTrash(int trash) {
        this.trash = trash;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

   

    @Override
    public String toString() {
        return name;
    }


    public static Service getFromResultSet(ResultSet rs) throws SQLException {
        Service e = new Service();
        e.setId(rs.getLong("id"));
         e.setName(rs.getNString("name"));
        e.setPrice(rs.getDouble("price"));
        e.setRequired(rs.getInt("required"));
        e.setTrash(rs.getInt("trash"));
          e.setStatus(rs.getInt("status"));
          e.setCreated(rs.getTimestamp("created"));
        return e;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{
         id,name
        };
    }


  

}
