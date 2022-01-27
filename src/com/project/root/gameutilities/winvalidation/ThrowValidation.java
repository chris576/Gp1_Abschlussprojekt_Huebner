package com.project.root.gameutilities.winvalidation;

import com.project.root.sources.Strings;

/**
 * A utility class used to validate if a throw is valid, or not. In further purposes it can be used to caculate the
 * reason, why the throw wasnt valid. Currently the only reason for a invalid throw is some where fallen down.
 *
 * @author Christopher Hübner
 * @version 1.0 15.01.2021
 */
public class ThrowValidation {

    /**
     * The propability at which the {@link DicesFallOfException} is thrown.
     * 10 percent.
     */
    private static double propability = 0.1;

    /**
     * If an {@link DicesFallOfException} is thrown or not is calculates separately from the exact number
     * of fallen down dices. Therefore the variable fallenDownDices can be non zero, while the method calculates
     * the throw as valid.
     *
     * @param diceNumber The number of dices to be thrown.
     * @throws DicesFallOfException Is thrown with a risk of 10 percent.
     */
    public static void validate(int diceNumber) throws DicesFallOfException {
        double rand = Math.random() + propability;
        int fallenDownDices = (int) Math.round((diceNumber - 1) * Math.random());
        if (rand > 1.00)
            throw new DicesFallOfException(Strings.GET_ERROR_FALLENOF(fallenDownDices), fallenDownDices);
    }
}
