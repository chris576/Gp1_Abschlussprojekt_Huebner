package com.project.root.gameutilities;

import com.project.root.gameutilities.error_reason.Reason;
import com.project.root.sources.Strings;

/**
 * @author Christopher Hübner
 * @version 1.0 15.01.2021
 */
public class ThrowValidation {

    /**
     * The propability at which the {@link InvalidThrowException} is thrown.
     * 10 percent.
     */
    private static double propability = 0.1;

    /**
     * If an {@link InvalidThrowException} is thrown or not is calculates separately from the exact number
     * of fallen down dices. Therefore the variable fallenDownDices can be non zero, while the method calculates
     * the throw as valid.
     *
     * @param diceNumber The number of dices to be thrown.
     * @throws InvalidThrowException Is thrown with a possibility of 10 percent.
     */
    public static void validate(int diceNumber) throws InvalidThrowException {
        double rand = Math.random() + propability;
        int fallenDownDices = (int) Math.round((diceNumber - 1) * Math.random());
        if (rand > 1.00)
            throw new InvalidThrowException(Reason.FALLENOF, Strings.GET_ERROR_FALLENOF(fallenDownDices));
    }
}
