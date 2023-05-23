/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.SessionManager;
import models.User;
import utils.IconManager;
import views.LoginViewResident;
import views.ResidentHoaDonDichVu;
import views.ResidentDKDichVu;
import views.ResidentGuiYeuCau;
import views.ResidentDashboardView;
import views.resident.MenuItem;


/**
 *
 * @author Admin
 */
public class UserDashboardController {

    private ResidentDashboardView view;
    ResidentHoaDonDichVu hddv = new ResidentHoaDonDichVu();
    ResidentDKDichVu dkdv = new ResidentDKDichVu();
    ResidentGuiYeuCau gyc = new ResidentGuiYeuCau();
    
    SideBarController sideBarController = new SideBarController();
    JPanel[] cards = {hddv,dkdv,gyc,};
    private JPanel UserHoaDonDichVu;
    private JPanel UserDKDichVu;
    private JPanel UserGuiYeuCau;
    private JPanel homeView;

    public UserDashboardController(ResidentDashboardView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
        view.setVisible(true);
//        initMenu();
        addEvent();
        User session = SessionManager.getSession().getUser();
        if (session != null) {
            view.getLbName().setText(session.getName());
        }
        view.setCards(cards);
        view.setPanel(homeView);
    }

    public ResidentDashboardView getView() {
        return view;
    }

    public void setView(ResidentDashboardView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
    }

    private void initMenu() {
        IconManager im = new IconManager();
        MenuItem menuHDDV = new MenuItem("QLKH", null, "Hóa Đơn Dịch Vụ");
        MenuItem menuDKDV = new MenuItem("QLDDH", null, "Đăng ký dịch vụ");
        MenuItem menuGYC = new MenuItem("QLGH", null, "Gửi yêu cầu");
 
        sideBarController.addMenu(menuHDDV, menuDKDV, menuGYC);
       
    }

    private void addEvent() {
        view.getBtnLogout().addActionListener(evt -> {
            int confirm = JOptionPane.showConfirmDialog(view, "Bạn thực sự muốn đăng xuất?");
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            try {
                SessionManager.update();// Đẵng xuất
            } catch (SQLException ex) {
                view.showError(ex);
            }
            view.dispose();
            new LoginControllerResident(new LoginViewResident());
        });
    }

//    private void onMenuChange(MenuItem item) {
//        switch (item.getId()) {
//            case "HDDV"://Hóa đơn dịch vụ
//                view.setPanel(ResidentHoaDonDichVu);
////                orderManagerController.setView(orderManagerView);
////                orderManagerController.updateData();
//                break;
//            case "DKDV"://Đăng ký dịch vụ
//                view.setPanel(ResidentDKDichVu);
//               
//                break;
//            case "GYC"://Gửi yêu cầu
//                view.setPanel(ResidentGuiYeuCau);
////                shipmentManagerController.setView(shipmentManagerView);
////                shipmentManagerController.updateData();
//                break;
////            case "TT":
////                view.setPanel(aboutView);
////                break;
////            case "TTCN": // Thống tin cá nhân
////                view.setPanel(informationView);
////                informationController.setView(informationView);
////                break;
//            default:
//                view.setPanel(homeView);
//                break;
//        }
//    }

    
}
