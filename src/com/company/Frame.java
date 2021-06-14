package com.company;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Frame {
    static JFrame frame;
    private static GameManager gameManager;
    private static Ai ai;
    private static Draw draw;

    public static void main(String[] args) {
        if (frame == null) {
            frame = new JFrame("Draw a line");
            frame.setSize(1200, 800);
            frame.setVisible(true);

            gameManager = new GameManager(4);
            ai = new Ai();
            printLines(gameManager.getLines());
            controlFrame();
        }
    }

    private static void controlFrame() {
        JFrame jFrame = new JFrame();
        JPanel enterT = new JPanel();
        JLabel tzam = new JLabel("enter number of sticks");
        JLabel tzam2 = new JLabel("enter row");
        JTextField tzag = new JTextField(20);
        JTextField tzag2 = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        enterT.add(tzam2);
        enterT.add(tzam);
        enterT.add(tzag);
        enterT.add(tzag2);
        enterT.add(submitButton);
        enterT.setLayout(new GridLayout(3, 2));
        jFrame.setSize(800, 100);
        jFrame.add(enterT);
        jFrame.setVisible(true);

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
        JLabel title = new JLabel("THE GAME OF STICKS AND LINES!");
        JPanel enterT = new JPanel();
//        JLabel tzam = new JLabel("enter number of sticks");
//        JLabel tzam2 = new JLabel("enter row");
//        JTextField tzag = new JTextField(20);
//        JTextField tzag2 = new JTextField(20);
//        JButton submitButton = new JButton("Submit");
//        enterT.add(tzam);
//        enterT.add(tzam2);
//        enterT.add(tzag);
//        enterT.add(tzag2);
//        enterT.add(submitButton);
//        enterT.setLayout(new GridLayout(3,2));

        int[] board = new int[4];
        board[0] = 1;
        board[1] = 3;
        board[2] = 5;
        board[3] = 7;
        title.setSize(300, 70);
        enterT.setSize(300, 100);
        draw = new Draw(number, board);
//		frame.add(title);
//		title.setLocation(120, 20);
//		frame.add(enterT);
//		enterT.setLocation(860,100);
        frame.add(enterT);
        frame.add(draw);
        frame.setVisible(true);

//        submitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int row = Integer.parseInt(tzag.getText());
//                row++;
//                int amount = Integer.parseInt(tzag2.getText());
//                gameManager.playTurn(row,amount);
//                draw = new Draw(gameManager.getLines(),board);
//                frame.add(draw);
//                //Move move = ai.playTurn(gameManager);
//                //gameManager.playTurn(move.row,move.amount);
//
//                //draw = new Draw(gameManager.getLines(),board);
//                //frame.add(draw);
//            }
//        });
    }

}