package com.company;


import java.util.*;

public class GameManager {
    private Maklot[] lines;
    private int turn; //0- p1 1 -p2
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
        this.turn = rand.nextInt(2);
        this.hasStarted = false;
    }

    public GameManager(GameManager other) {
        this.lines = new Maklot[other.lines.length];
        for (int i = 0; i < other.lines.length; i++) {
            this.lines[i] = new Maklot(other.lines[i].getAmount());
        }
        this.turn = other.turn;
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

        if (getWinner().equals("none")) {
            if (this.turn == 0) {
                this.turn = 1;
            } else {
                this.turn = 0;

            }
        }
        return true;

    }

    public String getWinner() {
        int count = 0;
        for (int i = 0; i < lines.length; i++) {
            if (!lines[i].isGotOne() && !lines[i].isEmpty())
                return "none";
            if (lines[i].isGotOne())
                count++;
        }
        if (count == 1) {
            return turn == 0 ? "ai" : "player";
        }
        return "none";
    }

    public int getTurn() {
        return turn;
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
