package controllers.resident;

import dao.SessionDao;
import java.util.ArrayList;
import javax.swing.JFrame;
import main.SessionManager;
import models.Session;
import views.resident.HistoryLoginView;
import views.resident.SessionLoginView;

public class HistoryLoginController {

    JFrame previousView;
    HistoryLoginView view;
    SessionDao sessionDao = new SessionDao();
    long id = SessionManager.getSession().getIdUser();

    public void show(HistoryLoginView view) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            render();
            return;
        }
        previousView = view;
        this.view = view;
        view.setVisible(true);
        render();
    }

    public void render() {
        view.getPanelContent1().removeAll();
        try {
            ArrayList<Session> sessions = sessionDao.getSession(id);
            for (Session session : sessions) {
                SessionLoginController ctr = new SessionLoginController();
                SessionLoginView view = new SessionLoginView();
                ctr.setView(view);
                ctr.render(session);
                this.view.getPanelContent1().add(view);
                this.view.getPanelContent1().updateUI();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
