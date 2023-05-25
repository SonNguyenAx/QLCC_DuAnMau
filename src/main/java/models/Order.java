package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import utils.OrderStatus;
import utils.OrderType;

public class Order extends Model {

    private Long id, apartmentId, serviceId, monthId;
    private int trash, status;
//    private Date startBill, paymentTerm, dayPayment;
    private double total;
    private String personPayment, description, createdBy,startBill, paymentTerm, dayPayment;
    private Month month;
    private Service service;
    private Apartment apartment;
 
    public Long getId() {
        return id;
    }

    public int getTrash() {
        return trash;
    }

    public int getStatus() {
        return status;
    }

    public Long getMonthId() {
        return monthId;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public String getPersonPayment() {
        return personPayment;
    }

    public String getStartBill() {
        return startBill;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public String getDayPayment() {
        return dayPayment;
    }

   

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public double getTotal() {
        return total;
    }

    public String getDescription() {
        return description;
    }

    public Month getMonth() {
        return month;
    }

    public Service getService() {
        return service;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public void setTrash(int trash) {
        this.trash = trash;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMonthId(Long monthId) {
        this.monthId = monthId;
    }

    public void setPersonPayment(String personPayment) {
        this.personPayment = personPayment;
    }

    public void setStartBill(String startBill) {
        this.startBill = startBill;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public void setDayPayment(String dayPayment) {
        this.dayPayment = dayPayment;
    }

   

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMonth(Month month) {
        this.month = month;
        this.monthId = month.getId();
    }

    public void setService(Service service) {
        this.service = service;
        this.serviceId = service.getId();
    }
   

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
        this.apartmentId = apartment.getId();
    }

    public static Order getFromResultSet(ResultSet rs) throws SQLException {
        Order o = new Order();
        o.setId(rs.getLong("id"));
        o.setStartBill(rs.getString("start_bill"));
        o.setPaymentTerm(rs.getString("payment_term"));
        o.setTotal(rs.getDouble("total"));
        o.setDescription(rs.getString("description"));
        o.setMonthId(rs.getLong("month_id"));
        o.setApartmentId(rs.getLong("apartment_id"));
        o.setServiceId(rs.getLong("service_id"));
        o.setPersonPayment(rs.getNString("person_payment"));
        o.setDayPayment(rs.getString("day_payment"));
        o.setTrash(rs.getInt("trash"));
        o.setStatus(rs.getInt("status"));
        o.setCreatedBy(rs.getNString("created_by"));
        return o;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{ this.getId(),this.getDescription(),this.getTotal(),this.getApartmentId(),
            this.getStartBill(),this.getPaymentTerm(),this.getDayPayment(),this.createdBy
            //            this.getId(), this.getEmployee().getName(), this.getTable().getName(),
        //            this.getType().getName(), this.getStatus().getName(), this.getOrderDate(), this.getPayDate(),
        //            String.format("%d/%d", this.getPaidAmount(), this.getFinalAmount())
        };
    }

 

}
