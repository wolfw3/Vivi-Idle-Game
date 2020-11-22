package com.weiss.upgrades;

import com.weiss.Main;

import javax.swing.*;

import static com.weiss.Main.*;

public class PointGenerator {
    private final int initialCost;
    private int cost;
    private final int pointsPerTick;
    private int count = 0;
    private final JButton btn_buy;
    private final String name;
    private boolean autobuy = false;

    public PointGenerator(int pointsPerTick, int initialCost, String name, JButton connectedButton) {
        this.pointsPerTick = pointsPerTick;
        cost = initialCost;
        this.initialCost = initialCost;
        this.name = name;
        btn_buy = connectedButton;
        pointGenerators.add(this);
        btn_buy.addActionListener(e -> buy(Upgrades.buyAmount));
    }

    public void buy(int amount) {
        for (int i = 0; i < amount; i++) {
            count++;
            points -= cost;
            cost *= 1.35;
            Main.update();
        }
    }

    public void setAutobuy (boolean enabled) {
        autobuy = enabled;
    }

    public boolean getAutobuy() {
        return autobuy;
    }

    public void update() {
        btn_buy.setEnabled(points >= getCost(Upgrades.buyAmount) * Upgrades.buyAmount);
        if(btn_buy.isEnabled() && autobuy) buy(1);
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

    public void setCount(int number) { //Sets number of generators and loops through costs to find the correct amount
        count = number;
        cost = initialCost;
        for (int i = 0; i < count; i++) {
            cost *= 1.35;
        }
    }

    public String getName() {
        return name;
    }
}