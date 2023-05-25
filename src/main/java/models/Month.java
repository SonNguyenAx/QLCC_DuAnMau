package models;


import java.sql.ResultSet;
import java.sql.SQLException;


public class Month extends Model {
    protected Long id;
    protected int month,year;
    
    public Month() {
    }

    public Long getId() {
        return id;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

  


    public static Month getFromResultSet(ResultSet rs) throws SQLException {
        Month e = new Month();
        e.setId(rs.getLong("id"));
         e.setMonth(rs.getInt("month"));
         e.setYear(rs.getInt("year"));
        
        return e;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{
//            id, name,phone,date,gender,position,salary.getSalary()
                month,year
     
        };
    }

    @Override
    public String toString() {
     return Integer.toString(month);
    }


  

}
