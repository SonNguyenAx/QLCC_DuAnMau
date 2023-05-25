package controllers.admin;

import controllers.ManagerController;
import controllers.popup.BuildingPopupController;


import dao.BuildingDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import models.Building;

import views.popup.BuildingPopupView;

public class BuildingManagerController extends ManagerController {
    
//    BuildingManagerController BuildingDao BuildingPopupController BuildingPopupView Building
    
    BuildingDao buildingDao = new BuildingDao();

    BuildingPopupController popupController = new BuildingPopupController();
    public BuildingManagerController() {
        super();
    }

    @Override
    public void actionAdd() {

        popupController.add(new BuildingPopupView(), this::updateData, view::showError);
    }

    @Override
    public void actionEdit() {
        try {
            long selectedId = view.getSelectedId();
            if (selectedId < 0) {
                throw new Exception("Chọn nhân viên cần edit");
            }
             Building e = buildingDao.get(selectedId);
            if (e == null) {
                throw new Exception("Nhân viên bạn chọn không hợp lệ");
            }

            popupController.edit(new BuildingPopupView(), e, this::updateData, view::showError);

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
                buildingDao.deleteById(selectedIds[i]);
                updateData();
            }
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Building> buildings = buildingDao.getAll();
            view.setTableData(buildings);
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionSearch() {
        try {
            ArrayList<Building> buildings = buildingDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
            view.setTableData(buildings);
        } catch (Exception e) {
            view.showError(e);
        }
    }

}
