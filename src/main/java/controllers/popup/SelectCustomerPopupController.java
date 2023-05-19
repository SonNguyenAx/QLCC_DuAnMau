package controllers.popup;

import dao.ResidentDao;
import java.sql.SQLException;
import javax.swing.JFrame;
import models.Resident;
import views.popup.SelectCustomerPopupView;

/**
 * createAt Dec 31, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.cSom>
 */
public class SelectCustomerPopupController {

    ResidentDao customerDao = new ResidentDao();
    JFrame previousView;

    public interface Callback {

        public abstract void run(Resident customer);
    }

    public void select(SelectCustomerPopupView view, Callback callback) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        try {
            view.renderCustomer(customerDao.getAll());
        } catch (SQLException e) {
            view.showError(e);
        }
        view.getBtnOK().addActionListener(evt -> {
            Resident c = view.getListCustomer().getSelectedValue();
            if (c == null) {
                view.showError("Bạn chưa chọn khách hàng");
                return;
            }
            callback.run(c);
            view.dispose();
        });
        view.getBtnSearch().addActionListener(evt -> {
            String txtSearch = view.getTxtCustomerName().getText();
            try {
                view.renderCustomer(customerDao.searchByKey("name", txtSearch));
            } catch (Exception e) {
                view.showError(e);
            }
        });
        view.getBtnCancel().addActionListener(evt -> {
            view.dispose();
        });
    }
}
