package com.project.root.sources;

/**
 * A utility class to store string resources used in the cli communication with the player.
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
     * The welcome message.
     */
    public static final String OUT_GREETING = "Welcome to the decades best dice cli game.";

    /**
     * The request that asks the player to permit the amount of players.
     */
    public static final String OUT_PLAYERINQUIRI = "Please type in the amount of players that want to play the game. " +
            "\nMinimum 1 players must be selected. Maximum 10 players are allowed.";

    /**
     * Prints the options after each round.
     */
    public static final String OUT_OPTIONS = "1. Play. \n2. End the game. \n3. Print the rules.";

    /**
     * Requests the player to permit the dice count to play with.
     */
    public static final String OUT_DICECOUNT = "Please type in the number of dices you want to play with.\n" +
            "Maximum is 10, minimum 3.";

    /**
     * Requests the player to select his favour option.
     */
    public static final String OUT_REQUESTOPTSELECT = "Please select your favour option.\nOnly submit the number " +
            "of " + "the option.";

    /**
     * The final message after the game has been finished by the player.
     */
    public static final String OUT_FINALMESSAGE = "The game has beend finished.\nThank your for playing.";

    /**
     * Requests the player to submit a correct option due to the console output.
     */
    public static final String ERROR_OPTION = "Please input a correct option.";

    /**
     * Requests the player to submit a integer.
     */
    public static final String ERROR_INTOPTION = "Please input a integer.";

    /**
     * Generates the message at start time of the game when player and dice amount where selected.
     *
     * @param players The amount of players.
     * @param dices   The amount of dices.
     * @return Returns the message as a string.
     */
    public static String GET_STARTMESAGE(int players, int dices) {
        return "You are playing with " + players + " Players and " + dices + " Dices.";
    }

    /**
     * Generates the message when some dices where fallen of.
     *
     * @param number The amount of dices that where fallen of.
     * @return The message as a string.
     */
    public static String GET_ERROR_FALLENOF(int number) {
        return number + "dices where fallen of";
    }

    /**
     * Generates a conclusion for a player that finished the game.
     *
     * @param name   The name of the player.
     * @param points The points he got.
     * @return Returns the message as string.
     */
    public static String GET_PLAYERCONCLUSION(String name, int points) {
        return "Player " + name + " has got " + points + ".";
    }

    /**
     * Generates the conclusion for a throw.
     *
     * @param name   The name.
     * @param points The points.
     * @param win    The win.
     * @return Returns the message.
     */
    public static String GET_THROWCONCLUSION(String name, int points, String win) {
        return "Player " + name + " has got " + points + " by throwing a " + win;
    }

    /**
     * Generates a request to input the name of a new player.
     *
     * @param player The number of the player in the current run.
     * @return Returns the message.
     */
    public static String GET_PLAYERNAMEREQUEST(int player) {
        return "Type in Player " + player + "'s Name.";
    }
}
