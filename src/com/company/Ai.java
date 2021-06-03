package com.company;

import java.time.temporal.IsoFields;

public class Ai {

    private GameManager gameManager;

    public Ai(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public Move playTurn() {
        int bestScore = Integer.MIN_VALUE;
        Move move = new Move(1, 1);
        GameManager toCheckOn = new GameManager(gameManager);
        for (int i = 0; i < gameManager.getLines().length; i++) {
            for (int j = 1; j < gameManager.getLines()[i].getMaklot().length + 1; j++) {
                Move moveToTry = new Move(i, j);
                if (gameManager.getLines()[i].isEnough(j)) {
                    toCheckOn.playTurn(i, j);
                    int score = minimax(toCheckOn, Integer.MAX_VALUE, false);
                    toCheckOn.getLines()[i].addNumOfMaklot(j);
                    if (bestScore <= score) {
                        bestScore = score;
                        move.amount = moveToTry.amount;
                        move.row = moveToTry.row;
                    }
                }
            }
        }
        return move;
    }

    public int minimax(GameManager gameManager, int depth, boolean isMaximizing) {
        if (gameManager.getWinner().equals("ai")) {
            return depth;
        } else if (gameManager.getWinner().equals("player")) {
            return -1*depth;
        }

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < gameManager.getLines().length; i++) {
                for (int j = 1; j < gameManager.getLines()[i].getMaklot().length + 1; j++) {
                    if (gameManager.getLines()[i].isEnough(j)) {
                        if (gameManager.playTurn(i, j)) {
                            int score = minimax(gameManager, depth - 1, false);
                            if (score > bestScore)
                                bestScore = score;
                        }
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < gameManager.getLines().length; i++) {
                for (int j = 1; j < gameManager.getLines()[i].getMaklot().length + 1; j++) {
                    if (gameManager.getLines()[i].isEnough(j)) {
                        if(gameManager.playTurn(i, j)) {
                            int score = minimax(gameManager, depth - 1, true);
                            if (score < bestScore)
                                bestScore = score;
                        }
                    }
                }
            }
        }

        return bestScore;
    }

}
