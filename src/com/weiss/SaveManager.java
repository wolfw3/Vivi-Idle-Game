package com.weiss;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static com.weiss.Main.*;
import static java.lang.String.valueOf;

public class SaveManager {
    public static Properties data = new Properties();

    public static void save() {
        data.setProperty("points", valueOf(points)); //Stores points
        data.setProperty("time", valueOf(System.currentTimeMillis())); //Stores current time to calculate idle progress
        data.setProperty("idleTimeUpgradeCount", valueOf(IdleUpgrades.idleTimeUpgradeCount));
        data.setProperty("idleTickSpeedUpgradeCount", valueOf(IdleUpgrades.idleTickSpeedUpgradeCount));
        data.setProperty("prestigeCount", valueOf(prestigeCount));
        data.setProperty("prestigePoints", valueOf(prestigePoints));
        data.setProperty("autobuySmallGenerator", valueOf(Upgrades.smallPointGen.getAutobuy()));
        data.setProperty("autobuySmallClickUpgrader", valueOf(Upgrades.smallClickUpgrader.getAutobuy()));
        clickUpgraders.forEach(clickUpgrader -> data.setProperty(clickUpgrader.getName() + " Count", valueOf(clickUpgrader.getCount()))); //Stores all ClickUpgraders
        pointGenerators.forEach(pointGenerator -> data.setProperty(pointGenerator.getName() + " Count", valueOf(pointGenerator.getCount()))); //Stores all PointGenerators
        try(FileWriter output = new FileWriter("save.properties")) { //Tries to acquire access to save.properties
            data.store(output, "Save Data"); //Stores properties in file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            FileReader fileReader = new FileReader("save.properties"); //Tries to read save.properties
            data.load(fileReader); //Copies properties into program
            points += Integer.parseInt(data.getProperty("points")); //Adds stored points to total
            IdleUpgrades.setIdleTimeUpgradeCount(Integer.parseInt(data.getProperty("idleTimeUpgradeCount")));
            IdleUpgrades.setIdleTickSpeedUpgradeCount(Integer.parseInt(data.getProperty("idleTickSpeedUpgradeCount")));
            clickUpgraders.forEach(clickUpgrader -> clickUpgrader.setCount(Integer.parseInt(data.getProperty(clickUpgrader.getName() + " Count")))); //Sets count of each ClickUpgrader
            pointGenerators.forEach(pointGenerator -> pointGenerator.setCount(Integer.parseInt(data.getProperty(pointGenerator.getName() + " Count")))); //Sets count of each Upgrader
            int timePassed = (int) ((System.currentTimeMillis() - Long.parseLong(data.getProperty("time"))) / 1000); //Gets time passed since last game open to calculate idle progress
            if (timePassed > idleTime * 3600) timePassed = idleTime * 3600; //If more time has passed than the max idle time, sets it to that
            int offlinePoints = (int) (timePassed * tick() * tickSpeed * idleTickSpeed); //Gets points earned offline
            if (offlinePoints > 0) //Checks if any points are earned offline
                JOptionPane.showMessageDialog(GUI_Main.frame, "You have earned " + offlinePoints + " points while offline."); //Shows player how many points they've earned
            points += offlinePoints; //Adds idle points to total count
            prestigeCount = Integer.parseInt(data.getProperty("prestigeCount"));
            prestigePoints = Integer.parseInt(data.getProperty("prestigePoints"));
            Upgrades.smallPointGen.setAutobuy(Boolean.parseBoolean(data.getProperty("autobuySmallGenerator")));
            Upgrades.smallClickUpgrader.setAutobuy(Boolean.parseBoolean(data.getProperty("autobuySmallClickUpgrader")));
        } catch (IOException error) { //No save data found or not able to access
            System.out.println("No save data found!\nCreating new data...");
            save(); //Creates save file with default settings
        } catch (NumberFormatException error) {
            error.printStackTrace();
            System.out.println("Save is outdated! Trying to load anyways.");
            save();
        }
    }
}
