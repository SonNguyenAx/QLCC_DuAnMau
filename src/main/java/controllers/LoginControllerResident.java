package controllers;

import dao.ResidentDao;
import dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import main.SessionManager;
import models.User;
import views.AdminDashboardView;
import views.EmployeeDashboardView;
import views.LoginViewResident;
import views.admin.HomeView;


public class LoginControllerResident {

    private LoginViewResident view;
    ResidentDao residentDao = new ResidentDao();

    public LoginControllerResident(LoginViewResident view) {
        this.view = view;
        view.setVisible(true);
        addEvent();
    }

    public LoginViewResident getView() {
        return view;
    }

    public void setView(LoginViewResident view) {
        this.view = view;
        view.setVisible(true);
    }

    public void login() {
//        String username = view.getTxtUsername().getText();
//        String password = new String(view.getTxtPassword().getPassword());
//    
//            User employee = employeeDao.findByUsername(username);
//            if (employee == null) {
//                view.showError("Không tồn tại tài khoản!");
//                return;
//            }
//            if (!employee.checkPassword(password)) {
//                view.showError("Mật khẩu sai");
//                return;
//            }
//           
//            AdminDashboardController controller = new AdminDashboardController(new AdminDashboardView());
//                    controller.getView().setPanel(new HomeView());
//                    view.dispose();// Tắt form đăng nhập
                 
          
        
    }

    // Tạo sự kiện
    public void addEvent() {
        //Sự kiện login
        view.getTxtPassword().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    view.getBtnLogin().doClick();
                }
            }
        });
        view.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                login();
            }
        });
        view.getLblForgotPassword().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view.showMessage("Chưa hỗ trợ!");
            }
        });
        view.getLblRegister().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view.showMessage("Chưa hỗ trợ!");
            }
        });
    }

}
