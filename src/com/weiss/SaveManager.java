package com.weiss;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static com.weiss.Main.*;

public class SaveManager {
    public static Properties data = new Properties();

    public static void save() {
        data.setProperty("points", String.valueOf(points)); //Stores points
        data.setProperty("time", String.valueOf(System.currentTimeMillis())); //Stores current time to calculate idle progress
        clickUpgraders.forEach(clickUpgrader -> data.setProperty(clickUpgrader.getName() + " Count", String.valueOf(clickUpgrader.getCount()))); //Stores all ClickUpgraders
        pointGenerators.forEach(pointGenerator -> data.setProperty(pointGenerator.getName() + " Count", String.valueOf(pointGenerator.getCount()))); //Stores all PointGenerators
        try(FileWriter output = new FileWriter("save.properties")) { //Tries to acquire access to save.properties
            data.store(output, "Save Data"); //Stores properties in file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try(FileReader fileReader = new FileReader("save.properties")) { //Tries to read save.properties
            data.load(fileReader); //Copies properties into program
            points += Integer.parseInt(data.getProperty("points")); //Adds stored points to total
            clickUpgraders.forEach(clickUpgrader -> clickUpgrader.setCount(Integer.parseInt(data.getProperty(clickUpgrader.getName() + " Count")))); //Sets count of each ClickUpgrader
            pointGenerators.forEach(pointGenerator -> pointGenerator.setCount(Integer.parseInt(data.getProperty(pointGenerator.getName() + " Count")))); //Sets count of each PointGenerator
            int timePassed = (int) ((System.currentTimeMillis() - Long.parseLong(data.getProperty("time"))) / 1000); //Gets time passed since last game open to calculate idle progress
            int offlinePoints = timePassed * tick(); //Gets points earned offline
            if (offlinePoints > 0) //Checks if any points are earned offline
                JOptionPane.showMessageDialog(GUI.frame, "You have earned " + offlinePoints + " points while offline."); //Shows player how many points they've earned
            points += offlinePoints; //Adds idle points to total count
        } catch (IOException e) { //No save data found or not able to access
            System.out.println("No save data found!\nCreating new data...");
            save(); //Creates save file with default settings
        }
    }
}
