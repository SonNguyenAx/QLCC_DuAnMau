package controllers.popup;

import dao.UserDao;
import javax.swing.JFrame;
import models.User;
import utils.UserRole;
import views.popup.EmployeePopupView;

/**
 * createAt Dec 17, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class UserPopupController {

    UserDao employeeDao = new UserDao();
    JFrame previousView;

    public void add(EmployeePopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        for (UserRole role : UserRole.values()) {
            view.getCboPermission().addItem(role.getName());
        }
        view.getBtnOK().addActionListener(evt -> {
            try {
                addEmployee(view);
                view.dispose();
                view.showMessage("Thêm nhân viên thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }

    public void edit(EmployeePopupView view, User employee, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        for (UserRole role : UserRole.values()) {
            view.getCboPermission().addItem(role.getName());
        }
        view.getLbTitle().setText("Sửa nhân viên - " + employee.getId());
       view.getTxtName().setText(employee.getName());
        view.getTxtPassword().setText(employee.getPassword());
       
        
        view.getCboPermission().setSelectedItem(employee.getRole().getName());
   
        view.getBtnOK().setText("Cập nhật");
        view.getBtnOK().addActionListener(evt -> {
            try {
                editEmployee(view, employee);
                view.dispose();
                view.showMessage("Sửa nhân viên thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }

    public void addEmployee(EmployeePopupView view) throws Exception {
        String username = view.getTxtUsername().getText(),
                password = view.getTxtPassword().getText(),
                phoneNumber = view.getTxtPhoneNumber().getText(),
                name = view.getTxtName().getText();
        int salary = (int) view.getSpnSalary().getValue();
        if (username.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        if (salary < 0) {
            throw new Exception("Lương không thể âm");
        }
        if (employeeDao.findByUsername(username) != null) {
            throw new Exception("Tài khoản đã tồn tại");
        }
        User e = new User();
        e.setName(name);
        e.setPassword(password);
       
     
        e.setRole(UserRole.getByName(view.getCboPermission().getSelectedItem().toString()));
       
        employeeDao.save(e);
        return;
    }

    public boolean editEmployee(EmployeePopupView view, User e) throws Exception {
        String username = view.getTxtUsername().getText(),
                password = view.getTxtPassword().getText(),
                phoneNumber = view.getTxtPhoneNumber().getText(),
                name = view.getTxtName().getText();
        int salary = (int) view.getSpnSalary().getValue();
        if (username.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        if (salary < 0) {
            throw new Exception("Lương không thể âm");
        }
        User temp = employeeDao.findByUsername(username);
        if (temp != null && temp.getId() != e.getId()) {
            throw new Exception("Tên tài khoản đã tồn tại");
        }
         e.setName(name);
        e.setPassword(password);
     
      
        e.setRole(UserRole.getByName(view.getCboPermission().getSelectedItem().toString()));
      
        employeeDao.update(e);
        return true;
    }

}
