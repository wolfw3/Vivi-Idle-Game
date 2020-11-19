package com.weiss;

import javax.swing.*;

import static com.weiss.Main.*;

public class PointGenerator {
    private int cost;
    private final int pointsPerTick;
    private int count = 0;
    private final JButton btn_buy;
    private final String name;
    public PointGenerator(int pointsPerTick, int initialCost, String name, JButton connectedButton) {
        this.pointsPerTick = pointsPerTick;
        cost = initialCost;
        this.name = name;
        btn_buy = connectedButton;
        pointGenerators.add(this);
        btn_buy.addActionListener(e -> buy(GUI_Upgrades.buyAmount));
    }
    public void buy(int amount) {
        for (int i = 0; i < amount; i++) {
            count++;
            points -= cost;
            cost *= 1.35;
            Main.update();
        }
    }

    public void update() {
        btn_buy.setEnabled(points >= (getCost(GUI_Upgrades.buyAmount) * GUI_Upgrades.buyAmount));
        btn_buy.setText(name + " - " + cost + " Points - x" + count);
    }

    public int getPointsPerTick() {
        return pointsPerTick;
    }

    public int getCost(int amount) {
        return cost;
    }

    public int getCount() {
        return count;
    }


    public String getName() {
        return name;
    }
}
