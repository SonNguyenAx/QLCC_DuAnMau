/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import models.Order;
import utils.OrderStatus;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OrderDao extends Dao<Order> {

    MonthDao monthDao = new MonthDao();
    ApartmentDao apartmentDao = new ApartmentDao();
    ServiceDao serviceDao = new ServiceDao();

    public static void importExcel(String file,Connection conn) {

        try {
            FileInputStream excelFile = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            
            for(int i=1; i<= sheet.getLastRowNum();i++) {
                Order orders = new Order();
                Row row = sheet.getRow(i);
                  long id = (long) row.getCell(0).getNumericCellValue();
                    String description = row.getCell(18).getStringCellValue();
                    double total = row.getCell(14).getNumericCellValue();
                    long apartmentId = (long) row.getCell(1).getNumericCellValue();
                    Date startBill = row.getCell(16).getDateCellValue();          
                Date paymentTerm = row.getCell(17).getDateCellValue();
                orders.setId(id);
                orders.setDescription(description);
                orders.setTotal(total);
                orders.setApartmentId(apartmentId);
//                orders.setStartBill(startBill);
//                orders.setPaymentTerm(paymentTerm);
            
                insertData(orders,conn);
                
                
            }

            
          
                   //                orders.setId(Long.parseLong(fmt.formatCellValue(currentRow.getCell(0))));
//                orders.setDescription(currentRow.getCell(18).getStringCellValue());
//                orders.setTotal(currentRow.getCell(14).getNumericCellValue());
//                orders.setApartmentId(Long.parseLong(fmt.formatCellValue(currentRow.getCell(1))));
//                orders.setStartBill(Timestamp.valueOf(fmt.formatCellValue(currentRow.getCell(16))));
//                orders.setPaymentTerm(Timestamp.valueOf(fmt.formatCellValue(currentRow.getCell(17))));
//                OrderDao.insertData(orders);
//                    long id = (long) currentRow.getCell(0).getNumericCellValue();
//                    String description = currentRow.getCell(18).getStringCellValue();
//                    double total = currentRow.getCell(14).getNumericCellValue();
//                    long apartmentId = (long) currentRow.getCell(1).getNumericCellValue();
//                    Date startBill = currentRow.getCell(16).getDateCellValue();
//               String startBill = formatter.formatCellValue(sheet.getRow(i).getCell(16));
//                Date paymentTerm = currentRow.getCell(17).getDateCellValue();
//                System.out.println(paymentTerm);
            

//            workbook.close();
//            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                Row row = sheet.getRow(i);
//                if (row != null) {
//                    long id = (long) row.getCell(0).getNumericCellValue();
//                    String description = row.getCell(18).getStringCellValue();
//                    double total = row.getCell(14).getNumericCellValue();
//                    long apartmentId = (long) row.getCell(1).getNumericCellValue();
//                    Date startBill = row.getCell(16).getDateCellValue();
////               String startBill = formatter.formatCellValue(sheet.getRow(i).getCell(16));
//                    Date paymentTerm = row.getCell(17).getDateCellValue();
//                }
//                Order orders = new Order();
//                orders.setId(id);
//                orders.setDescription(description);
//                orders.setTotal(total);
//                orders.setApartmentId(apartmentId);
//                orders.setStartBill(startBill);
//                orders.setPaymentTerm(paymentTerm);
//            }
            wb.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void insertData(Order t,Connection conn) {
   
        String query = "insert into monthly_service_bill (`id`, `start_bill`, `payment_term`, `total`, `description`, `month_id`, `apartment_id`, \n"
                + "`service_id`, `person_payment`, `day_payment`, `trash`, `status`, `created_by`) \n"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,1,1,?);";
        try {
            JOptionPane.showMessageDialog(null, "hello");
            PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setLong(1, t.getId());
//        stmt.setTimestamp(2, (Timestamp) t.getStartBill());
//        stmt.setTimestamp(3, (Timestamp) t.getPaymentTerm());
        stmt.setDouble(4, t.getTotal());
        stmt.setString(5, t.getDescription());
        stmt.setLong(6, t.getMonthId());
        stmt.setLong(7, t.getApartmentId());
           stmt.setLong(8, t.getServiceId());
           
        int row = stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("loi roi");
        }

    }

    @Override
    public ArrayList<Order> getAll() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `monthly_service_bill` ORDER BY `monthly_service_bill`.`start_bill` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            order.setMonth(monthDao.get(order.getMonthId()));
            order.setService(serviceDao.get(order.getServiceId()));
            order.setApartment(apartmentDao.get(order.getApartmentId()));
            orders.add(order);
        }
        return orders;
    }

    public ArrayList<Order> getAll(Long idApartment) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `monthly_service_bill`  WHERE `apartment_id`= '" + idApartment + "' ORDER BY `order`.`orderDate` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            order.setMonth(monthDao.get(order.getMonthId()));
            order.setService(serviceDao.get(order.getServiceId()));
            order.setApartment(apartmentDao.get(order.getApartmentId()));
            orders.add(order);
        }
        return orders;
    }

    @Override
    public Order get(Long id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `monthly_service_bill` WHERE `id` = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            order.setMonth(monthDao.get(order.getMonthId()));
            order.setService(serviceDao.get(order.getServiceId()));
            order.setApartment(apartmentDao.get(order.getApartmentId()));
            return order;
        }
        return null;
    }
      public Order get(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `monthly_service_bill` WHERE `id` = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            order.setMonth(monthDao.get(order.getMonthId()));
            order.setService(serviceDao.get(order.getServiceId()));
            order.setApartment(apartmentDao.get(order.getApartmentId()));
            return order;
        }
        return null;
    }
 
    
    @Override
    public void save(Order t) throws SQLException {
        
        if (t == null) {
            throw new SQLException("Order rỗng");
        }
        String query = "insert into monthly_service_bill (`start_bill`, `payment_term`, `total`, `description`, `month_id`, `apartment_id`,`service_id`, `person_payment`, `day_payment`, `trash`, `status`, `created_by`)VALUES ( ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?);";
        PreparedStatement stmt = conn.prepareStatement(query);
   
        stmt.setString(1, t.getStartBill());
        stmt.setString(2, t.getPaymentTerm());
        stmt.setDouble(3, t.getTotal());
        stmt.setNString(4, t.getDescription());
        stmt.setLong(5, t.getMonthId());
        stmt.setLong(6, t.getApartmentId());
        stmt.setLong(7, t.getServiceId());
        stmt.setNString(8, t.getPersonPayment());
        stmt.setString(9, t.getDayPayment());
        stmt.setInt(10, t.getTrash());
        stmt.setInt(11, t.getStatus());
        stmt.setNString(12, t.getCreatedBy());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Order t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order rỗng");
        }
        String query = "UPDATE `monthly_service_bill` SET `start_bill` = ?, `payment_term` = ?, `total` = ?, `description` = ?, `month_id` = ?, `apartment_id` = ?, `service_id` = ?, `person_payment` = ?, `day_payment` = ?, `trash` = ?, `status` = ?, `created_by` = ? WHERE `monthly_service_bill`.`id` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, t.getStartBill());
          stmt.setString(2, t.getPaymentTerm());
        stmt.setDouble(3, t.getTotal());
        stmt.setNString(4, t.getDescription());
        stmt.setLong(5, t.getMonthId());
        stmt.setLong(6, t.getApartmentId());
        stmt.setLong(7, t.getServiceId());
        stmt.setNString(8, t.getPaymentTerm());
        stmt.setString(9, t.getDayPayment());
        stmt.setInt(10, t.getTrash());
        stmt.setInt(11, t.getStatus());
        stmt.setNString(12, t.getCreatedBy());
        stmt.setLong(13, t.getId());
