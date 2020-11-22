package com.weiss.gui;

import com.weiss.upgrades.ClickUpgrader;
import com.weiss.upgrades.PointGenerator;

import javax.swing.*;

import static com.weiss.Main.*;

public class Upgrades {

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
    public ClickUpgrader smallClickUpgrader = new ClickUpgrader(1, 100, "Small Click Upgrader", btn_click_upgrade);

    public Upgrades() {
    }

    public void checkPoints() {
        pointGenerators.forEach(PointGenerator::update); //Checks if any point generator upgrades should be allowed
        clickUpgraders.forEach(ClickUpgrader::update);//Checks if any click upgrader upgrades should be allowed
    }

    public void init() {
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
