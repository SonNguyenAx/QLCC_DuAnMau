package views.admin;

import javax.swing.DefaultComboBoxModel;
import models.Building;



public class BuildingManagerView extends ManagerPaneView<Building> {

     String[] list = {"ID", "name"};

    public BuildingManagerView() {
        super();
        setTableModel();
        renderTable();
    }

    @Override
    public void setTableModel() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Họ và tên");
        tableModel.addColumn("Số điện thoại");
        tableModel.addColumn("Ngày sinh");
        tableModel.addColumn("Giới tính");
         tableModel.addColumn("Chức vụ");
          tableModel.addColumn("Lương");
        tableModel.addColumn("Ngày vào làm");
      
        this.getCboSearchField().setModel(new DefaultComboBoxModel(list));
    }
}
