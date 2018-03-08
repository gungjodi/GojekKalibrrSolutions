package com.company;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int x[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int y[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public static void main(String[] args) {
        File fileInput = new File("input.in");
        try(Scanner scanner = new Scanner(fileInput))
        {
            int cases = scanner.nextInt();
            int lines = scanner.nextInt();
            int charLength = scanner.nextInt();
            scanner.nextLine();
            char[][] wordsArray = new char[lines][charLength];
            for(int i=0;i<lines;i++)
            {
                wordsArray[i] = scanner.nextLine().toCharArray();
            }
            String wordToSearch = scanner.nextLine();
            for(int i=0;i<lines;i++)
            {
                System.out.println(Arrays.toString(wordsArray[i]));
            }
            validateInputData(wordsArray,wordToSearch);
            System.out.println("LtoR : "+findLeftToRight(wordsArray,wordToSearch));
            System.out.println("RtoL : "+findRightToLeft(wordsArray,wordToSearch));
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        System.out.println();
    }

    public static String findLeftToRight (char[][]board, String word) {
        char[] letters = word.toCharArray();

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                boolean found = true;

                for (int k = 0; k < letters.length; k++) {
                    if ((j+k >= board[i].length) || (letters[k] != board[i][j+k])) {
                        found = false;
                        break;
                    }
                }

                if (found) {
                    return "String " + word + " found in row=" + i + " col=" +j;
                }
            }
        }
        return "String " + word + " not found";
    }

    public static String findRightToLeft (char[][]board, String word) {
        char[] letters = word.toCharArray();

        for (int i = board.length-1; i > -1; i--){
            for (int j = board[i].length-1; j > -1; j--) {
                boolean found = true;

                for (int k = 0; k < letters.length; k++) {
                    if ((j - k < 0) || (letters[k] != board[i][j-k])) {
                        found = false;
                        break;
                    }
                }

                if (found) {
                    return "String " + word + " found in row=" + i + " col=" +j;
                }
            }
        }
        return "String " + word + " not found";
    }

    private static void validateInputData(char[][] wordSearch, String word) {
        if (word == null || wordSearch == null) {
            throw new IllegalArgumentException("You can't pass null instances as parameter.");
        }
    }
}
