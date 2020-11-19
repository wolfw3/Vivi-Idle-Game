package com.weiss;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.weiss.Main.*;

public class GUI_Main {

    private JButton btn_click;
    private JPanel panel;
    private JLabel lbl_points;
    private JButton btn_upgrades;
    private JButton btn_idle_upgrades;
    public JFrame frame = new JFrame("Clicker");
    public GUI_Main() {
        btn_click.addActionListener(e -> {
                points += (clickMultiplier);
                update();
        });
        btn_upgrades.addActionListener(e -> GUI_Upgrades.init());
        btn_idle_upgrades.addActionListener(e -> GUI_Idle_Upgrades.init());
    }

    public void setPoints() {
        this.lbl_points.setText(String.valueOf(points));
    }

    public void init() {
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
