package com.company;


import java.util.*;

public class GameManager {
    private final ArrayList<Integer> lines;
    private int turn; //0- p1 1 -p2

    public GameManager(int lines) {
        int numOfMaklot = 1; // reset lines in pyramid shape
        this.lines = new ArrayList<Integer>();
        for (int i = 0; i < lines; i++) {
            this.lines.add(numOfMaklot);
            numOfMaklot += 2;
        }
        Random rand = new Random();
        this.turn = rand.nextInt(2);
    }

    public GameManager(GameManager other) {
        this.lines = new ArrayList<Integer>();
        this.lines.addAll(other.lines);
        this.turn = other.turn;
    }

    public boolean playTurn(int selectedRow, int amount) {
        if (this.lines.get(selectedRow) >= amount) {
            this.lines.set(selectedRow,this.lines.get(selectedRow)-amount);
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

    public void addNumToLine(int line,int amount){
        lines.set(line,lines.get(line)+amount);
    }

    public String getWinner() {
        int count = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (!(lines.get(i) == 1) && !(lines.get(i) == 0))
                return "none";
            if (lines.get(i) == 1)
                count++;
        }
        if (count == 1) {
            return turn == 0 ? "ai" : "player";
        }
        return "none";
    }

    public boolean isLegalBoard(){
        int sum =0;
        for (Integer i:lines) {
            sum+=i;
        }
        return sum != 0;
    }

    public int getTurn() {
        return turn;
    }

    public ArrayList<Integer> getLines() {
        return lines;
    }


    public void print() {
        for (int i = 0; i < lines.size(); i++) {
            Integer m = lines.get(i);
            System.out.print(i + 1 + "  ");
            for (int j = 0; j < m; j++) {
                System.out.print("X");
            }
            System.out.println();
        }
    }

    public String getBoardGraphics(){
        StringBuilder output= new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            Integer m = lines.get(i);
            output.append(i+1).append("  ");
            output.append("X".repeat(Math.max(0, m)));
            output.append("\n");
        }
        return output.toString();
    }
}
