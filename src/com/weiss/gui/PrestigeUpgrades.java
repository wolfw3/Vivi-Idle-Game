package com.weiss.gui;

import com.weiss.upgrades.ClickUpgrader;
import com.weiss.upgrades.PointGenerator;

import javax.swing.*;

import static com.weiss.Main.Upgrades;
import static com.weiss.Main.*;

public class PrestigeUpgrades {
    private JPanel panel;
    private JButton btn_autobuy_small_generator;
    private JButton btn_autobuy_small_click_upgrader;
    private JLabel lbl_prestige_points;
    private final JFrame frame = new JFrame("Prestige Upgrades");

    public PrestigeUpgrades() {
        Upgrades.smallPointGen.setAutobuyOptions(5, btn_autobuy_small_generator);
        Upgrades.smallClickUpgrader.setAutobuyOptions(8, btn_autobuy_small_click_upgrader);
    }

    public void checkPoints() {
        lbl_prestige_points.setText("Prestige Points: " + prestigePoints);
        for (PointGenerator pointGenerator : pointGenerators) {
            if (pointGenerator.hasAutobuy()) {
                if(pointGenerator.getAutobuy()) {
                    pointGenerator.getAutobuyButton().setEnabled(false);
                    pointGenerator.getAutobuyButton().setText("Autobuy " + pointGenerator.getName() + " - PURCHASED");
                } else {
                    pointGenerator.getAutobuyButton().setEnabled(prestigePoints >= pointGenerator.getAutobuyCost());
                    pointGenerator.getAutobuyButton().setText("Autobuy " + pointGenerator.getName() + " - " + pointGenerator.getAutobuyCost() + " Prestige Points");
                }
            }
        }
        for (ClickUpgrader clickUpgrader : clickUpgraders) {
            if (clickUpgrader.hasAutobuy()) {
                if(clickUpgrader.getAutobuy()) {
                    clickUpgrader.getAutobuyButton().setEnabled(false);
                    clickUpgrader.getAutobuyButton().setText("Autobuy " + clickUpgrader.getName() + " - PURCHASED");
                } else {
                    clickUpgrader.getAutobuyButton().setEnabled(prestigePoints >= clickUpgrader.getAutobuyCost());
                    clickUpgrader.getAutobuyButton().setText("Autobuy " + clickUpgrader.getName() + " - " + clickUpgrader.getAutobuyCost() + " Prestige Points");
                }
            }
        }
    }

    public void init() {
        lbl_prestige_points.setText("Prestige Points: " + prestigePoints);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
