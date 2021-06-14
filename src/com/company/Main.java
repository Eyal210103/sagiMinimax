package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GameManager gm = new GameManager(4);
        gm.print();
        Ai ai = new Ai();

        Frame frame = new Frame();

        Scanner sc = new Scanner(System.in);

        while (gm.getWinner().equals("none")) {
            if (gm.getTurn() == 0) {
                System.out.println("AI");
                Move move = ai.playTurn(gm);
                gm.playTurn(move.row,move.amount);
            } else {
                System.out.println("player " + (gm.getTurn() + 1) + " its your turn");
                System.out.println("choose row");
                int row = Integer.parseInt(sc.nextLine());
                row--;
                System.out.println("choose amount");
                int amount = Integer.parseInt(sc.nextLine());
                if (!gm.playTurn(row, amount)){
                    System.out.println("AGAIN");
                }
            }
            gm.print();
            System.out.println("\n");

        }
        System.out.println("Winner " + gm.getWinner());
    }
}
