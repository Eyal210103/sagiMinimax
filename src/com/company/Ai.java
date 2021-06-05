package com.company;

import java.time.temporal.IsoFields;
import java.util.ArrayList;

public class Ai {


    public Ai() {
    }

    ArrayList<ArrayList<String>> boards = new ArrayList<>();

    public Move playTurn(GameManager gameManager) {
        Move move = new Move(1, 1);
        int bestScore = Integer.MIN_VALUE;
        int count = 1;
        for (int i = 0; i < gameManager.getLines().size(); i++) {
            for (int j = 1; j < gameManager.getLines().get(i) + 1; j++) {
                GameManager boardToTry = new GameManager(gameManager);
//                System.out.println("++++++++++++++++++++++++++++++++++" + count);
//                System.out.println("INITIAL");
//                boardToTry.print();
//                count++;
//                System.out.println();
//                System.out.println("TRY");
                boardToTry.playTurn(i, j);
//                boardToTry.print();
//                System.out.println();
                int score = minimax(boardToTry, 0, false,Integer.MIN_VALUE,Integer.MAX_VALUE);

          //     printTree();

               // boards = new ArrayList<>();

             //   System.out.println("check -> " + i + "," + j + " Score:" + score);
                boardToTry.addNumToLine(i, j);
                if (bestScore < score) {
                    bestScore = score;
               //    System.out.println("Better -------> " + i + "," + j + " Score:" + score);
                    move.row = i;
                    move.amount = j;
                }
            }
        }
        return move;
    }

    private int minimax(GameManager gameManager, int depth, boolean isMaximizing , int alpha, int beta) {
        if (!gameManager.getWinner().equals("none")) {
//            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
//            System.out.println(gameManager.getBoardGraphics());
//            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
            if (depth == 0)
                depth++;
            if (isMaximizing)
                return -1000 / depth;
            else
                return 1000 / depth;
        }
        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < gameManager.getLines().size(); i++) {
                for (int j = 1; j < gameManager.getLines().get(i) + 1; j++) {
                    gameManager.playTurn(i, j);

                    if (boards.size() == (depth)) {
                        boards.add(depth, new ArrayList<>());
                    }
                    boards.get(depth).add(gameManager.getBoardGraphics());


                    int score = minimax(gameManager, depth + 1, false,alpha,beta);
                    bestScore = Math.max(bestScore, score);
                    gameManager.addNumToLine(i, j);
                    alpha = Math.max(alpha,score);
                    if (beta<= alpha)
                        break;
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < gameManager.getLines().size(); i++) {
                for (int j = 1; j < gameManager.getLines().get(i) + 1; j++) {
                    gameManager.playTurn(i, j);

                    if (boards.size() == (depth)) {
                        boards.add(depth, new ArrayList<>());
                    }
                    boards.get(depth).add(gameManager.getBoardGraphics());



                    int score = minimax(gameManager, depth + 1, true,alpha,beta);
                    bestScore = Math.min(bestScore, score);
                    gameManager.addNumToLine(i, j);
                    beta = Math.min(beta,score);
                    if (beta<= alpha)
                        break;
                }
            }
        }
        return bestScore;
    }


    private void printTree() {
        int count = 1;
        for (ArrayList<String> arr : boards) {
            System.out.println(count + ":=======:" + (count % 2 == 0 ? "ai" : "enemy"));
            count++;
            for (String str : arr) {
                System.out.println(str);
            }
        }
    }


//    public Move playTurn(GameManager gameManager) {
//        Move move = new Move(1, 1);
//        GameManager toCheckOn = new GameManager(gameManager);
//        int bestScore = Integer.MIN_VALUE;
//        for (int i = 0; i < gameManager.getLines().length; i++) {
//            for (int j = 1; j < gameManager.getLines()[i].getMaklot().size()+1; j++) {
//                Move moveToTry = new Move(i, j);
//                if (toCheckOn.getLines()[i].isEnough(j)) {
//                    toCheckOn.playTurn(i, j);
//                    int score = minimax2(toCheckOn, 1, false);
//                    toCheckOn = new GameManager(gameManager);
//                    System.out.println("Try    " + moveToTry + score);
//                    if (bestScore <= score) {
//                        bestScore = score;
//                        move.amount = moveToTry.amount;
//                        move.row = moveToTry.row;
//                        System.out.println("Better           " + move + score);
//                    }
//                }
//            }
//        }
//        System.out.println("CHOOSEN           " + move);
//
//        return move;
//    }
//
//    public int minimax2(GameManager gameManager, int depth, boolean isMaximizing) {
//        if (gameManager.isGameOver()) {
//            if (isMaximizing) {
//                return -100000 / depth;
//            } else {
//                return 100000/ depth;
//            }
//        }
//        int bestScore;
//        System.out.println("ITERATION________" + depth);
//        if (isMaximizing) {
//            bestScore = Integer.MIN_VALUE;
//            for (int i = 0; i < gameManager.getLines().length; i++) {
//                for (int j = 1; j <= gameManager.getLines()[i].getMaklot().size(); j++) {
//                    if (gameManager.getLines()[i].isEnough(j)) {
//                        gameManager.playTurn(i, j);
//                        int score = minimax2(gameManager, depth+1, gameManager.getTurn()==0);
//                        bestScore = Math.max(bestScore, score);
//                    }
//                }
//            }
//        } else {
//            bestScore = Integer.MAX_VALUE;
//            for (int i = 0; i < gameManager.getLines().length; i++) {
//                for (int j = 1; j <= gameManager.getLines()[i].getMaklot().size(); j++) {
//                    if (gameManager.getLines()[i].isEnough(j)) {
//                        gameManager.playTurn(i, j);
//                        int score = minimax2(gameManager, depth, gameManager.getTurn()==0);
//                        bestScore = Math.min(bestScore, score);
//                    }
//                }
//            }
//        }
//        return bestScore;
//    }
//
//    public int minimax(GameManager gameManager, int depth, boolean isMaximizing) {
//        if (gameManager.getWinner().equals("ai")) {
//            return 1000 / depth;
//        } else if (gameManager.getWinner().equals("player")) {
//            return -1000 / depth;
//        }
//
//        int bestScore;
//        if (isMaximizing) {
//            bestScore = Integer.MIN_VALUE;
//            for (int i = 0; i < gameManager.getLines().length; i++) {
//                for (int j = 1; j < gameManager.getLines()[i].getMaklot().size() + 1; j++) {
//                    if (gameManager.getLines()[i].isEnough(j)) {
//                        gameManager.setTurn(0);
//                        if (gameManager.playTurn(i, j)) {
//                            int score = minimax(gameManager, depth + 1, false);
//                            if (score > bestScore)
//                                bestScore = score;
//                        }
//                    }
//                }
//            }
//        } else {
//            bestScore = Integer.MAX_VALUE;
//            for (int i = 0; i < gameManager.getLines().length; i++) {
//                for (int j = 1; j < gameManager.getLines()[i].getMaklot().size() + 1; j++) {
//                    if (gameManager.getLines()[i].isEnough(j)) {
//                        gameManager.setTurn(1);
//                        if (gameManager.playTurn(i, j)) {
//                            int score = minimax(gameManager, depth + 1, true);
//                            if (score < bestScore)
//                                bestScore = score;
//                        }
//                    }
//                }
//            }
//        }
//
//        return bestScore;
//    }

}
