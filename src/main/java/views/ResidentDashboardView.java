/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controllers.user.chuyenmanhinh;
import controllers.user.danhmuc;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utils.ErrorPopup;
import views.admin.HomeView;
import views.admin.MenuItem;

/**
 *
 * @author WINDOWS
 */
public class ResidentDashboardView extends javax.swing.JFrame {
     JPanel[] cards;
    ArrayList<MenuItem> menuItems = new ArrayList<>();
    /**
     * Creates new form UserDashboardView
     */
    public ResidentDashboardView() {
        initComponents();
      chuyenmanhinh control = new chuyenmanhinh(panelLayout);
     List<danhmuc> listItem = new ArrayList<>();
     listItem.add(new danhmuc("DKDichVu", DKDichVuPanel, lblDKDichVu));
     listItem.add(new danhmuc("HoaDonDV", HoaDonDVPanel, lblHoadonDV));
       listItem.add(new danhmuc("GuiYeuCau", GuiYeuCauPanel, lblGuiYeuCau));
       control.setEvent(listItem);
     control.setView(panelLeft, lbName);
    btnLogout.putClientProperty("JButton.buttonType", "roundRect");
    }
    public void showError(String message) {
        ErrorPopup.show(new Exception(message));
    }

    public void showError(Exception e) {
        ErrorPopup.show(e);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public JLabel getLbName() {
        return lbName;
    }

    // Thêm dropdown menu
    public void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            MenuItem item = menu[i];
            menuItems.add(item);
            panelSideBar.add(item);
            ArrayList<MenuItem> subMenus = item.getSubMenu();
            for (MenuItem subMenu : subMenus) {
                addMenu(subMenu);
                subMenu.setVisible(false);
            }
        }
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setCards(JPanel[] cards) {
        this.cards = cards;
        initLayout();
    }

    // Thêm các pane vào cardlayout
    public void initLayout() {
        panelLayout.removeAll();
        for (int i = 0; i < cards.length; i++) {
            panelLayout.add(cards[i]);
        }
        panelLayout.updateUI();
    }

    public JPanel getPanelLayout() {
        return panelLayout;
    }

    public JPanel getPanelSideBar() {
        return panelSideBar;
    }

