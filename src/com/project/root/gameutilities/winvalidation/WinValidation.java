package com.project.root.gameutilities.winvalidation;

import com.project.root.gameutilities.Mug;

import java.util.Arrays;

/**
 *
 */
public class WinValidation {

    /**
     *
     */
    private WinValidation() {
    }

    /**
     *
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
     * @param eyes
     * @return
     */
    public static Win checkForPasch(int[] eyes) {
        // Step 1: Check for pasch.
        boolean pasch = true;
        for (int first : eyes) {
            for (int second : eyes) {
                if (first != second) {
                    pasch = false;
                    break;
                }
            }
        }
        // Step 2: Calculate points.
        int points = pasch ? eyes[0] * 100 : 0;
        Profit_Types type = pasch ? Profit_Types.PASCH : Profit_Types.NONE;
        return new Win(points, type);
    }

    /**
     * @param eyes
     * @return
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
     * @param eyes
     * @return
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
        Profit_Types type = (greates_multi > 0) ? Profit_Types.Pair : Profit_Types.NONE;
        return new Win(points, type);
    }

    /**
     * @param eyes
     * @return
     */
    public static Win sumEyes(int[] eyes) {
        int points = 0;
        for (int e : eyes) {
            points = points + e;
        }
        return new Win(points, Profit_Types.NONE);
    }

    /**
     *
     */
    public static class Win {
        private final int point;
        private final Profit_Types profit;

        public Win(int point, Profit_Types profit) {
            this.point = point;
            this.profit = profit;
        }

        public int getPoint() {
            return point;
        }

        public Profit_Types getProfit() {
            return profit;
        }
    }
}
