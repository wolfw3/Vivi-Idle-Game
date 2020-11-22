package com.weiss.gui;

import com.weiss.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.weiss.Main.*;

public class PrestigeUpgrades {
    private JPanel panel;
    private JButton btn_autobuy_small_generator;
    private JButton btn_autobuy_small_click_upgrader;
    private JLabel lbl_prestige_points;
    private final JFrame frame = new JFrame("Prestige Upgrades");

    public PrestigeUpgrades() {
        btn_autobuy_small_generator.addActionListener(actionEvent -> {
            Upgrades.smallPointGen.setAutobuy(true);
            prestigePoints -= 5;
        });
        btn_autobuy_small_click_upgrader.addActionListener(actionEvent -> {
            Upgrades.smallClickUpgrader.setAutobuy(true);
            prestigePoints -= 8;
        });
    }

    public void checkPoints() {
        btn_autobuy_small_generator.setEnabled(prestigePoints >= 5);
        btn_autobuy_small_click_upgrader.setEnabled(prestigePoints >= 8);
        if(Upgrades.smallPointGen.getAutobuy()) btn_autobuy_small_generator.setText("Autobuy Small Generators - PURCHASED");
        if(Upgrades.smallClickUpgrader.getAutobuy()) btn_autobuy_small_click_upgrader.setText("Autobuy Small Click Upgrader - PURCHASED");
    }

    public void init() {
        lbl_prestige_points.setText("Prestige Points: " + prestigePoints);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
