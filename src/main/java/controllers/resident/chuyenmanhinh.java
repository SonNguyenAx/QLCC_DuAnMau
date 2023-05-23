/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import views.ResidentDKDichVu;
import views.ResidentGuiYeuCau;
import views.ResidentHoaDonDichVu;
import views.admin.HomeView;
/**
 *
 * @author WINDOWS
 */
public class chuyenmanhinh {
    private JPanel root;
    private String kindSelected = "";
    private List<danhmuc> listItem = null;
    
    public chuyenmanhinh (JPanel jpnRoot) {
        this.root = jpnRoot;
    }
    
    public void setView (JPanel jpnItem, JLabel lblItem) {
        kindSelected = "HomeView";
//        jpnItem.setBackground(new Color(23, 70, 162));
//        lblItem.setBackground(new Color(23, 70, 162));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new HomeView());
        root.validate();
        root.repaint();
    }
    
     public void setEvent(List<danhmuc> listItem) {
        this.listItem = listItem;
        for (danhmuc item : listItem) {
            item.getLbl().addMouseListener(new LabelEvent(item.getKind(), item.getJpanel(), item.getLbl()));
        }
    }
     
     class LabelEvent implements MouseListener {
        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel lblItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.lblItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch(kind) {
                case "HomeView":
                    node = new HomeView();
                    break;
                case "DKDichVu":
                    node = new ResidentDKDichVu();
                    break;
                case "HoaDonDV":
                    node = new ResidentHoaDonDichVu();
                    break;
                case "GuiYeuCau":
                    node = new ResidentGuiYeuCau();
                    break;
                default: node = new HomeView();
            }
                root.removeAll();
                root.setLayout(new BorderLayout());
                root.add(node);
                root.validate();
                root.repaint();
                changeBackGroundColor(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
//            jpnItem.setBackground(new Color(23, 70, 162));
//            lblItem.setBackground(new Color(23, 70, 162));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//            jpnItem.setBackground(new Color(23, 70, 162));
//            lblItem.setBackground(new Color(23, 70, 162));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
//                jpnItem.setBackground(new Color(95, 157, 247));
//                lblItem.setBackground(new Color(95, 157, 247));
            }
        }
        
        
    }
    
    private void changeBackGroundColor(String kind) {
        for (danhmuc item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
//                item.getJpanel().setBackground(new Color(23, 70, 162));
//                item.getLbl().setBackground(new Color(23, 70, 162));
            } else {
//                item.getLbl().setBackground(new Color(95, 157, 247));
//                item.getJpanel().setBackground(new Color(95, 157, 247));
            }
        }
    }
}
