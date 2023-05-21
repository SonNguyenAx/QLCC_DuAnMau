/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.user;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author WINDOWS
 */
public class danhmuc {
    private String kind;
    private JPanel jpanel;
    private JLabel lbl;

    public danhmuc() {
    }

    public danhmuc(String kind, JPanel jpanel, JLabel lbl) {
        this.kind = kind;
        this.jpanel = jpanel;
        this.lbl = lbl;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    public void setJpanel(JPanel jpanel) {
        this.jpanel = jpanel;
    }

    public JLabel getLbl() {
        return lbl;
    }

    public void setLbl(JLabel lbl) {
        this.lbl = lbl;
    }
}
