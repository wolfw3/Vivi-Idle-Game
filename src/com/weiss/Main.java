package com.weiss;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static ArrayList<PointGenerator> pointGenerators = new ArrayList<>();
    public static int points = 0;
    public static int tickSpeed = 2; //Ticks per second
    public static int clickMultiplier = 1;
    public static Timer tickTimer = new Timer("Tick");
    public static GUI_Main GUI = new GUI_Main();
    public static GUI_Idle_Upgrades GUI_Idle_Upgrades = new GUI_Idle_Upgrades();
    public static GUI_Upgrades GUI_Upgrades = new GUI_Upgrades();
    public static void tick() {
        pointGenerators.forEach(pointGenerator -> points += pointGenerator.getCount() * pointGenerator.getPointsPerTick());
        update();
    }
    public static void update() {
        GUI.setPoints();
        GUI_Upgrades.checkPoints();
        GUI_Upgrades.btn_click_upgrade.setEnabled(points >= GUI_Upgrades.clickUpgradeCost);
    }
    public static void main(String[] args) {
        tickTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        }, 0, (1000L / tickSpeed));
        GUI.init();
    }
}
