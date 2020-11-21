package com.weiss;

import javax.swing.*;

import static com.weiss.Main.*;

public class GUI_Idle_Upgrades {
    public JFrame frame = new JFrame("Idle Upgrades");
    private JPanel panel;
    private JButton btnIdleTimeUpgrade;
    private JButton btnIdleTickUpgrade;
    public int idleTickSpeedUpgradeCount = 0;
    public int idleTimeUpgradeCount = 0;
    private int idleTickSpeedUpgradeCost = 750;
    private int idleTimeUpgradeCost = 500;

    public GUI_Idle_Upgrades() {
        btnIdleTimeUpgrade.addActionListener(e -> {
            points -= idleTimeUpgradeCost;
            idleTimeUpgradeCount += 1;
            idleTimeUpgradeCost *= 1.5;
            idleTime++;
            update();
        });
        btnIdleTickUpgrade.addActionListener(actionEvent -> {
            points -= idleTickSpeedUpgradeCost;
            idleTickSpeedUpgradeCount += 1;
            idleTickSpeedUpgradeCost *= 1.5;
            idleTickSpeed += 0.25;
            update();
        });
    }

    public void setIdleTickSpeedUpgradeCount(int number) {
        idleTickSpeedUpgradeCount = number;
        for (int i = 0; i < number; i++) {
            idleTickSpeed += 0.25;
            idleTickSpeedUpgradeCost *= 1.5;
        }
        update();
    }

    public void setIdleTimeUpgradeCount(int number) {
        idleTimeUpgradeCount = number;
        for (int i = 0; i < number; i++) {
            idleTime += 1;
            idleTimeUpgradeCost *= 1.5;
        }
        update();
    }

    public void checkPoints() {
        btnIdleTickUpgrade.setEnabled(points >= idleTickSpeedUpgradeCost);
        btnIdleTimeUpgrade.setEnabled(points >= idleTimeUpgradeCost);
        btnIdleTickUpgrade.setText("Idle point gain (" + (int) idleTickSpeed * 100 + "%) - " + idleTickSpeedUpgradeCost + " Points");
        btnIdleTimeUpgrade.setText("Max Idle Time (" + idleTime + " Hours) - " + idleTimeUpgradeCost + " Points");
    }

    public void init() {
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
