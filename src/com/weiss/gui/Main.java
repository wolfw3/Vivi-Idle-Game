package com.weiss.gui;

import com.weiss.SaveManager;

import javax.swing.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.weiss.Main.IdleUpgrades;
import static com.weiss.Main.PrestigeUpgrades;
import static com.weiss.Main.Upgrades;
import static com.weiss.Main.*;

public class Main {

    private JButton btn_click;
    private JPanel panel;
    private JLabel lbl_points;
    private JButton btn_upgrades;
    private JButton btn_idle_upgrades;
    private JButton btn_exit;
    private JButton btn_prestige;
    private JButton btn_prestige_upgrades;
    private JLabel lbl_news_ticker;
    public JFrame frame = new JFrame("Clicker");
    private final String[] news = {
            "Breaking News: Points Generated after 2020 subject to taxes",
            "Keep on clicking!",
            "Made by Vivian!",
            "Stats coming soon!",
            "Vivian#9120 on Discord!",
            "You're welcome for the arthritis!",
            "Have you prestiged yet?",
            "Barely functioning saves!"
    };

    public Main() {
        btn_click.addActionListener(actionEvent -> {
            clickUpgraders.forEach(clickUpgrader -> points += clickUpgrader.getCount() * clickUpgrader.getPoints()); //Adds upgraders' bonus points
            points++; //Base click
            update();
        });
        btn_upgrades.addActionListener(e -> Upgrades.init()); //Opens upgrade menu on button click
        btn_idle_upgrades.addActionListener(e -> IdleUpgrades.init()); //Opens idle upgrade menu on button click
        btn_exit.addActionListener(actionEvent -> {
            SaveManager.save();
            System.exit(1);
        });
        btn_prestige.setVisible(false);
        btn_prestige_upgrades.setVisible(false);
        btn_prestige.addActionListener(actionEvent -> {
            if(JOptionPane.showConfirmDialog(frame, "Are you sure you'd like to prestige?\nYou will gain " + pendingPrestigePoints + " Prestige Points") == JOptionPane.YES_OPTION) {
                points = 0;
                prestigePoints += pendingPrestigePoints;
                prestigeCount++;
                pendingPrestigePoints = 0;
                pointGenerators.forEach(pointGenerator -> pointGenerator.setCount(0));
                clickUpgraders.forEach(clickUpgrader -> clickUpgrader.setCount(0));
                IdleUpgrades.setIdleTickSpeedUpgradeCount(0);
                IdleUpgrades.setIdleTimeUpgradeCount(0);
            }
        });
        btn_prestige_upgrades.addActionListener(actionEvent -> PrestigeUpgrades.init());
    }

    private void updateNewsTicker() {
        String newsToSet = news[new Random().nextInt(news.length)];
        lbl_news_ticker.setText(newsToSet);
        Timer newsTimer = new Timer("News Ticker");
        newsTimer.schedule(new TimerTask() { //Autosave timer
            @Override
            public void run() {
                updateNewsTicker();
            }
        }, newsToSet.length() * 500);
    }

    public void checkPrestige() {
        pendingPrestigePoints = 0;
        int points = com.weiss.Main.points;
        for (int pointCost = 10000; pointCost <= points; pointCost *= 2) {
            pendingPrestigePoints++;
            points -= pointCost;
        }
        btn_prestige.setVisible(pendingPrestigePoints > 0);
        btn_prestige.setText("Prestige - Resets ALL Points - " + pendingPrestigePoints + " Prestige Points");
        btn_prestige_upgrades.setVisible(prestigePoints > 0);
    }

    public void setPoints() {
        this.lbl_points.setText(String.valueOf(points)); //Updates GUI points label
    }

    public void init() {
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        updateNewsTicker();
    }
}
