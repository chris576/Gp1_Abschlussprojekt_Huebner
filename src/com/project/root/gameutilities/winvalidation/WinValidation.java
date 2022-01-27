package com.project.root.gameutilities.winvalidation;

import com.project.root.gameutilities.Mug;

import java.util.Arrays;

/**
 * A utility class to validate the win of a throw.
 * Because the class itself does not store or encapsulate any values, the constructor is private and its public methods
 * static.
 * <p>
 * Due {@link Win} is just used in the context of the validation of a win, its a static inner class of
 * {@link WinValidation}
 */
public class WinValidation {

    /**
     * Because the class itself does not store or encapsulate any values, the constructor is private.
     */
    private WinValidation() {
    }

    /**
     * Validates the win of a throw of a mug. The algorithm checks whether the mug has a pasch, street or a pair. The
     * sum of the eyes are calculated as well.
     */
    public static Win validate(Mug mug) {
        int[] eyes = new int[mug.diceQuantity()];
        for (int i = 0; i < mug.diceQuantity(); i++) {
            eyes[i] = mug.getEye(i);
        }
        Win pasch = checkForPasch(eyes);
        Win street = checkForStreet(eyes);
        Win pair = checkForPair(eyes);
        Win other = sumEyes(eyes);

        boolean paschMost = pasch.point > street.point && pasch.point > pair.point;
        boolean streetMost = street.point > pasch.point && street.point > pair.point;
        boolean pairMost = pair.point > pasch.point && pair.point > street.point;

        if (paschMost) {
            return pasch;
        }

        if (pairMost) {
            return pair;
        }

        if (streetMost) {
            return street;
        }
        return other;
    }

    /**
     * Checks whether any elements within {@param eyes} are equal.
     *
     * @param eyes An array of integers. Must be not null.
     * @return Returns a win object with eye * 100 and {@link Profit_Types#PASCH},
     * or 0 points with {@link Profit_Types#NONE}.
     */
    public static Win checkForPasch(int[] eyes) {
        boolean pasch = true;
        for (int first : eyes) {
            for (int second : eyes) {
                if (first != second) {
                    pasch = false;
                    break;
                }
            }
        }
        int points = pasch ? eyes[0] * 100 : 0;
        Profit_Types type = pasch ? Profit_Types.PASCH : Profit_Types.NONE;
        return new Win(points, type);
    }

    /**
     * Checks whether minimum 3 integers within {@param eyes} build a series.
     *
     * @param eyes An array of integers. Must be not null.
     * @return Returns a win object with 100 points and {@link Profit_Types#STREET},
     * or 0 points with {@link Profit_Types#NONE}.
     */
    public static Win checkForStreet(int[] eyes) {
        int[] array = eyes.clone();
        Arrays.sort(array);
        for (int i = 0; i < eyes.length - 1; i++) {
            if (array[i] == array[i + 1]) {
                array[i] = 0;
            }
        }
        Arrays.sort(array);
        int counter = 0;
        int street = array[0];
        for (int e : array) {
            if (street + 1 == e) {
                counter++;
            } else if (counter < 2) {
                counter = 0;
            }
            street = e;
            if (counter >= 2) {
                break;
            }
        }
        int points = (counter > 1) ? 100 : 0;
        Profit_Types type = (counter > 1) ? Profit_Types.STREET : Profit_Types.NONE;
        return new Win(points, type);
    }

    /**
     * Checks whether minimum 2 integers within {@param eyes} exist twice.
     *
     * @param eyes An array of integers. Must be not null.
     * @return Returns a win object with 100 points and {@link Profit_Types#PAIR},
     * or 0 points with {@link Profit_Types#NONE}.
     */
    public static Win checkForPair(int[] eyes) {
        int[] array = eyes.clone();
        Arrays.sort(array);
        //Step 1. Find the highest number that is minimum twice in eyes.
        int greates_multi = 0;
        for (int i = 0; i < array.length; i++) {
            if (i + 1 < array.length && array[i] == array[i + 1]) {
                greates_multi = array[i];
            }
        }
        //Step 2. Calculate the points.
        int points = greates_multi * 10;
        Profit_Types type = (greates_multi > 0) ? Profit_Types.PAIR : Profit_Types.NONE;
        return new Win(points, type);
    }

    /**
     * Returns the sum of {@param eyes}
     *
     * @param eyes The array which sum is to be returned.
     * @return The sum of the array.
     */
    public static Win sumEyes(int[] eyes) {
        int points = 0;
        for (int e : eyes) {
            points = points + e;
        }
        return new Win(points, Profit_Types.NONE);
    }

    /**
     * Encapsulates a the data for a win, such as the points and the type of win ({@link Profit_Types}).
     * Objects of this class are immutable, therefore data can just be written to a object at init time.
     *
     * @version 1.0 25.01.2021
     * @author Christopher Hübner
     */
    public static class Win {

        /**
         * The points of a win.
         */
        private final int point;

        /**
         * The type of the profit.
         */
        private final Profit_Types profit;

        /**
         * The constructor of this class.
         * @param point The points associated with the win.
         * @param profit The type of profit.
         */
        public Win(int point, Profit_Types profit) {
            this.point = point;
            this.profit = profit;
        }

        /**
         * Returns the points of the win.
         * @return Returns the points of the win.
         */
        public int getPoint() {
            return point;
        }

        /**
         * Returns the associated type of profit.
         * @return The type of profit as enum.
         */
        public Profit_Types getProfit() {
            return profit;
        }
    }

    /**
     * Encapsulates the different win types.
     *
     * @version 1.0.0 20.01.2021
     * @author Christopher Hübner
     */
    public enum Profit_Types {
        /**
         * Illegal throw.
         */
        ILLEGAL,
        /**
         * No special win.
         */
        NONE,
        /**
         * A street.
         */
        STREET,
        /**
         * A pasch.
         */
        PASCH,
        /**
         * A pair.
         */
        PAIR
    }
}
