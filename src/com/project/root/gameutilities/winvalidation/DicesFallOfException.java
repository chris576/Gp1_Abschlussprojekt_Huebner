package com.project.root.gameutilities.winvalidation;

/**
 * This is an Exception that should be thrown when an invalid throw has been thrown. It encapsulates data of
 * of the invalid throw, such as the error message, the amount of missed
 *
 * @author Christopher Hübner
 * @version 1.0.0 16.01.2021
 */
public class DicesFallOfException extends Exception {

    /**
     * The amount of fallen down dices.
     */
    private int amount;

    /**
     * The constructor for this exception.
     *
     * @param amount  The amount of fallen down dices.
     * @param message The error message that should be displayed on the command line when executed.
     */
    public DicesFallOfException(String message, int amount) {
        super(message);
        this.amount = amount;
    }

    /**
     * Returns the amount of fallen down dices.
     *
     * @return Returns the amount of fallen down dices.
     */
    public int amount() {
        return amount;
    }
}