package com.project.root.gameutilities;

import com.project.root.gameutilities.winvalidation.DicesFallOfException;
import com.project.root.gameutilities.winvalidation.ThrowValidation;

import java.util.Arrays;

/**
 * The "Becher" is a class used to throw the dices.
 *
 * @author Christopher Hübner
 * @version 1.0 16.01.2021
 */
public class Mug {

    /**
     * The actual dices within this class.
     */
    private Dice[] dices;

    /**
     * Instanziates the Becher with {@param dice_amount} dices.
     *
     * @param dice_amount The amount of dices.
     */
    public Mug(int dice_amount) {
        initDiceArray(dice_amount);
    }

    /**
     * Inits with a dice number of 1.
     */
    public Mug() {
        initDiceArray(1);
    }

    /**
     * Initializes {@link #dices} with the parameter given amount.
     *
     * @param number The dice amount.
     */
    private void initDiceArray(int number) {
        dices = new Dice[number];
        for (int i = 0; i < number; i++) {
            dices[i] = new Dice();
        }
    }

    /**
     * Sets the amount of the dices. The dices are set by assigning a new dice array with the new length to
     * the {@link #dices} variable.
     *
     * @param amount The new dice amount.
     */
    public void setDiceQuantity(int amount) {
        initDiceArray(amount);
    }

    /**
     * Returns the eye on the given index.
     *
     * @param iw The index.
     * @return The eye on the given index.
     */
    public int getEye(int iw) {
        assert iw >= dices.length;
        return dices[iw].getEye();
    }

    /**
     * Returns the amount of dices.
     *
     * @return The amount of dices.
     */
    public int diceQuantity() {
        return dices.length;
    }

    /**
     * Throws any dices in the mug. If the exception is thrown, the dices dont change.
     * @throws DicesFallOfException Throws at a risk of 10 percent.
     */
    public void throwAny() throws DicesFallOfException {
        ThrowValidation.validate(dices.length);
        Arrays.stream(dices).forEach(Dice::toThrow);
    }

    /**
     * Prints all dices in the mug.
     */
    public void cliOutAll() {
        Arrays.stream(dices).forEach(Dice::output);
    }
}
