package controllers.popup;



import dao.ApartmentDao;
import dao.ApartmentDao;
import javax.swing.JFrame;
import models.Apartment;
import views.popup.ApartmentPopupView;

import views.popup.ApartmentPopupView;


public class ApartmentPopupController {

    ApartmentDao apartmentDao = new ApartmentDao();
    JFrame previousView;

    public void add(ApartmentPopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        
        view.getBtnOK().addActionListener(evt -> {
            try {
                addApartment(view);
//                Gọi tời hàm add phía dưới
                view.dispose();
                view.showMessage("Thêm nhân viên thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }

    public void edit(ApartmentPopupView view, Apartment apartment, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
       
//        view.getLbTitle().setText("Sửa nhân viên - " + apartment.getId());
//       view.getTxtName().setText(apartment.get());
//        view.getTxtPassword().setText(apartment.getPassword());
//       
//        
//        view.getCboPermission().setSelectedItem(apartment.getRole().getName());
   
        view.getBtnOK().setText("Cập nhật");
        view.getBtnOK().addActionListener(evt -> {
            try {
                editApartment(view, apartment);
                view.dispose();
                view.showMessage("Sửa nhân viên thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }

    public void addApartment(ApartmentPopupView view) throws Exception {
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
        if (apartmentDao.findByUsername(username) != null) {
            throw new Exception("Tài khoản đã tồn tại");
        }
        Apartment e = new Apartment();
        e.setApartmentnumber(name);
       
       
     
//        e.setRole(ApartmentRole.getByName(view.getCboPermission().getSelectedItem().toString()));
       
        apartmentDao.save(e);
        return;
    }

    public boolean editApartment(ApartmentPopupView view, Apartment e) throws Exception {
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
//        Apartment temp = apartmentDao.findByUsername(username);
//        if (temp != null && temp.getId() != e.getId()) {
//            throw new Exception("Tên tài khoản đã tồn tại");
//        }
//         e.setNameApartment(name);
   
     
      
//        e.setRole(ApartmentRole.getByName(view.getCboPermission().getSelectedItem().toString()));
      
        apartmentDao.update(e);
        return true;
    }

}
