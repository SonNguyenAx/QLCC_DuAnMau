package controllers.admin;

import controllers.ManagerController;
import controllers.popup.ApartmentPopupController;
import dao.ApartmentDao;


import dao.ApartmentDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import models.Apartment;

import views.popup.ApartmentPopupView;

public class ApartmentManagerController extends ManagerController {
    
//    ApartmentManagerController ApartmentDao ApartmentManagerController ApartmentPopupView Apartment
    
    ApartmentDao apartmentDao = new ApartmentDao();

    ApartmentPopupController popupController = new ApartmentPopupController();
    public ApartmentManagerController() {
        super();
    }

    @Override
    public void actionAdd() {

        popupController.add(new ApartmentPopupView(), this::updateData, view::showError);
    }

    @Override
    public void actionEdit() {
        try {
            int selectedId = view.getSelectedId();
            if (selectedId < 0) {
                throw new Exception("Chọn nhân viên cần edit");
            }
             Apartment e = apartmentDao.get(selectedId);
            if (e == null) {
                throw new Exception("Nhân viên bạn chọn không hợp lệ");
            }

            popupController.edit(new ApartmentPopupView(), e, this::updateData, view::showError);

        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionDelete() {
        int selectedIds[] = view.getSelectedIds();
        try {
            if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa hàng loạt?", "Xóa nhân viên", ERROR_MESSAGE) != YES_OPTION) {
                return;
            }
            for (int i = 0; i < selectedIds.length; i++) {
                apartmentDao.deleteById(selectedIds[i]);
                updateData();
            }
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Apartment> buildings = apartmentDao.getAll();
            view.setTableData(buildings);
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionSearch() {
        try {
            ArrayList<Apartment> buildings = apartmentDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
            view.setTableData(buildings);
        } catch (Exception e) {
            view.showError(e);
        }
    }

}
