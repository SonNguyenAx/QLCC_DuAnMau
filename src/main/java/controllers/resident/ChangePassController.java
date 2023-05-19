/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.resident;

import controllers.popup.ErrorCallback;
import controllers.popup.SuccessCallback;
import dao.UserDao;
import javax.swing.JFrame;
import main.SessionManager;
import models.User;
import views.resident.ChangePassView;

/**
 *
 * @author Admin
 */
public class ChangePassController {

    UserDao employeeDao = new UserDao();
    JFrame previousView;

    public ChangePassController() {

    }

    public void show(ChangePassView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getBtnConfirm().addActionListener(evt -> {
            try {
                changePassword(view);
                sc.onSuccess();
            } catch (Exception e) {
                ec.onError(e);
            }
        });
    }

    public void changePassword(ChangePassView view) throws Exception {
        String oldPass = view.getTxtOldPass().getText(),
                newPass = view.getTxtNewPass().getText(),
                confirmPass = view.getTxtConfirmPass().getText();
        User currentLogin = SessionManager.getSession().getUser();
        if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        if (!oldPass.equals(currentLogin.getPassword())) {
            throw new Exception("Mật khẩu cũ không đúng ");
        }
        if (!newPass.equals(confirmPass)) {
            throw new Exception("Xác nhận mật khẩu sai!");
        }

        currentLogin.setPassword(newPass);
        employeeDao.update(currentLogin);
        view.dispose();
    }

}
