package com.project.root.player;

import com.project.root.gameutilities.winvalidation.WinValidation;

import java.util.ArrayList;

/**
 * Represents a playing entity in this game. Can have losses, wins and points that increase while playing. Has a name
 * for identification.
 *
 * @author Christopher Hübner
 * @version 1.0 22.01.2021
 */
public class Player {
    /**
     * The name of the playing entity.
     */
    private final String name;
    /**
     * THe points.
     */
    private int points = 0;
    /**
     * An sorted list of the wins.
     */
    private ArrayList<WinValidation.Profit_Types> profits = new ArrayList<>();

    /**
     * The constructor.
     * Points are set by default to 0.
     * @param name The name for this entity.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Returns the current points.
     * @return The points of this player.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Adds points to the player.
     * @param points The points to be added.
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Returns a sorted list of the win types of this player.
     * @return Returns the sorted list of wins.
     */
    public ArrayList<WinValidation.Profit_Types> getProfits() {
        return profits;
    }

    /**
     * Adds a Profit_Type to the entity.
     * @param profit The profit type to be added.
     */
    public void addProfits(WinValidation.Profit_Types profit) {
        this.profits.add(profit);
    }

    /**
     * Returns the name.
     * @return The name of this entity.
     */
    public String getName() {
        return name;
    }
}