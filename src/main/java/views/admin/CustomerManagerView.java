package views.admin;

import javax.swing.DefaultComboBoxModel;
import models.Resident;

/**
 * createAt Dec 15, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class CustomerManagerView extends ManagerPaneView<Resident> {

    String[] list = {"ID", "phoneNumber", "name", "address"};

    public CustomerManagerView() {
        super();
        setTableModel();
        renderTable();
    }

    @Override
    public void setTableModel() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Họ và tên");
        tableModel.addColumn("Số điện thoại");
        tableModel.addColumn("Địa chỉ");
        tableModel.addColumn("Ngày sinh");
        this.getCboSearchField().setModel(new DefaultComboBoxModel(list));
    }
}
