package controllers;

import dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import main.SessionManager;
import models.User;
import views.AdminDashboardView;
import views.ResidentDashboardView;
import views.LoginViewAdmin;
import views.admin.HomeView;

/**
 * createAt Dec 15, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class LoginControllerAdmin {

    private LoginViewAdmin view;
    UserDao userDao = new UserDao();

    public LoginControllerAdmin(LoginViewAdmin view) {
        this.view = view;
        view.setVisible(true);
        addEvent();
    }

    public LoginViewAdmin getView() {
        return view;
    }

    public void setView(LoginViewAdmin view) {
        this.view = view;
        view.setVisible(true);
    }

    public void login() {
        String username = view.getTxtUsername().getText();
        String password = new String(view.getTxtPassword().getPassword());
        try {
            User user = userDao.findByUsername(username);
            if (user == null) {
                view.showError("Không tồn tại tài khoản!");
                return;
            }
            if (!user.checkPassword(password)) {
                view.showError("Mật khẩu sai");
                return;
            }
            SessionManager.create(user);//Khởi tạo session

            switch (user.getRole()) {
                case MANAGER:
                    //Admin controller
                    AdminDashboardController controller = new AdminDashboardController(new AdminDashboardView());
                    controller.getView().setPanel(new HomeView());
                    view.dispose();// Tắt form đăng nhập
                    break;
                case STAFF:
                    UserDashboardController controller1 = new UserDashboardController(new ResidentDashboardView());
                    controller1.getView().setPanel(new HomeView());
                    view.dispose();// Tắt form đăng nhập                    
                    break;
                //Seller Controller
                case INACTIVE:
                    view.showError("Tài khoản của bạn đã bị khóa.\nVui lòng liên hệ admin để biết thêm chi tiết");
                    SessionManager.update();
                    view.dispose();
                    break;
                default:
                    view.showError("Vui lòng liên hệ admin để biết thêm chi tiết");
                    SessionManager.update();
                    view.dispose();
                    break;
            }
        } catch (Exception e) {
            view.showError(e);
        }
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
