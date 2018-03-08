package com.company;

import java.io.File;
import java.util.*;

public class Kingdom {
    static char[][] board;
    static int boardWidth;
    static int boardLength;
    static int cases;

    public static void main(String[] args) {
        File fileInput = new File("kingdom.in");
        try (Scanner scanner = new Scanner(fileInput)) {
            cases = scanner.nextInt();
            for(int i=1;i<=cases;i++)
            {
                boardWidth = scanner.nextInt();
                boardLength = scanner.nextInt();
                board = new char[boardWidth][boardLength];
                scanner.nextLine();
                for (int x = 0; x < boardWidth; x++) {
                    board[x] = scanner.nextLine().toCharArray();
                }
                System.out.println("CASE "+i+" :");
                getPlayers(board);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    static void getPlayers(char[][] board) {
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (Character.isLetter(board[i][j])) {
                    Player player = new Player(Character.toString(board[i][j]), i, j);
                    players.add(player);
                }
            }
        }

        int[][] newBoard = new int[boardWidth][boardLength];
        for(Player player : players)
        {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == '#') {
                        newBoard[i][j] = 0;
                    } else {
                        newBoard[i][j] = 1;
                    }
                }
            }
            Set<List<Integer>> area = new HashSet<>();
            findNumberConnected(player.getPosX(), player.getPosY(), newBoard,area);
            System.out.println("PLAYER : "+player.getPlayerName()+" AREA : "+area);
        }


    }

    public static int findNumberConnected(int a, int b, int[][] z,Set<List<Integer>> area)
    {
        boolean canUp = (a - 1 >= 0);
        boolean canDown = (a + 1 < z.length);
        boolean canRight = (b + 1 < z[0].length);
        boolean canLeft = (b - 1 >= 0);

        int value = z[a][b];

        int up = 0;
        int down = 0;
        int right = 0;
        int left = 0;

        z[a][b] = 2;

        if (canUp && z[a - 1][b] == value) {
            up = findNumberConnected(a - 1, b, z,area);
        }
        if (canDown && z[a + 1][b] == value) {
            down = findNumberConnected(a + 1, b, z,area);
        }
        if (canLeft && z[a][b - 1] == value) {
            left = findNumberConnected(a, b - 1, z,area);
        }
        if (canRight && z[a][b + 1] == value) {
            right = findNumberConnected(a, b + 1, z,area);
        }
        List<Integer> myArea = new ArrayList<>();
        myArea.add(a);
        myArea.add(b);
        area.add(myArea);
        return up + left + right + down + 1;
    }
}

class Player
{
    private String playerName;
    private int posX;
    private int posY;

    Player(String player,int posX,int posY)
    {
        this.playerName = player;
        this.posX = posX;
        this.posY = posY;
    }

    String getPlayerName()
    {
        return this.playerName;
    }

    void moveLeft()
    {
        System.out.println("GO LEFT");
        this.posX = this.posX + 0;
        this.posY = this.posY-1;
    }
    void moveRight()
    {
        System.out.println("GO RIGHT");
        this.posX = this.posX + 0;
        this.posY = this.posY+1;
    }
    void moveUp()
    {
        System.out.println("GO UP");
        this.posX = this.posX-1;
        this.posY = this.posY+0;
    }
    void moveDown()
    {
        System.out.println("GO DOWN");
        this.posX = this.posX + 1;
        this.posY = this.posY+0;
    }

    void setPos(int posX,int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    int getPosX()
    {
        return this.posX;
    }

    int getPosY()
    {
        return this.posY;
    }
}