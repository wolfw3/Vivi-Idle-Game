package com.weiss;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static ArrayList<PointGenerator> pointGenerators = new ArrayList<>();
    public static ArrayList<ClickUpgrader> clickUpgraders = new ArrayList<>();
    public static int points = 0;
    public static int tickSpeed = 2; //Ticks per second
    public static int clickMultiplier = 1;
    public static Timer tickTimer = new Timer("Tick");
    public static Timer saveTimer = new Timer("Autosave");
    public static GUI_Main GUI = new GUI_Main();
    public static GUI_Idle_Upgrades GUI_Idle_Upgrades = new GUI_Idle_Upgrades();
    public static GUI_Upgrades GUI_Upgrades = new GUI_Upgrades();
    public static int tick() {
        int addedPoints = pointGenerators.stream().mapToInt(pointGenerator -> pointGenerator.getCount() * pointGenerator.getPointsPerTick()).sum();
        points += addedPoints;
        update();
        return addedPoints;
    }
    public static void update() {
        GUI.setPoints();
        GUI_Upgrades.checkPoints();
    }
    public static void main(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case "reset" -> SaveManager.save();
                case "cheatMode" -> points += 1000000;
            }
        }
        SaveManager.init();
        GUI.init();
        tickTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        }, 0, (1000L / tickSpeed));
        saveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SaveManager.save();
            }
        }, 0, 5000L);
    }
}
