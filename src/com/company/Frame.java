package com.company;


import org.w3c.dom.Text;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class Frame {
    static JFrame frame;
    static JFrame controlJFrame;
    private static GameManager gameManager;
    private static Ai ai;
    private static Draw draw;
    private static int hintsLeft;

    public static void openGame(GameManager gameManager ,Ai ai) {
        if (frame == null) {
            frame = new JFrame("Draw a line");
            frame.setSize(1200, 800);
            frame.setVisible(true);
            hintsLeft = 1;

            Frame.gameManager = gameManager;
            Frame.ai = ai;
            printLines(gameManager.getLines());
            controlFrame();
            if (gameManager.getTurn() == 0){
                Move move = ai.playTurn(gameManager);
                gameManager.playTurn(move.row, move.amount);
            }
            printLines(gameManager.getLines());
        }
    }

    private static void controlFrame() {
        controlJFrame = new JFrame();
        JPanel enterT = new JPanel();
        JLabel tzam = new JLabel("enter number of sticks");
        JLabel tzam2 = new JLabel("enter row");
        JTextField tzag = new JTextField(20);
        JTextField tzag2 = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        JButton hintButton = new JButton("Hint");

        enterT.add(tzam2);
        enterT.add(tzam);
        enterT.add(tzag);
        enterT.add(tzag2);
        enterT.add(submitButton);
        enterT.add(hintButton);
        enterT.setLayout(new GridLayout(4, 2));
        controlJFrame.setSize(800, 125);
        controlJFrame.add(enterT);
        controlJFrame.setVisible(true);

        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hintsLeft>0) {
                    Ai ai = new Ai();
                    Move hint = ai.playTurn(gameManager);
                    hint.row++;
                    JLabel hintRow = new JLabel("Row:" + hint.row + ", Amount: " + hint.amount);
                    enterT.add(hintRow);
                    controlJFrame.setSize(800, 150);
                    hintsLeft--;
                    if (hintsLeft==0){
                        hintButton.setVisible(false);
                    }
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(tzag.getText());
                row--;
                int amount = Integer.parseInt(tzag2.getText());
                gameManager.playTurn(row, amount);
                printLines(gameManager.getLines());
                Move move = ai.playTurn(gameManager);
                gameManager.playTurn(move.row, move.amount);
                printLines(gameManager.getLines());
            }
        });
    }


    public static void printLines(ArrayList<Integer> number) {
        if (gameManager.getWinner().equals("none")) {
            JLabel title = new JLabel("THE GAME OF STICKS AND LINES!");
            JPanel enterT = new JPanel();

            int[] board = new int[4];
            board[0] = 1;
            board[1] = 3;
            board[2] = 5;
            board[3] = 7;
            title.setSize(300, 70);
            enterT.setSize(300, 100);
            draw = new Draw(number, board);

            frame.add(enterT);
            frame.add(draw);
            frame.setVisible(true);
        }
        else {
            JFrame jFrame = new JFrame();
            controlJFrame.setVisible(false);
            jFrame.setSize(100,100);
            JLabel winner = new JLabel("Winner :"  + gameManager.getWinner());
            jFrame.add(winner);
            jFrame.setVisible(true);
        }
    }

}