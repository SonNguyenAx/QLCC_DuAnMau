/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.resident;

import controllers.TimeCouterController;
import javax.swing.JOptionPane;
import main.SessionManager;
import models.Employee;
import models.User;
import utils.ErrorPopup;
import views.resident.ChangePassView;
import views.resident.HistoryLoginView;
import views.resident.InformationView;

/**
 *
 * @author Admin
 */
public class InformationController {

    private User session;
    private InformationView view;
    ChangePassController changePassController = new ChangePassController();
    HistoryLoginController historyLoginController = new HistoryLoginController();
   

    public InformationController() {
        session = SessionManager.getSession().getUser();
    }

    public InformationView getView() {
        return view;
    }

    public void setView(InformationView view) {
        if (this.view != view) {
            TimeCouterController.start((second) -> {
                view.getLbTimeWorking().setText(secondToHours(second));
            });
          
            addEvent(view);
            this.view = view;
        }
    }

    public void addEvent(InformationView view) {
        view.getBtnChangePass().addActionListener(evt -> {
            changePassController.show(new ChangePassView(), () -> JOptionPane.showMessageDialog(view, "Đổi pass thành công"), ErrorPopup::show);
        });
        view.getBtnHistoryLogin().addActionListener(evt -> {
            historyLoginController.show(new HistoryLoginView());
        });
    }

    private String secondToHours(int totalSecs) {
        int hours, minutes, seconds;
        hours = totalSecs / 3600;
        minutes = (totalSecs % 3600) / 60;
        seconds = totalSecs % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
