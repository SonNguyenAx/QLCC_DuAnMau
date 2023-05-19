package main;

import controllers.UserDashboardController;
import dao.UserDao;
import models.User;
import views.EmployeeDashboardView;

/**
 * createAt Dec 23, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class TestStaff {

    public static void main(String[] args) {
        UserDao employeeDao = new UserDao();
        try {
            javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            System.out.println("Khởi tạo look and feel thành công!");
            User e = employeeDao.get(2);
            SessionManager.create(e);
            new UserDashboardController(new EmployeeDashboardView()).getView().requestFocus();
            Runtime.getRuntime().addShutdownHook(new ShutdownHook());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
