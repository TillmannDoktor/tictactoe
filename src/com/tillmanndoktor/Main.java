package com.tillmanndoktor;

import java.util.Scanner;

public class Main {
    private static Board boardObject = new Board();
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        chooseMode();
    }

    private static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    private static void chooseMode() {
        System.out.println("SinglePlayer / Multiplayer\ns/m?");
        String inputstring = userInput.nextLine();
        if (inputstring.equals("s")) {
            singleplayer();
        } else if (inputstring.equals("m")) {
            multiplayer();
        } else if (!inputstring.equals("") || inputstring.equals("")) {
            chooseMode();
        }
    }

    private static void multiplayer() {
        boardObject.createBoard(".");
        boardObject.singleplayer = false;
        while (boardObject.continueGame) {
            boardObject.whoNext();
            String inputstring = userInput.nextLine();
            if (isParsable(inputstring)) {
                int input = Integer.parseInt(inputstring);
                boardObject.gameMulti(input - 1);
            } else {
                System.out.println("input a position");
            }

        }
    }

    private static void singleplayer() {
        boardObject.createBoard(".");
        boardObject.singleplayer = true;
        while (boardObject.continueGame) {
            boardObject.whoNext();
            if (boardObject.isXNext) {
                String inputstring = userInput.nextLine();
                if (isParsable(inputstring)) {
                    int input = Integer.parseInt(inputstring);
                    boardObject.gameSingle(input - 1);
                } else {
                    System.out.println("input a position");
                }
            } else {
                boardObject.gameSingle(0);
            }
        }
    }
}
