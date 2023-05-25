package views.admin;

import javax.swing.DefaultComboBoxModel;
import models.Order;

/**
 * createAt Dec 15, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class OrderManagerView extends ManagerPaneView<Order> {

    String[] list = {"ID"};

    public OrderManagerView() {
        super();
        setTableModel();
        renderTable();
    }

    @Override
    public void setTableModel() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Tiêu đề ");
        tableModel.addColumn("Số tiền");
        tableModel.addColumn("Căn hộ");
        tableModel.addColumn("Kì thanh toán");
        tableModel.addColumn("Hạn thanh toán");
        tableModel.addColumn("Ngày thanh toán");
        tableModel.addColumn("Người tạo");
        this.getCboSearchField().setModel(new DefaultComboBoxModel(list));
    }
}
