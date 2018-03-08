package com.company;

import java.io.File;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WordSearchPuzzle {

    private int currentChar;
    private char word[];

    public int wordExists(char board[][], String word) {

        int count=0;
        if (word.isEmpty()) {
            throw new InputMismatchException("Searched word can not be empty!");
        }
        this.word = new char[word.length()];
        currentChar = 0;

        for (int a = 0; a < word.length(); a++) {
            word.getChars(0, word.length(), this.word, 0);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (this.word.length != 0 && board[i][j] == this.word[currentChar]) {
                    if (this.word.length == 1) {
                        count++;
                    }

                    if (letterExists(board, i, j, this.word[++currentChar], "N")) {
                        count++;
                    }
                    currentChar = 0;
                    if (letterExists(board, i, j, this.word[++currentChar], "NE")) {
                        count++;
                    }
                    currentChar = 0;
                    if (letterExists(board, i, j, this.word[++currentChar], "E")) {
                        count++;
                    }
                    currentChar = 0;
                    if (letterExists(board, i, j, this.word[++currentChar], "SE")) {
                        count++;
                    }
                    currentChar = 0;
                    if (letterExists(board, i, j, this.word[++currentChar], "S")) {
                        count++;

                    }
                    currentChar = 0;
                    if (letterExists(board, i, j, this.word[++currentChar], "SW")) {
                        count++;
                    }
                    currentChar = 0;
                    if (letterExists(board, i, j, this.word[++currentChar], "W")) {
                        count++;

                    }
                    currentChar = 0;
                    if (letterExists(board, i, j, this.word[++currentChar], "NW")) {
                        count++;

                    }
                    currentChar = 0;
                    continue;
                }
            }
        }

        return count;
    }

    public boolean letterExists(char board[][], int i, int j, char letter, String direction) {
        currentChar++;
        if (i - 1 >= 0 && board[i - 1][j] == letter && direction.equals("N"))
        {
            if (currentChar == word.length)
                return true;
            return letterExists(board, i - 1, j, word[currentChar], "N");
        }
        else if (i - 1 >= 0 && j + 1 < board[i].length && board[i - 1][j + 1] == letter && direction.equals("NE"))
        {
            if (currentChar == word.length)
                return true;
            return letterExists(board, i - 1, j + 1, word[currentChar], "NE");
        }
        else if (j + 1 < board[i].length && board[i][j + 1] == letter && direction.equals("E"))
        {
            if (currentChar == word.length)
                return true;
            return letterExists(board, i, j + 1, word[currentChar], "E");
        }
        else if (i + 1 < board.length && j + 1 < board[i + 1].length && board[i + 1][j + 1] == letter && direction.equals("SE"))
        {
            if (currentChar == word.length)
                return true;
            return letterExists(board, i + 1, j + 1, word[currentChar], "SE");
        }
        else if (i + 1 < board.length && board[i + 1][j] == letter && direction.equals("S"))
        {
            if (currentChar == word.length)
                return true;
            return letterExists(board, i + 1, j, word[currentChar], "S");
        }
        else if (i + 1 < board.length && j - 1 >= 0 && board[i + 1][j - 1] == letter && direction.equals("SW"))
        {
            if (currentChar == word.length)
                return true;
            return letterExists(board, i + 1, j - 1, word[currentChar], "SW");
        }
        else if (j - 1 >= 0 && board[i][j - 1] == letter && direction.equals("W"))
        {
            if (currentChar == word.length)
                return true;
            return letterExists(board, i, j - 1, word[currentChar], "W");
        }
        else if (j - 1 >= 0 && i - 1 >= 0 && board[i - 1][j - 1] == letter && direction.equals("NW"))
        {
            if (currentChar == word.length)
                return true;
            return letterExists(board, i - 1, j - 1, word[currentChar], "NW");
        }
        return false;
    }

    public static void main(String[] args)
    {
        File fileInput = new File("input.in");
        try(Scanner scanner = new Scanner(fileInput))
        {
            int cases = scanner.nextInt();
            for(int k=1;k<=cases;k++)
            {
                int lines = scanner.nextInt();
                int charLength = scanner.nextInt();
                scanner.nextLine();
                char[][] board = new char[lines][charLength];
                for(int i=0;i<lines;i++)
                {
                    board[i] = scanner.nextLine().toCharArray();
                }
                String wordToSearch = scanner.nextLine();
                WordSearchPuzzle puzzle = new WordSearchPuzzle();
                System.out.println("Case "+k+": "+puzzle.wordExists(board, wordToSearch));
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