//        stmt.setInt(1, t.getIdEmployee());
//        stmt.setInt(2, t.getIdTable());
//        stmt.setNString(3, t.getType().getId());
//        stmt.setNString(4, t.getStatus().getId());
//        stmt.setTimestamp(5, t.getOrderDate());
//        stmt.setTimestamp(6, t.getPayDate());
//        stmt.setInt(7, t.getPaidAmount());
//        stmt.setInt(8, t.getTotalAmount());
//        stmt.setInt(9, t.getDiscount());
//        stmt.setInt(10, t.getId());
          
        int row = stmt.executeUpdate();
    }

    @Override
    public void delete(Order t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `order` WHERE `order`.`id` = ?");
        stmt.setLong(1, t.getId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `order` WHERE `order`.`id` = ?");
        stmt.setLong(1, id);
        stmt.executeUpdate();
    }

    public void deleteItems(Long idOder) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `order_item` WHERE `idOrder` = ?");
        stmt.setLong(1, idOder);
        stmt.executeUpdate();
    }

    public ArrayList<Order> searchByKey(String key, String word) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Order order = Order.getFromResultSet(rs);
//            order.setEmployee(employeeDao.get(order.getIdEmployee()));
//            order.setTable(tableDao.get(order.getIdTable()));
            orders.add(order);
        }
        return orders;
    }

    public void create(Order t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order rỗng");
        }
//        String query = "INSERT INTO `order` (`idEmployee`, `idTable`, `type`, `status`, `orderDate`, `payDate`, `paidAmount`, `totalAmount`, `discount`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement stmt = conn.prepareStatement(query);
//        stmt.setInt(1, t.getIdEmployee());
//        stmt.setInt(2, t.getIdTable());
//        stmt.setNString(3, t.getType().getId());
//        stmt.setNString(4, t.getStatus().getId());
//        stmt.setTimestamp(5, t.getOrderDate());
//        stmt.setTimestamp(6, t.getPayDate());
//        stmt.setInt(7, t.getPaidAmount());
//        stmt.setInt(8, t.getTotalAmount());
//        stmt.setInt(9, t.getDiscount());
//        int row = stmt.executeUpdate();
    }

    public ArrayList<Order> searchByKey(int idEmployee, String key, String word) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM `order` WHERE " + key + " LIKE ? AND idEmployee = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setNString(1, String.format("%s%s%s", "%", word, "%"));
        statement.setInt(2, idEmployee);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Order order = Order.getFromResultSet(rs);
//            order.setEmployee(employeeDao.get(order.getIdEmployee()));
//            order.setTable(tableDao.get(order.getIdTable()));
            orders.add(order);
        }
        return orders;
    }

    public Order getRandom() throws SQLException {
        String query = "SELECT * FROM `order` WHERE status = ? ORDER BY RAND() LIMIT 1";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setNString(1, OrderStatus.UNPAID.getId());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Order order = Order.getFromResultSet(rs);
//            order.setEmployee(employeeDao.get(order.getIdEmployee()));
//            order.setTable(tableDao.get(order.getIdTable()));
            return order;
        }
        return null;
    }

}
