package com.weiss;

import javax.swing.*;


import static com.weiss.Main.*;

public class GUI_Upgrades {

    public JFrame frame = new JFrame("Upgrades");
    public int buyAmount = 1;
    private JPanel panel;
    public JButton btn_small_point_gen;
    private JButton btn_medium_point_gen;
    public PointGenerator smallPointGen = new PointGenerator(1, 10, "Small Point Generator", btn_small_point_gen);
    public PointGenerator mediumPointGen = new PointGenerator(3, 100, "Medium Point Generator", btn_medium_point_gen);

    public GUI_Upgrades() {
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
