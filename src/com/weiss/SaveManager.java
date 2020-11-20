package com.weiss;

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
        try(FileWriter output = new FileWriter("data/save.properties")) {
            data.store(output, "Save Data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        points = Integer.parseInt(data.getProperty("points"));
        clickUpgraders.forEach(clickUpgrader -> clickUpgrader.setCount(Integer.parseInt(data.getProperty(clickUpgrader.getName() + " Count"))));
        pointGenerators.forEach(pointGenerator -> pointGenerator.setCount(Integer.parseInt(data.getProperty(pointGenerator.getName() + " Count"))));
        int timePassed = (int) (System.currentTimeMillis() - Long.parseLong(data.getProperty("time")) / 1000);
        points += timePassed * tick();
        try(FileReader fileReader = new FileReader("data/save.properties")) {
            data.load(fileReader);
        } catch (IOException e) {
            System.out.println("No save data found!\nCreating new data...");
            save();
        }
    }
}
