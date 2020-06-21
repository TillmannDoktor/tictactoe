package com.tillmanndoktor;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Board {
    private static Scanner userInput = new Scanner(System.in);
    boolean continueGame = true;
    boolean singleplayer;
    boolean isXNext;
    private String[] spots;
    private String placeholder;
    private String lastPlayed;
    private int xWins = 0;
    private int oWins = 0;

    void createBoard(String placeh) {
        spots = new String[9];
        Arrays.fill(spots, placeh);
        placeholder = placeh;
    }

    private void displayBoard() {
        System.out.println(
                "|" + spots[0] + " " + spots[1] + " " + spots[2] + "|\n" +
                        "|" + spots[3] + " " + spots[4] + " " + spots[5] + "|\n" +
                        "|" + spots[6] + " " + spots[7] + " " + spots[8] + "|" +
                        "\n"
        );
    }

    private void place(int pos, String xo) {
        if (pos > (-1) && pos < 9 && spots[pos].equals(placeholder)) {
            if (xo.equals("x") || xo.equals("X")) {
                spots[pos] = "X";
                lastPlayed = "X";
                displayBoard();
                checkWin();
            } else if (xo.equals("o") || xo.equals("O")) {
                spots[pos] = "O";
                lastPlayed = "O";
                displayBoard();
                checkWin();

            } else {
                System.out.println(xo + " is an illegal char");
            }
        } else {
            System.out.println(pos + " is already occupied / pos out of bound");
        }
    }

    private void clearBoard() {
        Arrays.fill(spots, placeholder);
        System.out.println("Resetting Board...");
    }

    private void checkWin() {
        if (isWon("X")) {
            xWins++;
            System.out.println("<><><><><>\nX WON!" + "\nX:" + String.valueOf(xWins) + " O:" + String.valueOf(oWins) + "\n<><><><><>\n");
            setContinueGame();
        } else if (isWon("O")) {
            oWins++;
            System.out.println("<><><><><>\nO WON!" + "\nX:" + String.valueOf(xWins) + " O:" + String.valueOf(oWins) + "\n<><><><><>\n");
            setContinueGame();
        }
    }

    private void setContinueGame() {
        System.out.println("continue? y/n");
        String input = userInput.nextLine();
        if (input.equals("y") || input.equals("Y")) {
            clearBoard();
        } else if (input.equals("n") || input.equals("N")) {
            continueGame = false;
        } else if (!input.equals("") || input.equals("")) {
            setContinueGame();
        }
    }

    private boolean isWon(String sign) {
        boolean isWon = false;
        //vertical
        for (int i = 0; i < 9; i += 3)
            if (spots[i].equals(sign) && spots[i + 1].equals(sign) && spots[i + 2].equals(sign)) {
                isWon = true;
                break;
            }
        //horizontal
        for (int i = 0; i < 3; i++)
            if (spots[i].equals(sign) && spots[i + 3].equals(sign) && spots[i + 6].equals(sign)) {
                isWon = true;
                break;
            }
        //diagonal
        if (spots[0].equals(sign) && spots[4].equals(sign) && spots[8].equals(sign)) {
            isWon = true;
        }
        if (spots[2].equals(sign) && spots[4].equals(sign) && spots[6].equals(sign)) {
            isWon = true;
        }
        return isWon;
    }

    private boolean xsturn() {
        boolean xsturn;
        if (lastPlayed == null) {
            if (singleplayer) {
                xsturn = true;
            } else {
                Random rd = new Random();
                xsturn = rd.nextBoolean();
            }
        } else xsturn = lastPlayed.equals("O");
        return xsturn;
    }

    void whoNext() {
        if (xsturn()) {
            System.out.println("It's X's turn");
            isXNext = true;
        } else {
            System.out.println("It's O's turn");
            isXNext = false;
        }
    }

    void gameMulti(int input) {
        if (isXNext) {
            place(input, "X");
        } else {
            place(input, "O");
        }
    }

    void gameSingle(int input) {
        if (isXNext) {
            place(input, "X");
        } else {
            bot();
        }
    }

    private void bot() {
        Random rd = new Random();
        int botPos = rd.nextInt(9);
        while (spots[botPos].equals("X")) {
            botPos = rd.nextInt(9);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        place(botPos, "O");
    }
}
