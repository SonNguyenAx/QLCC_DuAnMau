package controllers.admin;

import controllers.ManagerController;
import controllers.popup.CustomerPopupController;
import dao.ResidentDao;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import models.Resident;
import views.popup.CustomerPopupView;


public class ResidentManagerController extends ManagerController {

    ResidentDao customerDao = new ResidentDao();
    
    CustomerPopupController popupController = new CustomerPopupController();
    CustomerPopupController customerPopupController = new CustomerPopupController();

    public ResidentManagerController() {
        super();
    }

    @Override
    public void actionAdd() {
//        popupController.add(this, new CustomerPopupView());
        customerPopupController.add(new CustomerPopupView(), this::updateData, view::showError);
    }

    @Override
    public void actionDelete() {
        Long selectedIds[] = view.getSelectedIds();
        try {
            if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa hàng loạt?", "Xóa khách hàng", ERROR_MESSAGE) != YES_OPTION) {
                return;
            }
            for (int i = 0; i < selectedIds.length; i++) {
                long id = selectedIds[i];
//                shipmentDao.deleteByIdCustomer(id);
                customerDao.deleteById(id);
                updateData();
            }
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionEdit() {
        try {
            long selectedId = view.getSelectedId();
            if (selectedId < 0) {
                throw new Exception("Chọn khách hàng cần edit");
            } else {
                Resident c = customerDao.get(selectedId);
                if (c == null) {
                    throw new Exception("Khách hàng bạn chọn không hợp lệ");
                }
//                popupController.edit(this, new CustomerPopupView(), c);
                popupController.edit(new CustomerPopupView(), c, this::updateData, view::showError);
            }
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Resident> customers = customerDao.getAll();
            view.setTableData(customers);
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionSearch() {
        try {
            ArrayList<Resident> customers = customerDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
            view.setTableData(customers);
        } catch (Exception e) {
            view.showError(e);
        }
    }

}
