package com.project.root.gameutilities;

/**
 * A class to represent a dice in the game.
 *
 * @author Christopher Hübner
 * @version 1.0 15.01.2021
 */
public class Dice {

    /**
     * The eyes.
     */
    private int eye;

    /**
     * The constructor to init the dice with an maximum eye number of 6 and a minimum of 1.
     * Invokes {@link #toThrow()}.
     */
    public Dice() {
        toThrow();
    }

    /**
     * Returns the eye.
     *
     * @return The number of the eye.
     */
    public int getEye() {
        return eye;
    }

    /**
     * Outputs the dice to the console by following these steps.
     * <ul>
     *     <li>Calculate a three times three matrix representing the eye count on cli print level by using
     *     {@link DiceField#getCharMatrix(DiceField.MatrixPosition...)}
     *     </li>
     *     <li>Wrapping the matrix within a 5 times 5 matrix. The number is in the center of the new matrix, around
     *     a border. {@link DiceField#frameCharMatrix(char[][])}</li>
     *     <li>Output the frame matrix to the console.</li>
     * </ul>
     */
    public void output() {
        char[][] matrix = new char[3][3];
        if (eye == 1) {
            matrix = DiceField.getCharMatrix(DiceField.DiceFieldConfiguration.CONF_ONE);
        } else if (eye == 2) {
            matrix = DiceField.getCharMatrix(DiceField.DiceFieldConfiguration.CONF_TWO);
        } else if (eye == 3) {
            matrix = DiceField.getCharMatrix(DiceField.DiceFieldConfiguration.CONF_THREE);
        } else if (eye == 4) {
            matrix = DiceField.getCharMatrix(DiceField.DiceFieldConfiguration.CONF_FOUR);
        } else if (eye == 5) {
            matrix = DiceField.getCharMatrix(DiceField.DiceFieldConfiguration.CONF_FIVE);
        } else if (eye == 6) {
            matrix = DiceField.getCharMatrix(DiceField.DiceFieldConfiguration.CONF_SIX);
        }
        char[][] framed_matrix = DiceField.frameCharMatrix(matrix);
        for (int y = 0; y < framed_matrix.length; y++) {
            for (int x = 0; x < framed_matrix[0].length; x++) {
                System.out.print(framed_matrix[y][x]);
            }
            System.out.print('\n');
        }
    }

    /**
     * Throws the dice and calculates a random number between 1 and 6 to assign it to {@link #eye}.
     */
    public void toThrow() {
        eye = (int) (Math.random() * 6 + 1);
    }
}
