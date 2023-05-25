package controllers.admin;

import controllers.ManagerController;
import controllers.popup.EmployeePopupController;
import dao.EmployeeDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import models.Employee;

import views.popup.EmployeePopupView;


public class EmployeeManagerController extends ManagerController {
//EmployeeManagerController thêm hành động bật cửa sổ thêm sửa xóa 
    EmployeeDao employeeDao = new EmployeeDao();
    EmployeePopupController popupController = new EmployeePopupController();
//    Xử lí giao diện khi bật cửa sổ lên
    public EmployeeManagerController() {
        super();
    }

    @Override
    public void actionAdd() {

        popupController.add(new EmployeePopupView(), this::updateData, view::showError);
    }

    @Override
    public void actionEdit() {
        try {
            long selectedId = view.getSelectedId();
            if (selectedId < 0) {
                throw new Exception("Chọn nhân viên cần edit");
            }
             Employee e = employeeDao.get(selectedId);
            if (e == null) {
                throw new Exception("Nhân viên bạn chọn không hợp lệ");
            }

            popupController.edit(new EmployeePopupView(), e, this::updateData, view::showError);

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
                employeeDao.deleteById(selectedIds[i]);
                updateData();
            }
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            ArrayList<Employee> employees = employeeDao.getAll();
            view.setTableData(employees);
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionSearch() {
        try {
            ArrayList<Employee> employees = employeeDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
            view.setTableData(employees);
        } catch (Exception e) {
            view.showError(e);
        }
    }

}
