package com.weiss;

import javax.swing.*;

import static com.weiss.Main.*;
import static com.weiss.Main.update;

public class ClickUpgrader {
    private final int pointsPerClick;
    private final int initialCost;
    private int cost;
    private int count = 0;
    private final String name;
    private final JButton btn_buy;
    public ClickUpgrader(int pointsPerClick, int initialCost, String name, JButton connectedButton) {
        this.pointsPerClick = pointsPerClick;
        this.initialCost = initialCost;
        cost = initialCost;
        this.name = name;
        btn_buy = connectedButton;
        btn_buy.addActionListener(e -> {
            points -= cost;
            clickMultiplier += 1;
            cost *= 1.5;
            btn_buy.setText(name + " - " + cost + " Points - x" + ++count);
            Main.update();
        });
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

    private int getCost(int buyAmount) {
        return cost;
    }

    public int getPointsPerClick() {
        return pointsPerClick;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int number) {
        count = number;
        cost = initialCost;
        for (int i = 1; i <= count; i++) {
            cost *= 1.5;
        }
    }
}
