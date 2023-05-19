package controllers.popup;



import dao.BuildingDao;
import javax.swing.JFrame;
import models.Building;

import views.popup.BuildingPopupView;

/**
 * createAt Dec 17, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class BuildingPopupController {

    BuildingDao buildingDao = new BuildingDao();
    JFrame previousView;

    public void add(BuildingPopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        
        view.getBtnOK().addActionListener(evt -> {
            try {
                addBuilding(view);
//                Gọi tời hàm add phía dưới
                view.dispose();
                view.showMessage("Thêm nhân viên thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }

    public void edit(BuildingPopupView view, Building building, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
       
        view.getLbTitle().setText("Sửa nhân viên - " + building.getId());
       view.getTxtName().setText(building.getNameBuilding());
//        view.getTxtPassword().setText(building.getPassword());
//       
//        
//        view.getCboPermission().setSelectedItem(building.getRole().getName());
   
        view.getBtnOK().setText("Cập nhật");
        view.getBtnOK().addActionListener(evt -> {
            try {
                editBuilding(view, building);
                view.dispose();
                view.showMessage("Sửa nhân viên thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }

    public void addBuilding(BuildingPopupView view) throws Exception {
        String username = view.getTxtUsername().getText(),
                password = view.getTxtPassword().getText(),
                phoneNumber = view.getTxtPhoneNumber().getText(),
                name = view.getTxtName().getText();
        int salary = (int) view.getSpnSalary().getValue();
        if (username.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        if (salary < 0) {
            throw new Exception("Lương không thể âm");
        }
        if (buildingDao.findByUsername(username) != null) {
            throw new Exception("Tài khoản đã tồn tại");
        }
        Building e = new Building();
        e.setNameBuilding(name);
       
       
     
//        e.setRole(BuildingRole.getByName(view.getCboPermission().getSelectedItem().toString()));
       
        buildingDao.save(e);
        return;
    }

    public boolean editBuilding(BuildingPopupView view, Building e) throws Exception {
        String username = view.getTxtUsername().getText(),
                password = view.getTxtPassword().getText(),
                phoneNumber = view.getTxtPhoneNumber().getText(),
                name = view.getTxtName().getText();
        int salary = (int) view.getSpnSalary().getValue();
        if (username.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        if (salary < 0) {
            throw new Exception("Lương không thể âm");
        }
        Building temp = buildingDao.findByUsername(username);
        if (temp != null && temp.getId() != e.getId()) {
            throw new Exception("Tên tài khoản đã tồn tại");
        }
         e.setNameBuilding(name);
   
     
      
//        e.setRole(BuildingRole.getByName(view.getCboPermission().getSelectedItem().toString()));
      
        buildingDao.update(e);
        return true;
    }

}
