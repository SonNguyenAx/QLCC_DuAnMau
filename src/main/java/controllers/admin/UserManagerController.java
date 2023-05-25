package controllers.admin;

import controllers.ManagerController;
import controllers.popup.UserPopupController;
import dao.UserDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import models.User;
import utils.UserRole;
import views.popup.EmployeePopupView;


public class UserManagerController extends ManagerController {

    UserDao userDao = new UserDao();
    UserPopupController popupController = new UserPopupController();

    public UserManagerController() {
        super();
    }

    @Override
    public void actionAdd() {
//        popupController.add(this, new EmployeePopupView());
        popupController.add(new EmployeePopupView(), this::updateData, view::showError);
    }

    @Override
    public void actionEdit() {
        try {
            long selectedId = view.getSelectedId();
            if (selectedId < 0) {
                throw new Exception("Chọn nhân viên cần edit");
            }
            User e = userDao.get(selectedId);
            if (e == null) {
                throw new Exception("Nhân viên bạn chọn không hợp lệ");
            }
            if (e.getRole()== UserRole.MANAGER) {
                int value = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn chỉnh sửa admin?");
                if (value != YES_OPTION) {
                    return;
                }
            }
//                popupController.edit(this, new EmployeePopupView(), e);
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
                userDao.deleteById(selectedIds[i]);
                updateData();
            }
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void updateData() {
        try {
            ArrayList<User> employees = userDao.getAll();
            view.setTableData(employees);
        } catch (Exception e) {
            view.showError(e);
        }
    }

    @Override
    public void actionSearch() {
        try {
            ArrayList<User> employees = userDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
            view.setTableData(employees);
        } catch (Exception e) {
            view.showError(e);
        }
    }

}
