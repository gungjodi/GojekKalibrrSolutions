package com.company;

import java.io.File;
import java.util.*;

public class Kingdom {
    static List<Player> players = new ArrayList<Player>();
    public static void main(String[] args)
    {
        File fileInput = new File("kingdom.in");
        try(Scanner scanner = new Scanner(fileInput))
        {
            int cases = scanner.nextInt();
            int boardWidth = scanner.nextInt();
            int boardLength = scanner.nextInt();
            char[][] board = new char[boardWidth][boardLength];
            scanner.nextLine();
            for(int x=0;x<boardWidth;x++)
            {
                board[x] = scanner.nextLine().toCharArray();
            }
            getPlayers(board);
            getPlayerArea(players.get(0),board);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    static void getPlayers(char[][] board)
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                if(Character.isLetter(board[i][j]))
                {
                    Player player = new Player(Character.toString(board[i][j]),i,j);
                    players.add(player);
                }
            }
        }
        for(Player player : players)
        {
            System.out.println("Player "+player.getPlayerName()+" POS : "+player.getPosX()+", "+player.getPosY());
        }
    }

    static void getPlayerArea(Player player,char[][] board)
    {
        int posX = player.getPosX();
        int posY = player.getPosY();
        Set<List<Integer>> area = new HashSet<List<Integer>>();
        for(int i=posY;i>=0;i--)
        {
            if(!(board[posX][i]=='#'))
            {
                List<Integer> pos = new ArrayList<Integer>();
                pos.add(posX);
                pos.add(i);
                area.add(pos);
            }
            else
            {
                break;
            }

        }
        for(int i=posY;i<board.length;i++)
        {
            if(!(board[posX][i]=='#'))
            {
                List<Integer> pos = new ArrayList<Integer>();
                pos.add(posX);
                pos.add(i);
                area.add(pos);
            }
            else
            {
                break;
            }
        }
        System.out.println(area);
        //check left and right till get #
        //go up check left to right, so on till get #
        // go down check left to right, so on till get #
        // go up or down stop till get #
    }
}

class Player
{
    String playerName;
    int posX;
    int posY;
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
        this.posX = this.posX + 0;
        this.posY = this.posY-1;
    }
    void moveRight()
    {
        this.posX = this.posX + 0;
        this.posY = this.posY+1;
    }
    void moveUp()
    {
        this.posX = this.posX-1;
        this.posY = this.posY+0;
    }
    void moveDown()
    {
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