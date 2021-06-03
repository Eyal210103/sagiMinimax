package com.company;

import java.util.*;

class GameManager2 {
    private Maklot[] lines;
    private int turn; //0- p1 1 -p2
    private boolean gameOver;
    private boolean hasStarted;

    public GameManager2(int lines) {
        int numOfMaklot = 1; // reset lines in pyramid shape
        this.lines = new Maklot[lines];
        for(int i  = 0; i<lines ; i++) {
            this.lines[i] = new Maklot(numOfMaklot);
            numOfMaklot+=2;
        }

        Random rand = new Random();
        this.turn = 0;
        this.gameOver = false;
        this.hasStarted = false;
    }


    public void playTurn(int selectedRow, int amount) throws Exception {
        if(!hasStarted) {
            hasStarted = true;
        }
        if(this.lines[selectedRow].isEnough(amount)) {
            this.lines[selectedRow].removeNumOfMaklot(amount);
        }else {
            throw new Exception("NOOOOOO");
        }

        if(true){//isWon()) {
            this.gameOver = true;
        }
        else {
            if(this.turn == 0) {
                this.turn = 1;
            }else {
                this.turn = 0;

            }
        }
    }

//    private boolean isWon() {
//        int count = 0;
//        for(int i  = 0; i<lines.length ; i++) {
//            if(this.lines[i].isGotOne()) {
//                count++;
//            }
//            if(count > 1) {
//                return false;
//            }
//        }
//        return true;
//    }


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

    public Maklot[] getLines() {
        return lines;
    }

    public void print() {
        for(int i = 0 ; i<lines.length; i++) {
            Maklot m  = lines[i];
            for(int j  =0 ; j<m.getAmount();j++) {
                if(m.isThere(j)) {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
    public int minimax(int depth,boolean isP1) {
        int bestScore = Integer.MIN_VALUE;
        int score = bestScore;
        int bestRow = -1;
        int bestAmmount = -1;
        if (this.isGameOver()) {
            if (!isP1)
                return depth*-1;
            else
                return depth;
        }
        if (depth == 0)
            return 0;
        if (!isP1) {

            for (int i = 0; i<this.lines.length; i++) {
                for (int j = 1; j<this.lines[i].getMaklot().length+1;j++) {
                    if (this.lines[i].isEnough(j)) {
                        this.lines[i].removeNumOfMaklot(j);
                        score = this.minimax(depth - 1,!isP1);
                        if (score > bestScore) {
                            bestScore = score;
                            bestRow = i+1;
                            bestAmmount = j;
                        }
                        this.lines[i].addNumOfMaklot(j);
                    }
                }
            }
        }
        else {
            bestScore = Integer.MAX_VALUE;
            score = bestScore;
            for (int i = 0; i<this.lines.length; i++) {
                for (int j = 1; j<this.lines[i].getMaklot().length+1;j++) {
                    if (this.lines[i].isEnough(j)) {
                        this.lines[i].removeNumOfMaklot(j);
                        score = this.minimax(depth - 1,!isP1);
                        if (score < bestScore) {
                            bestScore = score;
                            bestRow = i+1;
                            bestAmmount = j;
                        }
                        this.lines[i].addNumOfMaklot(j);
                    }
                }
            }
        }
        return bestScore;
    }
    public int bestRow() {
        int bestRow = -1;
        int bestAmmount = -1;
        int bestScore = Integer.MIN_VALUE;
        int score = bestScore;
        for (int i = 0; i<this.lines.length; i++) {
            for (int j = 1; j<this.lines[i].getMaklot().length+1;j++) {
                if (this.lines[i].isEnough(j)) {
                    this.lines[i].removeNumOfMaklot(j);
                    score = this.minimax(7,true);
                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = i+1;
                        bestAmmount = j;
                    }
                    this.lines[i].addNumOfMaklot(j);
                }
            }
        }
        return bestRow*10+bestAmmount;
    }

}

//public class Maklot {
//    private Boolean[] maklot;
//
//    public Maklot(int num) {
//        maklot = new Boolean[num];
//        for(int i  = 0; i<num ; i++) {
//            maklot[i] =  true;
//        }
//    }
//
//    public void removeNumOfMaklot(int num) {
//        int count = 0;
//        for(int i  = 0; i<maklot.length && count < num ; i++) {
//            if(maklot[i]) {
//                maklot[i] = false;
//                count++;
//            }
//        }
//    }
//
//    public boolean isEnough(int num) {
//        int count= 0 ;
//        for(int i  = 0; i<maklot.length ; i++) {
//            if(maklot[i]) {
//                count++;
//            }
//        }
//        return count >= num;
//    }
//
//    public boolean isGotOne() {
//
//        for(int i  = 0; i<maklot.length ; i++) {
//            if(maklot[i]) {
//                return true;
//
//            }
//        }
//        return false;
//    }
//
//    public boolean isAllOut() {
//        for(int i  = 0; i<maklot.length ; i++) {
//            if(maklot[i])
//                return false;
//        }
//        return true;
//    }
//
//    public int getAmount() {
//        return maklot.length;
//    }
//
//    public Boolean[] getMaklot() {
//        return maklot;
//    }
//
//    public boolean isThere(int index) {
//        return maklot[index];
//    }
//    public void addNumOfMaklot(int x) {
//        int count = 0;
//        for (int i = 0; i<this.getMaklot().length;i++) {
//            if (!this.getMaklot()[i]) {
//                maklot[i] = true;
//                count++;
//                if (count == x) {
//                    return;
//                }
//            }
//        }
//    }
//}