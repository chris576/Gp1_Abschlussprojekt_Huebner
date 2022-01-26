package com.project.root.player;

import com.project.root.gameutilities.winvalidation.Profit_Types;

import java.util.ArrayList;

/**
 * @author Christopher Hübner
 * @version 1.0 22.01.2021
 */
public class Player {
    /**
     *
     */
    private final String name;
    /**
     *
     */
    private int points;
    /**
     *
     */
    private ArrayList<Profit_Types> profits;

    /**
     * @param name
     */
    public Player(String name) {
        this.name = name;
        profits = new ArrayList<>();
    }

    /**
     * @return
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * @return
     */
    public ArrayList<Profit_Types> getProfits() {
        return profits;
    }

    /**
     * @param profit
     */
    public void addProfits(Profit_Types profit) {
        this.profits.add(profit);
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }
}