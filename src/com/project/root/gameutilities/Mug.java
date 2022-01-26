package com.project.root.gameutilities;

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
        this.dices = new Dice[dice_amount];
        putDicesToMug(dice_amount);
    }

    /**
     * Inits with a dice number of 1.
     */
    public Mug() {
        this.dices = new Dice[1];
        putDicesToMug(1);
    }

    /**
     * @param number
     */
    private void putDicesToMug(int number) {
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
        this.dices = new Dice[amount];
        putDicesToMug(amount);
    }

    public int getEye (int iw) {
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
     *
     */
    public void throwAny() throws InvalidThrowException {
        ThrowValidation.validate(dices.length);
        Arrays.stream(dices).forEach(Dice::toThrow);
    }

    public void cliOutAll () {
        Arrays.stream(dices).forEach(Dice::output);
    }
}
