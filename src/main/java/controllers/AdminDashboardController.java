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
        homeView, employeeManagerView, buildingManagerView, customerManagerView, shipmentManagerView, orderManagerView,
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
        MenuItem menuQLCC = new MenuItem("QLCC", null, "Quản lý chung cư");
        MenuItem menuQLYC = new MenuItem("QLYC", null, "Quản lý yêu cầu");
        MenuItem menuQLDV = new MenuItem("QLDV", null, "Quản lý dịch vụ");
        MenuItem menuQLNV = new MenuItem("QLNV", null, "Quản lý nhân viên");
        MenuItem menuTL = new MenuItem("TL", null, "Thiết lập");
        menuQLCC.addSubMenu(new MenuItem("QLTN", null, "Quản lý tòa nhà"));
        menuQLCC.addSubMenu(new MenuItem("QLCH", null, "Quản lý căn hộ"));
        menuQLCC.addSubMenu(new MenuItem("QLCD", null, "Quản lý cư dân"));
        menuQLDV.addSubMenu(new MenuItem("QLDV", null, "Quản lý dịch vụ"));
        menuQLDV.addSubMenu(new MenuItem("QLHD", null, "Quản lý hóa đơn"));
        menuQLYC.addSubMenu(new MenuItem("QLYC", null, "Quản lý yêu cầu"));
        menuQLYC.addSubMenu(new MenuItem("GQYC", null, "Giải quyết yêu cầu"));
        menuQLNV.addSubMenu(new MenuItem("QLNV", null, "Quản lý nhân viên"));
        menuQLNV.addSubMenu(new MenuItem("QLLNV", null, "Quản lý lương nhân viên"));
        menuTL.addSubMenu(new MenuItem("TTCN", null, "Thông tin cá nhân"));
        menuTL.addSubMenu(new MenuItem("TLGD", null, "Giao diện"));
        menuTL.addSubMenu(new MenuItem("TT", im.getIcon("help_25px.png"), "About us"));
        sideBarController.addMenu(menuQLNV, menuQLCC, menuQLDV, menuQLYC, menuTL);
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
