package com.weiss.upgrades;

import com.weiss.Main;

import javax.swing.*;

import static com.weiss.Main.*;

public class ClickUpgrader {
    private final int pointsPerClick;
    private final int initialCost;
    private int cost;
    private int count = 0;
    private final String name;
    private final JButton btn_buy;
    private JButton btn_autobuy;
    private int autobuyCost;
    private boolean hasAutobuy = false;
    private boolean autobuy = false;
    public ClickUpgrader(int pointsPerClick, int initialCost, String name, JButton connectedButton) {
        this.pointsPerClick = pointsPerClick;
        this.initialCost = initialCost;
        cost = initialCost;
        this.name = name;
        btn_buy = connectedButton;
        clickUpgraders.add(this);
        btn_buy.addActionListener(e -> buy(Upgrades.buyAmount));
    }

    public void buy(int amount) {
        for (int i = 0; i < amount; i++) {
            count++;
            points -= cost;
            cost *= 1.5;
            Main.update();
        }
    }

    public void update() {
        btn_buy.setEnabled(points >= getCost(Upgrades.buyAmount) * Upgrades.buyAmount); //Finds if the buy button should be enabled
        if(btn_buy.isEnabled() && autobuy) buy(1);
        btn_buy.setText(name + " - " + cost + " Points - x" + count); //Updates button texts' cost and count
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

    public void setAutobuy(boolean enabled) {
        autobuy = enabled;
    }

    public boolean getAutobuy() {
        return autobuy;
    }

    public void setCount(int number) { //Sets number of upgraders and loops through costs to find the correct amount
        count = number;
        cost = initialCost;
        for (int i = 0; i < count; i++) {
            cost *= 1.5;
        }
    }

    public void setAutobuyOptions(int cost, JButton button) {
        btn_autobuy = button;
        autobuyCost = cost;
        hasAutobuy = true;
        btn_autobuy.addActionListener(actionEvent -> {
            prestigePoints -= autobuyCost;
            setAutobuy(true);
            update();
        });
    }

    public int getAutobuyCost() {
        return autobuyCost;
    }

    public JButton getAutobuyButton() {
        return btn_autobuy;
    }

    public boolean hasAutobuy() {
        return hasAutobuy;
    }
}
