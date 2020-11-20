package com.weiss;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static com.weiss.Main.*;

public class SaveManager {
    public static Properties data = new Properties();
    public static void init() {
        load();
    }
    public static void save() {
        data.setProperty("points", String.valueOf(points));
        data.setProperty("time", String.valueOf(System.currentTimeMillis()));
        clickUpgraders.forEach(clickUpgrader -> data.setProperty(clickUpgrader.getName() + " Count", String.valueOf(clickUpgrader.getCount())));
        pointGenerators.forEach(pointGenerator -> data.setProperty(pointGenerator.getName() + " Count", String.valueOf(pointGenerator.getCount())));
        try(FileWriter output = new FileWriter("save.properties")) {
            data.store(output, "Save Data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try(FileReader fileReader = new FileReader("save.properties")) {
            data.load(fileReader);
            points += Integer.parseInt(data.getProperty("points"));
            clickUpgraders.forEach(clickUpgrader -> clickUpgrader.setCount(Integer.parseInt(data.getProperty(clickUpgrader.getName() + " Count"))));
            pointGenerators.forEach(pointGenerator -> pointGenerator.setCount(Integer.parseInt(data.getProperty(pointGenerator.getName() + " Count"))));
            int timePassed = (int) ((System.currentTimeMillis() - Long.parseLong(data.getProperty("time"))) / 1000);
            int offlinePoints = timePassed * tick();
            if (offlinePoints > 0)
                JOptionPane.showMessageDialog(GUI.frame, "You have earned " + offlinePoints + " points while offline.");
            points += offlinePoints;
        } catch (IOException e) {
            System.out.println("No save data found!\nCreating new data...");
            save();
        }
    }
}
