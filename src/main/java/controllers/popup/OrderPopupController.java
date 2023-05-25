package controllers.popup;

import dao.ApartmentDao;
import dao.Database;
import dao.MonthDao;
import dao.OrderDao;
import dao.OrderDao;
import dao.ServiceDao;
import dao.UserDao;
import java.sql.Connection;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.SessionManager;
import models.Order;
import models.Order;
import models.Service;

import models.Table;
import models.User;
import utils.OrderStatus;
import utils.OrderType;
import utils.ShipmentStatus;
import utils.TableStatus;
import views.popup.AddOrderPopupView;
import views.popup.EditOrderPopupView;

public class OrderPopupController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    OrderDao orderDao = new OrderDao();
    MonthDao monthDao = new MonthDao();
    ApartmentDao apartmentDao = new ApartmentDao();
    UserDao userDao = new UserDao();
    JFrame previousView;

    ServiceDao serviceDao = new ServiceDao();

    public void add(AddOrderPopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());

        try {
            for (Service service : serviceDao.getAll()) {
                view.getTbComboBoxModel().addElement(service);
            }
        } catch (Exception e) {
            view.dispose();
            ec.onError(e);
            return;
        }

        view.getBtnOK().addActionListener(evt -> {
            try {
                addOrder(view);
//                Gọi tời hàm add phía dưới
                view.dispose();
                view.showMessage("Thêm hóa đơn công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }

    public void edit(EditOrderPopupView view, Order order, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());

        view.getLbIdOrder().setText(order.getId() + "");
        view.getTxtApartment().setText(order.getApartment().getApartmentCode() + "");
        view.getTxtPersoncharge().setText(order.getPersonPayment() + "");
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(order.getStartBill() + "");
            view.getTxtStartbill().setDate(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Date datePayment = new SimpleDateFormat("yyyy-MM-dd").parse(order.getPaymentTerm() + "");
            view.getTxtTermPayment().setDate(datePayment);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        if (order.getStatus() == 1) {
            view.getLbStatus().setText("Chưa thanh toán");
        } else {
            view.getLbStatus().setText("Đã thanh toán");
        }
        view.getTxtTotal().setText(order.getTotal() + "");
        view.getTxtTitle().setText(order.getDescription() + "");
        view.getTbComboBoxModel().setSelectedItem(order.getService());
        try {
            for (Service service : serviceDao.getAll()) { // Hiển thị danh sách bàn
                if (service.getStatus() == 1) {
                    view.getTbComboBoxModel().addElement(service);
                }
            }
        } catch (Exception e) {
            view.dispose();
            ec.onError(e);
            return;
        }
        view.getBtnOK().setText("Cập nhật");
        view.getBtnOK().addActionListener(evt -> {
            try {
                editOrder(view, order);
                view.dispose();
                view.showMessage("Sửa hóa đơn thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }

    public void addOrder(AddOrderPopupView view) throws Exception {
        Order order = new Order();

        Service service = (Service) view.getTbComboBoxModel().getSelectedItem();
        String user = SessionManager.getSession().getUser().getName();

        String startBills = sdf.format(view.getTxtStartbill().getDate());
        String PaymentTerm = sdf.format(view.getTxtPaymentTerm().getDate());

        String monthSelected = startBills.substring(5, 7);
        int monthInt = Integer.parseInt(monthSelected);
        String yearSelected = startBills.substring(0, 4);
        int yearInt = Integer.parseInt(yearSelected);
        String Apartment = view.getTxtApartment().getText();
        String Total = view.getTxtTotal().getText();
        double TotalDouble = Double.parseDouble(Total);
        String Description = view.getTxtDescription().getText();
         String PersonPayment = view.getTxtPersonPayment().getText();
        monthDao.getAll().forEach((month) -> {

            if (month.getMonth() == monthInt && month.getYear() == yearInt) {

                order.setMonthId(month.getId());
            } else {
                JOptionPane.showMessageDialog(null, "chua them du lieu");
            }
        });

        order.setStartBill(startBills);
        order.setPaymentTerm(PaymentTerm);
        order.setTotal(TotalDouble);
        order.setDescription(Description);
        order.setApartment(apartmentDao.findByUsername(Apartment));
        order.setService(service);
        order.setPersonPayment(PersonPayment);
        order.setDayPayment(null);
        order.setStatus(1);
        order.setTrash(1);
        order.setCreatedBy(user);
        orderDao.save(order);

        return;
    }

    public boolean editOrder(EditOrderPopupView view, Order order) throws Exception {

        String Apartment = view.getTxtApartment().getText();
        Service service = (Service) view.getTbComboBoxModel().getSelectedItem();
        String description = view.getTxtTitle().getText();

        String startBills = sdf.format(view.getTxtStartbill().getDate());
        String PaymentTerm = sdf.format(view.getTxtTermPayment().getDate());

        String dayPayment = sdf.format(view.getTxtDayPayment().getDate());
        String Total = view.getTxtTotal().getText();
        double TotalDouble = Double.parseDouble(Total);
        
        if (dayPayment != null) {
           order.setStatus(0);
        } else {
             order.setStatus(1);
        }

        order.setStartBill(startBills);
        order.setPaymentTerm(PaymentTerm);
        order.setTotal(TotalDouble);
        order.setDescription(description);
        order.setApartment(apartmentDao.findByUsername(Apartment));
        order.setService(service);
        order.setPersonPayment(null);
        order.setDayPayment(null);
    
        order.setTrash(1);

        orderDao.update(order);

        return true;
    }
}
