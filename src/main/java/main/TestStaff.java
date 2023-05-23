package main;

import controllers.UserDashboardController;
import dao.ResidentDao;
import dao.UserDao;
import models.User;
import views.ResidentDashboardView;

/**
 * createAt Dec 23, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class TestStaff {

    public static void main(String[] args) {
        UserDao ResidentDao = new UserDao();
        try {
            javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            System.out.println("Khởi tạo look and feel thành công!");
            User e = ResidentDao.get(2);
            SessionManager.create(e);
            new UserDashboardController(new ResidentDashboardView()).getView().requestFocus();
            Runtime.getRuntime().addShutdownHook(new ShutdownHook());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
