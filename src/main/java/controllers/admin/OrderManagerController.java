package controllers.admin;

import controllers.ManagerController;
import controllers.popup.OrderPopupController;
import controllers.popup.OrderPopupController;
import dao.OrderDao;
import dao.OrderDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import models.Order;
import views.popup.AddOrderPopupView;
import views.popup.EditOrderPopupView;





public class OrderManagerController extends ManagerController {

    OrderDao orderDao = new OrderDao();
    OrderPopupController popupController = new OrderPopupController();
    
    public OrderManagerController() {
        super();
    }

    @Override
    public void actionAdd() {

        popupController.add(new AddOrderPopupView(), this::updateData, view::showError);
    }

    @Override
    public void actionEdit() {
        try {
            long selectedId = view.getSelectedId();
            if (selectedId < 0) {
                throw new Exception("Chọn nhân viên cần edit");
            }
             Order e = orderDao.get(selectedId);
            if (e == null) {
                throw new Exception("Nhân viên bạn chọn không hợp lệ");
            }

            popupController.edit(new EditOrderPopupView(), e, this::updateData, view::showError);

        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionDelete() {
        Long selectedIds[] = view.getSelectedIds();
        try {
            if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa hàng loạt?", "Xóa nhân viên", ERROR_MESSAGE) != YES_OPTION) {
                return;
            }
            for (int i = 0; i < selectedIds.length; i++) {
                orderDao.deleteById(selectedIds[i]);
                updateData();
            }
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Order> employees = orderDao.getAll();
            view.setTableData(employees);
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionSearch() {
        try {
            ArrayList<Order> employees = orderDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
            view.setTableData(employees);
        } catch (Exception e) {
            view.showError(e);
        }
    }

}
