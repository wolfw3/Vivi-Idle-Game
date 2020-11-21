package com.weiss;

import javax.swing.*;

import static com.weiss.Main.*;

public class GUI_Main {

    private JButton btn_click;
    private JPanel panel;
    private JLabel lbl_points;
    private JButton btn_upgrades;
    private JButton btn_idle_upgrades;
    private JButton btn_exit;
    public JFrame frame = new JFrame("Clicker");

    public GUI_Main() {
        btn_click.addActionListener(e -> {
            clickUpgraders.forEach(clickUpgrader -> points += clickUpgrader.getCount() * clickUpgrader.getPointsPerClick()); //Adds upgraders' bonus points
            points++; //Base click
            update();
        });
        btn_upgrades.addActionListener(e -> GUI_Upgrades.init()); //Opens upgrade menu on button click
        btn_idle_upgrades.addActionListener(e -> GUI_Idle_Upgrades.init()); //Opens idle upgrade menu on button click
        btn_exit.addActionListener(actionEvent -> {
            SaveManager.save();
            System.exit(1);
        });
    }

    public void setPoints() {
        this.lbl_points.setText(String.valueOf(points)); //Updates GUI points label
    }

    public void init() {
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
