package views.admin;

import javax.swing.DefaultComboBoxModel;
import models.Employee;
import models.User;

/**
 * createAt Dec 15, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class ApartmentManagerView extends ManagerPaneView<Employee> {

    String[] list = {};

    public ApartmentManagerView() {
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
