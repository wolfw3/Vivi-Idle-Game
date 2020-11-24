package com.weiss.gui;

import com.weiss.Upgrader;

import javax.swing.*;

import static com.weiss.Main.*;
import static com.weiss.Upgrader.*;

public class Upgrades {

    public JFrame frame = new JFrame("Upgrades");
    public int buyAmount = 1;
    private JPanel panel;
    private JButton btn_small_point_gen;
    private JButton btn_medium_point_gen;
    private JButton btn_large_point_gen;
    public JButton btn_click_upgrade;
    public Upgrader smallPointGen = new Upgrader(1, 10, "Small Point Generator", PASSIVE, btn_small_point_gen);
    public Upgrader mediumPointGen = new Upgrader(3, 100, "Medium Point Generator", PASSIVE, btn_medium_point_gen);
    public Upgrader largePointGen = new Upgrader(8, 1000, "Large Point Generator", PASSIVE, btn_large_point_gen);
    public Upgrader smallClickUpgrader = new Upgrader(1, 100, "Small Click Upgrader", ACTIVE, btn_click_upgrade);

    public Upgrades() {
    }

    public void checkPoints() {
        pointGenerators.forEach(Upgrader::update); //Checks if any point generator upgrades should be allowed
        clickUpgraders.forEach(Upgrader::update);//Checks if any click upgrader upgrades should be allowed
    }

    public void init() {
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
