package com.weiss;

import javax.swing.*;

import static com.weiss.Main.*;

public class Upgrader {
    private final int initialCost;
    private int cost;
    private int autobuyCost;
    private final int points;
    private int count = 0;
    private final JButton btn_buy;
    private JButton btn_autobuy;
    private boolean hasAutobuy = false;
    private final String name;
    private boolean autobuy = false;
    public static int PASSIVE = 0;
    public static int ACTIVE = 1;

    public Upgrader(int points, int initialCost, String name, int type, JButton connectedButton) {
        this.points = points;
        cost = initialCost;
        this.initialCost = initialCost;
        this.name = name;
        btn_buy = connectedButton;
        if (type == 0) pointGenerators.add(this);
        else if (type == 1) clickUpgraders.add(this);
        btn_buy.addActionListener(e -> buy(Upgrades.buyAmount));
    }

    public void buy(int amount) {
        for (int i = 0; i < amount; i++) {
            count++;
            Main.points -= cost;
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
        if(Main.points >= getCost(Upgrades.buyAmount)) {
            btn_buy.setEnabled(true);
            if(autobuy) buy(1);
        } else btn_buy.setEnabled(false);
        btn_buy.setText(name + " - " + cost + " Points - x" + count);
    }

    public int getPoints() {
        return points;
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

    public String getName() {
        return name;
    }
}
