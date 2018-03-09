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

        for(Player player : players)
        {
            char[][] newBoard = new char[boardWidth][boardLength];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if(Character.isLetter(board[i][j]))
                    {
                        newBoard[i][j]='.';
                    }
                    else
                    {
                        newBoard[i][j] = board[i][j];
                    }
                }
            }
            findPlayerArea(player,player.getPosX(),player.getPosY(),'.',player.getPlayerName().charAt(0),newBoard);
//            player.setArea(playerArea);
        }

        for(Player player:players)
        {
            System.out.println(String.format("Player %s - Area : %s",player.getPlayerName(),player.getArea()));
            ArrayList<List<Integer>> playerArea = player.getArea();
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

    public static void findPlayerArea(Player player, int x, int y, char original, char fill, char[][] arr) {
        int maxX = arr.length - 1;
        int maxY = arr[0].length - 1;
        int[][] stack = new int[(maxX + 1) * (maxY + 1)][2];
        int index = 0;

        stack[0][0] = x;
        stack[0][1] = y;
        arr[x][y] = fill;
        ArrayList<List<Integer>> area = new ArrayList<>();
        List<Integer> arreea = new ArrayList<Integer>();
        arreea.add(x);
        arreea.add(y);
        area.add(arreea);
        int count = 1;

        while (index >= 0) {
            x = stack[index][0];
            y = stack[index][1];
            index--;

            if ((x > 0) && (arr[x - 1][y] == original)) {
                arr[x - 1][y] = fill;
                index++;
                stack[index][0] = x - 1;
                stack[index][1] = y;
                List<Integer> arrea = new ArrayList<Integer>();
                arrea.add(x-1);
                arrea.add(y);
                area.add(arrea);
                count++;
            }

            if ((x < maxX) && (arr[x + 1][y] == original)) {
                arr[x + 1][y] = fill;
                index++;
                stack[index][0] = x + 1;
                stack[index][1] = y;
                List<Integer> arrea = new ArrayList<Integer>();
                arrea.add(x+1);
                arrea.add(y);
                area.add(arrea);
                count++;
            }

            if ((y > 0) && (arr[x][y - 1] == original)) {
                arr[x][y - 1] = fill;
                index++;
                stack[index][0] = x;
                stack[index][1] = y - 1;
                List<Integer> arrea = new ArrayList<Integer>();
                arrea.add(x);
                arrea.add(y-1);
                area.add(arrea);
                count++;
            }

            if ((y < maxY) && (arr[x][y + 1] == original)) {
                arr[x][y + 1] = fill;
                index++;
                stack[index][0] = x;
                stack[index][1] = y + 1;
                List<Integer> arrea = new ArrayList<Integer>();
                arrea.add(x);
                arrea.add(y+1);
                area.add(arrea);
                count++;
            }
        }
        player.setArea(area);
    }
}

class Player
{
    private String playerName;
    private int posX;
    private int posY;
    private ArrayList<List<Integer>> area;
    Player(String player,int posX,int posY)
    {
        this.playerName = player;
        this.posX = posX;
        this.posY = posY;
    }

    void setArea(ArrayList<List<Integer>> playerArea)
    {
        this.area = playerArea;
    }

    ArrayList<List<Integer>> getArea()
    {
        return this.area;
    }

    String getPlayerName()
    {
        return this.playerName;
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