    public void setPanel(JPanel panel) {
        for (JPanel card : cards) {
            card.setVisible(false);
        }
        panel.setVisible(true);
    }
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelLeft = new javax.swing.JPanel();
        panelHeader = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        panelSideBar = new javax.swing.JPanel();
        DKDichVuPanel = new javax.swing.JPanel();
        lblDKDichVu = new javax.swing.JLabel();
        HoaDonDVPanel = new javax.swing.JPanel();
        lblHoadonDV = new javax.swing.JLabel();
        GuiYeuCauPanel = new javax.swing.JPanel();
        lblGuiYeuCau = new javax.swing.JLabel();
        panelLayout = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(250, 240, 202));

        panelLeft.setBackground(new java.awt.Color(255, 255, 255));
        panelLeft.setPreferredSize(new java.awt.Dimension(200, 680));
        panelLeft.setLayout(new java.awt.BorderLayout());

        panelHeader.setBackground(new java.awt.Color(244, 211, 94));
        panelHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        panelHeader.setForeground(new java.awt.Color(255, 255, 255));
        panelHeader.setToolTipText("");
        panelHeader.setPreferredSize(new java.awt.Dimension(200, 50));
        panelHeader.setLayout(new java.awt.GridBagLayout());

        lbName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(13, 59, 102));
        lbName.setText("Tên người login");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        panelHeader.add(lbName, gridBagConstraints);

        btnLogout.setBackground(new java.awt.Color(244, 211, 94));
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logout.png"))); // NOI18N
        btnLogout.setBorder(null);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setFocusable(false);
        btnLogout.setRequestFocusEnabled(false);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        panelHeader.add(btnLogout, gridBagConstraints);

        panelLeft.add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelSideBar.setBackground(new java.awt.Color(250, 240, 202));
        panelSideBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 211, 94)));

        DKDichVuPanel.setBackground(new java.awt.Color(250, 240, 202));
        DKDichVuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 211, 94)));
        DKDichVuPanel.setPreferredSize(new java.awt.Dimension(200, 100));

        lblDKDichVu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDKDichVu.setForeground(new java.awt.Color(13, 59, 102));
        lblDKDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/mailbox.png"))); // NOI18N
        lblDKDichVu.setText("Đăng ký dịch vụ");

        javax.swing.GroupLayout DKDichVuPanelLayout = new javax.swing.GroupLayout(DKDichVuPanel);
        DKDichVuPanel.setLayout(DKDichVuPanelLayout);
        DKDichVuPanelLayout.setHorizontalGroup(
            DKDichVuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DKDichVuPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblDKDichVu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DKDichVuPanelLayout.setVerticalGroup(
            DKDichVuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DKDichVuPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblDKDichVu)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        HoaDonDVPanel.setBackground(new java.awt.Color(250, 240, 202));
        HoaDonDVPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 211, 94)));
        HoaDonDVPanel.setPreferredSize(new java.awt.Dimension(200, 100));

        lblHoadonDV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoadonDV.setForeground(new java.awt.Color(13, 59, 102));
        lblHoadonDV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/file-invoice-dollar.png"))); // NOI18N
        lblHoadonDV.setText("Hóa đơn dịch vụ");

        javax.swing.GroupLayout HoaDonDVPanelLayout = new javax.swing.GroupLayout(HoaDonDVPanel);
        HoaDonDVPanel.setLayout(HoaDonDVPanelLayout);
        HoaDonDVPanelLayout.setHorizontalGroup(
            HoaDonDVPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HoaDonDVPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblHoadonDV)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        HoaDonDVPanelLayout.setVerticalGroup(
            HoaDonDVPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HoaDonDVPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblHoadonDV)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        GuiYeuCauPanel.setBackground(new java.awt.Color(250, 240, 202));
        GuiYeuCauPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 211, 94)));
        GuiYeuCauPanel.setPreferredSize(new java.awt.Dimension(200, 100));

        lblGuiYeuCau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGuiYeuCau.setForeground(new java.awt.Color(13, 59, 102));
        lblGuiYeuCau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/comments-question.png"))); // NOI18N
        lblGuiYeuCau.setText("Gửi yêu cầu");

        javax.swing.GroupLayout GuiYeuCauPanelLayout = new javax.swing.GroupLayout(GuiYeuCauPanel);
        GuiYeuCauPanel.setLayout(GuiYeuCauPanelLayout);
        GuiYeuCauPanelLayout.setHorizontalGroup(
            GuiYeuCauPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GuiYeuCauPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblGuiYeuCau)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        GuiYeuCauPanelLayout.setVerticalGroup(
            GuiYeuCauPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GuiYeuCauPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblGuiYeuCau)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelSideBarLayout = new javax.swing.GroupLayout(panelSideBar);
        panelSideBar.setLayout(panelSideBarLayout);
        panelSideBarLayout.setHorizontalGroup(
            panelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HoaDonDVPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
            .addComponent(DKDichVuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
            .addComponent(GuiYeuCauPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        );
        panelSideBarLayout.setVerticalGroup(
            panelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSideBarLayout.createSequentialGroup()
                .addComponent(HoaDonDVPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(DKDichVuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(GuiYeuCauPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelLeft.add(panelSideBar, java.awt.BorderLayout.CENTER);

        panelLayout.setBackground(new java.awt.Color(250, 240, 202));
        panelLayout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 211, 94)));
        panelLayout.setMaximumSize(new java.awt.Dimension(1000, 680));
        panelLayout.setMinimumSize(new java.awt.Dimension(1000, 680));
        panelLayout.setPreferredSize(new java.awt.Dimension(1008, 680));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nen.jpg"))); // NOI18N
        lblLogo.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout panelLayoutLayout = new javax.swing.GroupLayout(panelLayout);
        panelLayout.setLayout(panelLayoutLayout);
        panelLayoutLayout.setHorizontalGroup(
            panelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayoutLayout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(287, Short.MAX_VALUE))
        );
        panelLayoutLayout.setVerticalGroup(
            panelLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayoutLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1208, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(panelLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ResidentDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResidentDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResidentDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResidentDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResidentDashboardView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DKDichVuPanel;
    private javax.swing.JPanel GuiYeuCauPanel;
    private javax.swing.JPanel HoaDonDVPanel;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lblDKDichVu;
    private javax.swing.JLabel lblGuiYeuCau;
    private javax.swing.JLabel lblHoadonDV;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelLayout;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelSideBar;
    // End of variables declaration//GEN-END:variables


  
}