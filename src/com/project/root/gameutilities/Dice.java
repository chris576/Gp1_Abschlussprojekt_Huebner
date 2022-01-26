package com.project.root.gameutilities;

/**
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
     */
    public Dice() {
        toThrow();
    }

    public int getEye() {
        return eye;
    }

    /**
     *
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
     *
     */
    public void toThrow() {
        eye = (int) (Math.random() * 6 + 1);
    }
}
