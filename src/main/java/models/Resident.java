package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @createAt Nov 24, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class Resident extends Model {

    protected Long id;
    protected String residentName, email, password,phone;
     protected int CCCD,trash,status;
    protected Timestamp birthday;

    public Resident() {
     
    }

    public Long getId() {
        return id;
    }

    public String getResidentName() {
        return residentName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public int getCCCD() {
        return CCCD;
    }

    public int getTrash() {
        return trash;
    }

    public int getStatus() {
        return status;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCCCD(int CCCD) {
        this.CCCD = CCCD;
    }

    public void setTrash(int trash) {
        this.trash = trash;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        return residentName + "\nSDT: " + phone + "\nEmail: " + email;
    }

    public static Resident getFromResultSet(ResultSet rs) throws SQLException {
      
        Resident c = new Resident();
//        c.setId(rs.getInt("id"));
//        c.setName(rs.getNString("name"));
//        c.setAddress(rs.getNString("address"));
//        c.setPhoneNumber(rs.getString("phoneNumber"));
//        c.setBirthday(rs.getTimestamp("birthday"));
        return c;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{
//            this.getId(), this.getName(), this.getPhoneNumber(), this.getAddress(), this.getBirthday()
        };
    }

    public boolean checkPassword(String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
