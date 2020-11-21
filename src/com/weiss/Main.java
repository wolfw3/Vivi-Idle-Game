package com.weiss;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static ArrayList<PointGenerator> pointGenerators = new ArrayList<>();
    public static ArrayList<ClickUpgrader> clickUpgraders = new ArrayList<>();
    public static int points = 0;
    public static int tickSpeed = 2; //Ticks per second
    public static Timer tickTimer = new Timer("Tick");
    public static Timer saveTimer = new Timer("Autosave");
    public static GUI_Main GUI = new GUI_Main();
    public static GUI_Idle_Upgrades GUI_Idle_Upgrades = new GUI_Idle_Upgrades();
    public static GUI_Upgrades GUI_Upgrades = new GUI_Upgrades();

    public static int tick() { //Gets passive point gain and adds to total
        int addedPoints = pointGenerators.stream().mapToInt(pointGenerator -> pointGenerator.getCount() * pointGenerator.getPointsPerTick()).sum(); //Gets passive points gained
        points += addedPoints; //Adds points to total
        update(); //Updates info on GUI
        return addedPoints; //Returns the amount of points added, used for idle progress calculation
    }
    public static void update() {
        GUI.setPoints(); //Sets GUI points label to current total
        GUI_Upgrades.checkPoints(); //Checks if any upgrades should be allowed to buy
    }
    public static void main(String[] args) {
        try {
            for (String arg : args) {
                switch (arg) {
                    case "reset":
                        SaveManager.save();
                        break;
                    case "cheatMode":
                        points += 1000000;
                        break;
                    default:
                        throw new IllegalArgumentException("Illegal argument: " + arg);
                }
            }
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
        SaveManager.load(); //Loads data or creates new file if none exists
        GUI.init(); //Starts the game GUI
        tickTimer.scheduleAtFixedRate(new TimerTask() { //Passive point calculation
            @Override
            public void run() {
                tick();
            }
        }, 0, (1000L / tickSpeed));
        saveTimer.scheduleAtFixedRate(new TimerTask() { //Autosave timer
            @Override
            public void run() {
                SaveManager.save();
            }
        }, 0, 5000L);
    }
}
