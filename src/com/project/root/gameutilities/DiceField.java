package com.project.root.gameutilities;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * This class encapsulates utility methods and classes that helps calculate and print dice fields on screen. Because
 * this is just a helping structure, any methods, fields and classes are static.
 * <p>
 * A side on a dice is thought on the model and print level as a 3 times 3 char double array. In this context I use the
 * "Matrix" as synonym for double char array. Any field on this char array is intended to have two states. It can be
 * the foreground in the printed painting, to symbol the number. Or it can be the background.
 * The background is a whitespace, the foreground a '*'.
 * <p>
 * This structure allows flexibility to any coder in symbolize values on this 3 times 3 matrix. The coder can
 * decide how to symbolize numbers on a dice on their self, without changing this class structure. Therefore the
 * question how to symbolize a number on a dice is independent from modeling that. I tried to follow the clean code
 * open closed principles.
 *
 * @author Christopher Hübner
 * @version 1.0 19.01.2021
 */
public class DiceField {

    /**
     * Its private because {@link DiceField} is a utility class.
     */
    private DiceField() {
    }

    /**
     * Returns a charMatrix in the dimension 3 times 3.
     * <p>
     * Asserts if more than 9 values or less than 1 values are given to positions.
     * Asserts if the x and y coordinates (position) is given twice.
     *
     * @return A double array (matrix) with dimension of 3 times 3.
     */
    public static char[][] getCharMatrix(MatrixPosition... positions) {
        char[][] charMatrix = new char[3][3];
        assert positions == null;
        assert positions.length > 9 || positions.length < 1;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                final int finalX = x;
                final int finalY = y;
                // Filters the {@param positions} for equality of the x and y coordinates that are set on the current
                // run.
                Stream<MatrixPosition> positionStream
                        = Arrays.stream(positions).filter(el -> el.x == finalX && el.y == finalY);
                assert positionStream.count() > 1;
                Optional<MatrixPosition> optPosition = positionStream.findFirst();
                if (optPosition.isPresent()) {
                    charMatrix[y][x] = '*';
                } else {
                    charMatrix[y][x] = ' ';
                }
            }
        }
        return charMatrix;
    }

    /**
     * Frames the three times three matrix from {@link #getCharMatrix(MatrixPosition...)} within a five time five
     * matrix, adding a border to it.
     *
     * @param matrix The matrix to frame with a border.
     * @return The framed matrix.
     */
    public static char[][] frameCharMatrix (char[][] matrix) {
        int height = matrix.length + 2;
        int width = matrix[0].length + 2;
        char[][] rtn_matrix = new char[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == 0 || y == height - 1) {
                    rtn_matrix[y][x] = '-';
                } else if (x == 0 || x == width - 1) {
                    rtn_matrix[y][x] = '|';
                } else {
                    rtn_matrix[y][x] = matrix[y - 1][x - 1];
                }
            }
        }
        return rtn_matrix;
    }

    /**
     * Encapsulates the x and y coordinates for a position within the three times three DiceFields Matrix.
     * Is a wrapper class for final int values.
     * Because the index of a array begins at 0, the maximum for x and y is 2.
     *
     * @author Christopher Hübner
     * @version 1.0 19.01.2021
     */
    public static class MatrixPosition {

        /**
         * The x coordinate.
         */
        private final int x;

        /**
         * The y coordinate.
         */
        private final int y;

        /**
         * Max values for x and y are 2, because the index of a double array begins at 0.
         * 0 is the first, 1 the second and 2 the third position.
         *
         * @param x The x coordinate.
         * @param y The y coordinate.
         */
        public MatrixPosition(int x, int y) {
            assert x > 2 || y > 2;
            this.x = x;
            this.y = y;
        }

        /**
         * Returns the x coordinate.
         *
         * @return The x coordinate.
         */
        public int getX() {
            return x;
        }

        /**
         * Returns the y coordinate.
         *
         * @return The y coordinate.
         */
        public int getY() {
            return y;
        }
    }

    /**
     * The Configuration for the print level of a three times three dice field.
     * Has different default configurations for the print level values of a dice with range from 1 to 6. The
     * configuration is saved within arrays or single {@link MatrixPosition}.
     *
     * For example {@link #CONF_ONE} defines how to display a "one" on a three times three dice field.
     *
     * @author Christopher Hübner
     * @version 1.0.0 19.01.2021
     */
    public static class DiceFieldConfiguration {

        /**
         * Because this class has only static fields and methods, the constructor is private.
         */
        private DiceFieldConfiguration() {
        }

        /**
         * The matrix position within a three times three matrix to symbolize a one.
         */
        public static final MatrixPosition CONF_ONE = new MatrixPosition(1, 1);

        /**
         * The matrix position within a three times three matrix to symbolize a two.
         */
        public static final MatrixPosition[] CONF_TWO = {
                new MatrixPosition(1, 0), new MatrixPosition(1, 2)
        };

        /**
         * The matrix position within a three times three matrix to symbolize a three.
         */
        public static final MatrixPosition[] CONF_THREE = {
                new MatrixPosition(1, 0), new MatrixPosition(1, 1), new MatrixPosition(1, 2)
        };

        /**
         * The matrix position within a three times three matrix to symbolize a four.
         */
        public static final MatrixPosition[] CONF_FOUR = {
                new MatrixPosition(0, 0), new MatrixPosition(2, 0), new MatrixPosition(0, 2),
                new MatrixPosition(2, 2)
        };

        /**
         * The matrix position within a three times three matrix to symbolize a five.
         */
        public static final MatrixPosition[] CONF_FIVE = {
                new MatrixPosition(0, 0), new MatrixPosition(2, 0), new MatrixPosition(1, 1),
                new MatrixPosition(0, 2), new MatrixPosition(2, 2)
        };

        /**
         * The matrix position within a three times three matrix to symbolize a six.
         */
        public static final MatrixPosition[] CONF_SIX = {
                new MatrixPosition(0, 0), new MatrixPosition(2, 0), new MatrixPosition(0, 1),
                new MatrixPosition(2, 1), new MatrixPosition(0, 2), new MatrixPosition(2, 2)
        };
    }
}
