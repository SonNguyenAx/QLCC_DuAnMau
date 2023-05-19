package controllers;

import controllers.admin.ApartmentManagerController;
import controllers.admin.BuildingManagerController;
import controllers.admin.CustomerManagerController;
import controllers.admin.EmployeeManagerController;
import controllers.admin.OrderManagerController;


import controllers.admin.StatisticalController;
import controllers.admin.StatisticalUserController;


import controllers.resident.InformationController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.SessionManager;
import models.User;
import utils.IconManager;
import views.AdminDashboardView;
import views.LoginViewAdmin;
import views.admin.AboutView;
import views.admin.ApartmentManagerView;
import views.admin.BuildingManagerView;
import views.admin.CustomerManagerView;
import views.admin.EmployeeManagerView;

import views.admin.HomeView;
import views.admin.ManagerPaneView;
import views.admin.MenuItem;
import views.admin.OrderManagerView;
import views.admin.FeedbackManagerView;
import views.admin.StatisticalEmployeeView;
import views.admin.StatisticalIncomeView;
import views.admin.StatisticalView;
import views.resident.InformationView;


public class AdminDashboardController {

    private AdminDashboardView view;
    ManagerController employeeManagerController = new EmployeeManagerController(), // Controller
            buildingManagerController = new BuildingManagerController(),
               apartmentManagerController = new ApartmentManagerController(),
            orderManagerController = new OrderManagerController(),
            customerManagerController = new CustomerManagerController();
    
    StatisticalController statisticalController = new StatisticalController();
  
    StatisticalUserController statisticalEmployeeController = new StatisticalUserController();
    InformationController informationController = new InformationController();

    HomeView homeView = new HomeView();
    ManagerPaneView employeeManagerView = new EmployeeManagerView(), // View
               buildingManagerView = new BuildingManagerView(),
               apartmentManagerView = new ApartmentManagerView(),
            orderManagerView = new OrderManagerView(),
            shipmentManagerView = new FeedbackManagerView(),
            customerManagerView = new CustomerManagerView();
    StatisticalView statisticalView = new StatisticalView();
    StatisticalIncomeView statisticalIncomeView = new StatisticalIncomeView();
    StatisticalEmployeeView statisticalEmployeeView = new StatisticalEmployeeView();
    AboutView aboutView = new AboutView();
    InformationView informationView = new InformationView();
    JPanel[] cards = {
        homeView, employeeManagerView, buildingManagerView, customerManagerView,shipmentManagerView,orderManagerView,
        statisticalView, statisticalIncomeView, statisticalEmployeeView,
        aboutView, informationView
    };

    SideBarController sideBarController = new SideBarController();

    public AdminDashboardController(AdminDashboardView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
        view.setVisible(true);
        initMenu();
        addEvent();
        User session = SessionManager.getSession().getUser();
        if (session != null) {
            view.getLbName().setText(session.getName());
        }
        view.setCards(cards);
        view.setPanel(homeView);
    }

    public AdminDashboardView getView() {
        return view;
    }

    public void setView(AdminDashboardView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
    }

