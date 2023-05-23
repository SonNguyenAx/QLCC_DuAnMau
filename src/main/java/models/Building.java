package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Building extends Model {

    protected long id;
    protected int numberFloor, totalAparments;
    protected String nameBuilding, address, image, phone, description;
    protected int trash, status;

    public Building() {
    }

    public long getId() {
        return id;
    }

    public int getNumberFloor() {
        return numberFloor;
    }

    public int getTotalAparments() {
        return totalAparments;
    }



    public String getNameBuilding() {
        return nameBuilding;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public int getTrash() {
        return trash;
    }

    public int getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNumberFloor(int numberFloor) {
        this.numberFloor = numberFloor;
    }

    public void setTotalAparments(int totalAparments) {
        this.totalAparments = totalAparments;
    }


    public void setNameBuilding(String nameBuilding) {
        this.nameBuilding = nameBuilding;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTrash(int trash) {
        this.trash = trash;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nameBuilding;
    }

    public static Building getFromResultSet(ResultSet rs) throws SQLException {
        Building e = new Building();
        e.setId(rs.getLong("id"));
        e.setNameBuilding(rs.getNString("name"));
        e.setAddress(rs.getNString("address"));
        e.setImage(rs.getNString("image"));
        e.setPhone(rs.getNString("phone"));
        e.setNumberFloor(rs.getInt("number_floor"));
        e.setTotalAparments(rs.getInt("total_apartment"));
        e.setDescription(rs.getString("description"));
        e.setTrash(rs.getInt("trash"));
        e.setStatus(rs.getInt("status"));
        return e;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{
            id, nameBuilding,address, image, phone,numberFloor, totalAparments,description,
            trash, status

        };
    }

}
