package views.admin;

import javax.swing.DefaultComboBoxModel;
import models.Shipment;


public class FeedbackManagerView extends ManagerPaneView<Shipment> {

    String[] list = {"id","name"};

    public FeedbackManagerView() {
        super();
        setTableModel();
        renderTable();
    }

    @Override
    public void setTableModel() {
        tableModel.addColumn("Mã HD");
        tableModel.addColumn("Tên KH");
        tableModel.addColumn("Địa chỉ");
        tableModel.addColumn("Tên shipper");
        tableModel.addColumn("SDT shipper");
        tableModel.addColumn("Giá Ship");
        tableModel.addColumn("Trạng thái");
        tableModel.addColumn("Ngày bắt đầu");
        tableModel.addColumn("Ngày kết thúc");
        this.getCboSearchField().setModel(new DefaultComboBoxModel(list));
    }
}
