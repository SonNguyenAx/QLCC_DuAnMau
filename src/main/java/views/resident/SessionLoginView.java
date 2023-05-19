/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.resident;

import java.awt.Dimension;
import javax.swing.JLabel;

/**
 * createAt Jan 12, 2021
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class SessionLoginView extends javax.swing.JPanel {

    /**
     * Creates new form SessionLoginView
     */
    public SessionLoginView() {
        setPreferredSize(new Dimension(400, 60));
        initComponents();
    }

    public JLabel getLbEndTime() {
        return lbEndTime;
    }

    public JLabel getLbMessage() {
        return lbMessage;
    }

    public JLabel getLbStartTime() {
        return lbStartTime;
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

        lbStartTime = new javax.swing.JLabel();
        lbEndTime = new javax.swing.JLabel();
        lbMessage = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(400, 60));
        setMinimumSize(new java.awt.Dimension(400, 60));
        setLayout(new java.awt.GridBagLayout());

        lbStartTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbStartTime.setText("12/01/2021 19:12:02");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lbStartTime, gridBagConstraints);

        lbEndTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbEndTime.setText("12/01/2021 19:12:02");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lbEndTime, gridBagConstraints);

        lbMessage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMessage.setForeground(new java.awt.Color(82, 164, 0));
        lbMessage.setText("Đang hoạt động");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lbMessage, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbEndTime;
    private javax.swing.JLabel lbMessage;
    private javax.swing.JLabel lbStartTime;
    // End of variables declaration//GEN-END:variables
}