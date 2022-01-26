package com.project.root.gameutilities.winvalidation;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

/**
 * Because my {@link WinValidation} algorithms doesnt work correctly and reliable, I have written a test class to
 * test the state of the reliability of my algorithms. Any changes on these methods shall calculate better results.
 * Those unit test have been written to ensure that.
 */
class WinValidationTest extends TestCase {

    @Test
    void validate() {
    }

    @Test
    void checkForPasch() {
        int[] eyes1 = {1, 1, 1, 1};
        WinValidation.Win win1 = WinValidation.checkForPasch(eyes1);
        assert win1.getProfit() == Profit_Types.PASCH;
        assert win1.getPoint() == 100;

        int[] eyes2 = {2, 2, 3, 4};
        WinValidation.Win win2 = WinValidation.checkForPasch(eyes2);
        assert win2.getPoint() == 0;
        assert win2.getProfit() == Profit_Types.NONE;
    }

    @Test
    void checkForStreet() {
        int[] eyes1 = {1, 2, 3, 5, 6};

    }

    @Test
    void checkForPair() {
        int[] eyes1 = {5, 4, 6, 6, 4};
        WinValidation.Win win = WinValidation.checkForPair(eyes1);
        assert win.getPoint() == 60;
        assert win.getProfit() == Profit_Types.Pair;

        int[] eyes2 = {5, 3, 3, 5};
        WinValidation.Win win2 = WinValidation.checkForPair(eyes2);
        assert win2.getProfit() == Profit_Types.Pair;
        assert win2.getPoint() == 50;

        int[] eyes3 = {2, 3, 4, 5, 2};
        WinValidation.Win win3 = WinValidation.checkForPair(eyes3);
        assertEquals(win3.getPoint(), 20);
        assertEquals(win3.getProfit(), Profit_Types.Pair);

        int[] eyes4 = {1, 2, 3, 6};
        WinValidation.Win win4 = WinValidation.checkForPair(eyes4);
        assert win4.getPoint() == 0;
        assert win4.getProfit() == Profit_Types.NONE;
    }

    @Test
    void sumEyes() {
        int[] eyes1 = {1, 2, 3};
        WinValidation.Win win1 = WinValidation.sumEyes(eyes1);
        assert Profit_Types.NONE == win1.getProfit();
        assert win1.getPoint() == 6;

        int[] eyes2 = {3, 3, 4, 6};
        WinValidation.Win win2 = WinValidation.sumEyes(eyes2);
        assert Profit_Types.NONE == win2.getProfit();
        assert win2.getPoint() == 16;
    }
}