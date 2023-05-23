package controllers.popup;


import dao.EmployeeDao;
import javax.swing.JFrame;
import models.Employee;

import views.popup.EmployeePopupView;

/**
 * createAt Dec 17, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class EmployeePopupController {

    EmployeeDao employeeDao = new EmployeeDao();
    JFrame previousView;

    public void add(EmployeePopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        
        view.getBtnOK().addActionListener(evt -> {
            try {
                addEmployee(view);
//                Gọi tời hàm add phía dưới
                view.dispose();
                view.showMessage("Thêm nhân viên thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }

    public void edit(EmployeePopupView view, Employee employee, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
       
        view.getLbTitle().setText("Sửa nhân viên - " + employee.getId());
       view.getTxtName().setText(employee.getName());
//        view.getTxtPassword().setText(employee.getPassword());
//       
//        
//        view.getCboPermission().setSelectedItem(employee.getRole().getName());
   
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
        Employee e = new Employee();
        e.setName(name);
       
       
     
//        e.setRole(EmployeeRole.getByName(view.getCboPermission().getSelectedItem().toString()));
       
        employeeDao.save(e);
        return;
    }

    public boolean editEmployee(EmployeePopupView view, Employee e) throws Exception {
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
        Employee temp = employeeDao.findByUsername(username);
        if (temp != null && temp.getId() != e.getId()) {
            throw new Exception("Tên tài khoản đã tồn tại");
        }
         e.setName(name);
   
     
      
//        e.setRole(EmployeeRole.getByName(view.getCboPermission().getSelectedItem().toString()));
      
        employeeDao.update(e);
        return true;
    }

}
