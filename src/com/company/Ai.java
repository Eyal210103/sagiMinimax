package com.company;

import java.time.temporal.IsoFields;
import java.util.ArrayList;

public class Ai {


    public Ai() {
    }

    public Move playTurn(GameManager gameManager) {
        Move move = new Move(1, 1);
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < gameManager.getLines().size(); i++) {
            for (int j = 1; j < gameManager.getLines().get(i) + 1; j++) {
                GameManager boardToTry = new GameManager(gameManager);
                boardToTry.playTurn(i, j);
                int score = minimax(boardToTry, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                boardToTry.addNumToLine(i, j);
                if (bestScore < score) {
                    bestScore = score;
                    move.row = i;
                    move.amount = j;
                }
            }
        }
        return move;
    }

    private int minimax(GameManager gameManager, int depth, boolean isMaximizing, int alpha, int beta) {
        if (!gameManager.getWinner().equals("none")) { // אם המשחק נגמר
            if (depth == 0) //מקרה קצה
                depth++;
            if (isMaximizing)
                return -1000 / depth; //להחזיר מינימום
            else
                return 1000 / depth; //להחזיר מקסימום
        }
        if (!gameManager.isLegalBoard()){ // אם התור התור חוקי והלוח נשאר חוקי
            return -1000; // המצב הכי גרוע
        }
        int bestScore;
        if (isMaximizing) { // אם הבינה משחקת
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < gameManager.getLines().size(); i++) { // מספר שורה
                for (int j = 1; j < gameManager.getLines().get(i) + 1; j++) { // מספר המקלות אותם לוקחים
                    gameManager.playTurn(i, j);
                    int score = minimax(gameManager, depth + 1, false, alpha, beta);
                    bestScore = Math.max(bestScore, score);
                    gameManager.addNumToLine(i, j); // לאפס למצב קודם

                    alpha = Math.max(alpha, score);
                    if (beta <= alpha)
                        break;
                }
            }
        } else { // אם הבינה לא משחקת
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < gameManager.getLines().size(); i++) {
                for (int j = 1; j < gameManager.getLines().get(i) + 1; j++) {
                    gameManager.playTurn(i, j);
                    int score = minimax(gameManager, depth + 1, true, alpha, beta);
                    bestScore = Math.min(bestScore, score);
                    gameManager.addNumToLine(i, j);

                    beta = Math.min(beta, score);
                    if (beta <= alpha)
                        break;
                }
            }
        }
        return bestScore;
    }
}
