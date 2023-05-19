package models;


import java.sql.ResultSet;
import java.sql.SQLException;


public class Apartment extends Model {
    protected Long id,buildingId;
    protected int floorNumber,trash,status;
    protected String apartmentCode, apartmentnumber,ownerName,ownerPhone;
    protected Building building;
    
    public Apartment() {
    }

    public Long getId() {
        return id;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getTrash() {
        return trash;
    }

    public int getStatus() {
        return status;
    }

    public String getApartmentCode() {
        return apartmentCode;
    }

    public String getApartmentnumber() {
        return apartmentnumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public Building getBuilding() {
        return building;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setTrash(int trash) {
        this.trash = trash;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setApartmentCode(String apartmentCode) {
        this.apartmentCode = apartmentCode;
    }

    public void setApartmentnumber(String apartmentnumber) {
        this.apartmentnumber = apartmentnumber;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

   
    public void setBuilding(Building building) {
        this.building = building;
        if (building != null) {
            this.buildingId = building.getId();
        }
    }

 

    @Override
    public String toString() {
        return apartmentCode;
    }


    public static Apartment getFromResultSet(ResultSet rs) throws SQLException {
        Apartment e = new Apartment();
//        e.setId(rs.getInt("id"));
//         e.setName(rs.getNString("name"));
//        e.setPhone(rs.getNString("phone"));
//        e.setDate(rs.getTimestamp("date"));
//        e.setGender(rs.getBoolean("gender"));
//          e.setPosition(rs.getNString("position"));
//         e.setSalaryId((int) rs.getLong("salary_id"));
//          e.setCreated(rs.getTimestamp("created"));
        return e;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{
//            id, name,phone,date,gender,position,salary.getSalary()
          
     
        };
    }


  

}
