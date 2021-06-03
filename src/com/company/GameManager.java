package com.company;


import java.util.*;

public class GameManager {
    private Maklot[] lines;
    private int turn; //0- p1 1 -p2
    private boolean gameOver;
    private boolean hasStarted;
    // turns
    //impl who wins


    public GameManager(int lines) {
        int numOfMaklot = 1; // reset lines in pyramid shape
        this.lines = new Maklot[lines];
        for (int i = 0; i < lines; i++) {
            this.lines[i] = new Maklot(numOfMaklot);
            numOfMaklot += 2;
        }

        Random rand = new Random();
        this.turn = 0;//rand.nextInt(2);
        this.gameOver = false;
        this.hasStarted = false;
    }

    public GameManager(GameManager other) {
        this.lines = new Maklot[other.lines.length];
        for (int i = 0; i < other.lines.length; i++) {
            this.lines[i] = new Maklot(other.lines[i].getAmount());
        }
        this.turn = other.turn;
        this.gameOver = other.gameOver;
        this.hasStarted = other.hasStarted;
    }

    public boolean playTurn(int selectedRow, int amount) {
        if (!hasStarted) {
            hasStarted = true;
        }
        if (this.lines[selectedRow].isEnough(amount)) {
            this.lines[selectedRow].removeNumOfMaklot(amount);

        } else {
            return false;
        }

        if (isWon()) {
            this.gameOver = true;
            return  true;
        } else {
            if (this.turn == 0) {
                this.turn = 1;
            } else {
                this.turn = 0;

            }
        }
        return true;

    }

    public boolean isWon() {
        int count = 0;
        for (int i = 0; i < lines.length; i++) {
            if (!lines[i].isGotOne() && !lines[i].isEmpty())
                return false;
            if (lines[i].isGotOne())
                count++;
        }
        return count == 1;
    }


    public int getTurn() {
        return turn;
    }


    public void setTurn(int turn) {
        this.turn = turn;
    }


    public boolean isGameOver() {
        return gameOver;
    }

    public boolean HasStarted() {
        return hasStarted;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getAmountOfLines() {
        return lines.length;
    }

    public int getAmountOfMaklotInLine(int row) {
        return lines[row].getAmount();
    }

    // public int getAmountOfMaklotLeftInLine(int row){return lines[row].getLeftAmount();}

    public Maklot[] getLines() {
        return lines;
    }

    public void print() {
        for (int i = 0; i < lines.length; i++) {
            Maklot m = lines[i];
            System.out.print(i + 1 + "  ");
            for (int j = 0; j < m.getAmount(); j++) {
                if (m.isThere(j)) {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
}
