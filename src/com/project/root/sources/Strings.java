package com.project.root.sources;

import com.project.root.gameutilities.winvalidation.WinValidation;
import com.project.root.player.Player;

/**
 * A utility class to store string resources used in the cli.
 * <p>
 * Because its a utility class, the constructor is set to private.
 *
 * @author Christopher Hübner
 * @version 1.0.0 20.01.2021
 */
public class Strings {

    /**
     * Private constructor to disable instantiation.
     */
    private Strings() {
    }

    /**
     *
     */
    public static final String OUT_GREETING = "Welcome to the decades best dice cli game.";

    /**
     *
     */
    public static final String OUT_PLAYERINQUIRI = "Please type in the amount of players that want to play the game. " +
            "\nMinimum 1 players must be selected. Maximum 10 players are allowed.";

    /**
     *
     */
    public static final String OUT_DEMNAND = "Sure? (0 / 1)";

    /**
     *
     */
    public static final String OUT_NUMBERINQUIRI = "Please type in the amount of dices you want to play with.";

    /**
     *
     */
    public static final String OUT_OPTIONS_START = "1. Start the game. \n2. End the game. \n3. Print the rules.";

    /**
     *
     */
    public static final String OUT_OPTIONS = "1. Play. \n2. End the game. \n3. Print the rules.";

    /**
     *
     */
    public static final String OUT_DICECOUNT = "Please type in the number of dices you want to play with.\n" +
            "Maximum is 10, minimum 3.";

    /**
     *
     */
    public static final String OUT_REQUESTOPTSELECT = "Please select your favour option.\nOnly submit the number " +
            "of " + "the option.";


    public static final String OUT_FINALMESSAGE = "The game has beend finished.\nThank your for playing.";

    /**
     *
     */
    public static final String ERROR_OPTION = "Please input a correct option.";

    /**
     *
     */
    public static final String ERROR_INTOPTION = "Please input a integer.";

    /**
     *
     */
    public static String GET_STARTMESAGE (int players, int dices) {
        return "You are playing with " + players + " Players and " + dices + " Dices.";
    }

    /**
     * @param amount
     * @return
     */
    public static String GET_DICETHROWN(int amount) {
        return amount + " dices where thrown.";
    }

    /**
     * @param number
     * @return
     */
    public static String GET_ERROR_FALLENOF(int number) {
        return number + "dices where fallen of";
    }

    public static String GET_PLAYERCONCLUSION (String name, int points) {
        return "Player " + name + " has got " + points + ".";
    }

    public static String GET_THROWCONCLUSION (String name, int points, String win) {
        return "Player " + name + " has got " + points + " by throwing a " + win;
    }

    /**
     *
     * @param player
     * @return
     */
    public static String GET_PLAYERNAMEREQUEST (int player) {
        return "Type in Player " + player + "'s Name.";
    }
}
