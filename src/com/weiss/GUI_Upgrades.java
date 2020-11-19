package com.weiss;

import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.weiss.Main.*;

public class GUI_Upgrades {

    public JFrame frame = new JFrame("Upgrades");
    public int buyAmount = 1;
    private JPanel panel;
    private JButton btn_small_point_gen;
    private JButton btn_medium_point_gen;
    private JButton btn_large_point_gen;
    public JButton btn_click_upgrade;
    public PointGenerator smallPointGen = new PointGenerator(1, 10, "Small Point Generator", btn_small_point_gen);
    public PointGenerator mediumPointGen = new PointGenerator(3, 100, "Medium Point Generator", btn_medium_point_gen);
    public PointGenerator largePointGen = new PointGenerator(8, 1000, "Large Point Generator", btn_large_point_gen);

    public int clickUpgradeCost = 100;
    private int clickUpgradeCount = 0;
    public GUI_Upgrades() {
        btn_click_upgrade.addActionListener(e -> {
            clickMultiplier += 1;
            clickUpgradeCost *= 1.5;
            btn_click_upgrade.setText("Click Upgrade - " + clickUpgradeCost + " Points - x" + ++clickUpgradeCount);
            update();
        });
    }

    public void checkPoints() {
        pointGenerators.forEach(PointGenerator::update);
    }

    public void init() {
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