    private void initMenu() {
        IconManager im = new IconManager();
        MenuItem menuQLNV = new MenuItem("QLNV", im.getIcon("user_groups_25px.png"), "Quản lý nhân viên");
        MenuItem menuQLHH = new MenuItem("QLHH", im.getIcon("cardboard_box_25px.png"), "Quản lý tòa nhà");
        MenuItem menuQLDH = new MenuItem("QLDH", im.getIcon("shopping_cart_25px.png"), "Quản lý yêu cầu");
        MenuItem menuTK = new MenuItem("TK", im.getIcon("increase_25px.png"), "Thống kê");
        MenuItem menuTL = new MenuItem("TL", im.getIcon("settings_25px.png"), "Thiết lập");
         menuQLHH.addSubMenu(new MenuItem("QLTN", null, "Tòa nhà"));
            menuQLHH.addSubMenu(new MenuItem("QLCH", null, "Quản lý căn hộ"));
        menuQLHH.addSubMenu(new MenuItem("QLCD", im.getIcon("food_25px.png"), "Quản lý cư dân"));
        menuQLDH.addSubMenu(new MenuItem("QLYCCD", im.getIcon("table_25px.png"), "Quản lý yêu cầu cư dân"));
        menuQLDH.addSubMenu(new MenuItem("QLDDH", im.getIcon("purchase_order_25px.png"), "Quản lý hóa đơn"));
        menuQLDH.addSubMenu(new MenuItem("QLYCDV", im.getIcon("truck_25px.png"), "Quản lý yêu cầu dịch vụ"));
        menuTK.addSubMenu(new MenuItem("TKNV", im.getIcon("user_25px.png"), "Thống kê nhân viên"));
        menuTK.addSubMenu(new MenuItem("TKDT", null, "Thống kê doanh thu"));
        menuTL.addSubMenu(new MenuItem("TTCN", im.getIcon("about_25px.png"), "Thông tin cá nhân"));
        menuTL.addSubMenu(new MenuItem("TLGD", im.getIcon("contrast_25px.png"), "Giao diện"));
        menuTL.addSubMenu(new MenuItem("TT", im.getIcon("help_25px.png"), "About us"));
        sideBarController.addMenu(menuQLNV, menuQLHH, menuQLDH, menuTK, menuTL);
        sideBarController.addMenuEvent(this::onMenuChange);
    }

    // Tạo sự kiện
    private void addEvent() {
        view.getBtnLogout().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
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
                new LoginControllerAdmin(new LoginViewAdmin());
            }
        });
    }

    private void onMenuChange(MenuItem item) {
        switch (item.getId()) {
            case "QLNV"://Nhân viên
                view.setPanel(employeeManagerView);
                employeeManagerController.setView(employeeManagerView);
                employeeManagerController.updateData();
                break;
                
                 case "QLTN": //Quản lí tòa nhà
                view.setPanel(buildingManagerView);
                buildingManagerController.setView(buildingManagerView);
                buildingManagerController.updateData();
                break;
                 case "QLCH"://Quản lý căn hộ
//                view.setPanel(customerManagerView);
//                customerManagerController.setView(customerManagerView);
//                customerManagerController.updateData();
                break;
                   case "QLCD"://Quản lý cư dân
                view.setPanel(customerManagerView);
                customerManagerController.setView(customerManagerView);
                customerManagerController.updateData();
                break;
         
//            case "QLYCCD"://Quản lý yêu cầu cư dân
//                view.setPanel(tableManagerView);
//                tableManagerController.setView(tableManagerView);
//                tableManagerController.updateData();
//                break;
//         
             case "QLYCDV"://Quản lý dịch vụ
//                view.setPanel(tableManagerView);
//                tableManagerController.setView(tableManagerView);
//                tableManagerController.updateData();
                break;
                  case "QLYCCD"://Quản lý hóa đơn
                view.setPanel(orderManagerView);
                orderManagerController.setView(orderManagerView);
                orderManagerController.updateData();
                break;

             
            
            case "TL":
                break;
            case "TK"://Thống kê chung
                view.setPanel(statisticalView);
                statisticalController.setView(statisticalView);
                statisticalController.initData();
                break;
            case "TKNV"://Thống kê nhân viên
                view.setPanel(statisticalEmployeeView);
                statisticalEmployeeController.setView(statisticalEmployeeView);
                statisticalEmployeeController.initData();
                break;
            case "TKDT"://Thống kê doanh thu
                view.setPanel(statisticalIncomeView);
              
                break;
            case "TT":
                view.setPanel(aboutView);
                break;
            case "TTCN": // Thống tin cá nhân
                view.setPanel(informationView);
                informationController.setView(informationView);
                break;
            default:
                view.setPanel(homeView);
                break;
        }
    }
}
