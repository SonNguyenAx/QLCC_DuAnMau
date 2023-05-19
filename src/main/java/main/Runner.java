package main;


import models.User;

import views.MainView;

/**
 * createAt Dec 15, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class Runner {

 private static User session;

    public static User getSession() {
        return session;
    }

    public static void setSession(User session) {
        Runner.session = session;
    }

    public static void main(String[] args) {
        //Set up look and feel
        try {
            javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            System.out.println("Khởi tạo look and feel thành công!");
        } catch (Exception ex) {
            System.err.println("Khởi tạo look and feel thất bại!");
        }
//        LoginController controller = new LoginController(new LoginViewAdmin());
            MainView mv = new MainView();
            mv.setVisible(true);
             Runtime.getRuntime().addShutdownHook(new ShutdownHook());
    }

}
