package com.project.root;

import com.project.root.gameutilities.InvalidThrowException;
import com.project.root.gameutilities.Mug;
import com.project.root.gameutilities.winvalidation.Profit_Types;
import com.project.root.gameutilities.winvalidation.WinValidation;
import com.project.root.player.Player;
import com.project.root.sources.Strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is the main class of the project. It has the main and therefore the entry point of the programm.
 * It defines the rules for starting, ending and playing the game. It also outputs
 *
 * @version 1.0 23.01.2021
 * @author Christopher Hübner
 */
public class Game {

    private Player[] players;
    private Mug mug = new Mug();

    public Game() {
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.begin();
        boolean notEnd = true;
        do {
            notEnd = game.play();
        } while (notEnd);
        game.finish();
    }

    /**
     * @return
     * @throws InputMismatchException
     * @throws NoSuchElementException
     */
    private int cli_int_in() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        int rtn = scanner.nextInt();
        return rtn;
    }

    private String cli_str_in() {
        Scanner scanner = new Scanner(System.in);
        String rtn = scanner.nextLine();
        return rtn;
    }

    /**
     *
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
     * Plays a single round.
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
                    case Pair:
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
            } catch (InvalidThrowException ex) {
                System.out.println(ex.getMessage());
                player.addProfits(Profit_Types.ILLEGAL);
                System.out.println(Strings.GET_THROWCONCLUSION(player.getName(), 0, "illegal throw"));
            }
        }
        return true;
    }

    /**
     * Finishes the game by exiting the program.
     */
    private void finish() {
        System.out.println(Strings.OUT_FINALMESSAGE);
        Arrays.stream(players).forEach(el -> {
            System.out.println(Strings.GET_PLAYERCONCLUSION(el.getName(), el.getPoints()));
        });
    }

    /**
     * Displays the rules.
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