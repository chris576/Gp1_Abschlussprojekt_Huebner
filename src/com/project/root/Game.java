package com.project.root;

import com.project.root.gameutilities.winvalidation.DicesFallOfException;
import com.project.root.gameutilities.Mug;
import com.project.root.gameutilities.winvalidation.WinValidation;
import com.project.root.player.Player;
import com.project.root.sources.Strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the main class of the project. It has the main and therefore the entry point of the program.
 * It defines the rules for starting, ending and playing the game. It also outputs
 *
 * @author Christopher Hübner
 * @version 1.0 23.01.2021
 */
public class Game {

    /**
     * An array to store any player who takes part of the competition. Is initialized in {@link #begin()}, because
     * the amount of players is unknown at init time of the game object.
     */
    private Player[] players;

    /**
     * The mug the players do play with.
     */
    private Mug mug = new Mug();

    /**
     * The standard constructor of the Game Class.
     */
    public Game() {
    }

    /**
     * The main method (entry point) of this project.
     *
     * @param args Optional arguments given to the program.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.begin();
        boolean notEnd;
        do {
            notEnd = game.play();
        } while (notEnd);
        game.finish();
    }

    /**
     * Requests the user to input a integer.
     *
     * @return Returns the next integer given to the cli.
     * @throws InputMismatchException Throws a Exception, if a non integer value is passed to the cli.
     */
    private int cli_int_in() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * Requests the user to input a string.
     *
     * @return The new line on the cli is returned as string.
     */
    private String cli_str_in() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Starts the game. Requests the user to select how much player want to play and how much dices shall be used.
     */
    private void begin() {
        System.out.println(Strings.OUT_GREETING);
        // 1. Step: How much players to play this game?
        boolean player_count_selected = false;
        do {
            System.out.println(Strings.OUT_PLAYERINQUIRI);
            try {
                int players = cli_int_in();
                if (players < 10 && players > 0) {
                    this.players = new Player[players];
                    player_count_selected = true;
                }
            } catch (InputMismatchException exception) {
                System.out.println(Strings.ERROR_INTOPTION);
            }
        } while (!player_count_selected);
        // Step 2: What are the names for the players?
        for (int i = 0; i < players.length; i++) {
            System.out.println(Strings.GET_PLAYERNAMEREQUEST(i + 1));
            String name = cli_str_in();
            players[i] = new Player(name);
        }
        // Step 3: Declare how much dices should be in a mug.
        boolean dice_count_selected = false;
        do {
            try {
                System.out.println(Strings.OUT_DICECOUNT);
                int count = cli_int_in();
                if (count < 11 && count > 2) {
                    mug.setDiceQuantity(count);
                    dice_count_selected = true;
                } else {
                    System.out.println(Strings.ERROR_OPTION);
                }
            } catch (InputMismatchException exception) {
                System.out.println(Strings.ERROR_OPTION);
            }
        } while (!dice_count_selected);
        // Step 4: Print the beginning options.
        System.out.println(Strings.GET_STARTMESAGE(players.length, mug.diceQuantity()));
    }

    /**
     * Defines the rules for playing a single round.
     * Step 1: Print the options.
     * Step 2: The user selects a option.
     * Step 3: Any player throws the mug. The points and wins are added to the players.
     * Step 4: The dices and the wins are printed to the cli.
     *
     * @return returns if a new round shall be played due to the user input from the cli. If true, a new round shall
     * be played.
     */
    private boolean play() {
        System.out.println(Strings.OUT_OPTIONS);
        System.out.println(Strings.OUT_REQUESTOPTSELECT);
        int option = 0;
        boolean optionSelected = false;
        do {
            try {
                option = cli_int_in();
                optionSelected = true;
            } catch (InputMismatchException NoSuchElementException) {
                System.out.println(Strings.ERROR_OPTION);
                System.out.println(Strings.OUT_OPTIONS);
            }
        } while (!optionSelected);
        if (option == 2) {
            return false;
        }
        if (option == 3) {
            try {
                displayRules();
            } catch (IOException e) {
                System.out.println("Rule file not found!");
                return false;
            }
            return true;
        }

        for (Player player : players) {
            try {
                mug.throwAny();
                mug.cliOutAll();
                WinValidation.Win win = WinValidation.validate(mug);
                player.addProfits(win.getProfit());
                player.addPoints(win.getPoint());
                String win_str = "";
                switch (win.getProfit()) {
                    case ILLEGAL:
                        win_str = "illegal throw";
                        break;
                    case NONE:
                        win_str = "regular throw without special wins.";
                        break;
                    case PAIR:
                        win_str = "pair.";
                        break;
                    case PASCH:
                        win_str = "pasch.";
                        break;
                    case STREET:
                        win_str = "street.";
                        break;
                }
                System.out.println(Strings.GET_THROWCONCLUSION(player.getName(), win.getPoint(), win_str));
            } catch (DicesFallOfException ex) {
                System.out.println(ex.getMessage());
                player.addProfits(WinValidation.Profit_Types.ILLEGAL);
                System.out.println(Strings.GET_THROWCONCLUSION(player.getName(), 0, "illegal throw"));
            }
        }
        return true;
    }

    /**
     * Finishes the game by output a conclusion of the played rounds.
     */
    private void finish() {
        System.out.println(Strings.OUT_FINALMESSAGE);
        Arrays.stream(players).forEach(
                el -> System.out.println(Strings.GET_PLAYERCONCLUSION(el.getName(), el.getPoints())));
    }

    /**
     * Displays the rules.
     *
     * @throws IOException throws a Exception, if the rules where not found.
     */
    private void displayRules() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sources/Rules.txt"));
        String line = in.readLine();
        while (line != null) {
            System.out.println(line);
            line = in.readLine();
        }
        in.close();
    }
}