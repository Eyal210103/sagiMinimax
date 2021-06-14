package com.company;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Draw extends JPanel {
    ArrayList<Integer> rows;// value of empty pieces
    int[] board;// value of board size

    public Draw(ArrayList<Integer> rows, int[] board) {// Initialize values
        this.rows = rows;
        this.board = board;
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        JPanel maingame = new JPanel();
        super.paintComponent(g);
        int offsetY = 120; // Y axis offset
        int offsetX = 100; // X axis offset
        int markerSize = 25; // maker size
        int gap = 1; // gap size
        Graphics2D g2 = (Graphics2D) g;

        // print parallel board numbers and lines
        for (int i = 1; i <= rows.size(); i++) {
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.DARK_GRAY);
            g2.drawString(String.valueOf(i), offsetX - 18, offsetY * i + 12);
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine((offsetX) - 15, offsetY * i - 4, (offsetX) * (getBiggestNumber(board) + 1), offsetY * i - 4);
        }

        // print perpendicular board numbers and lines
        for (int i = 1; i <= getBiggestNumber(board); i++) {
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.DARK_GRAY);
            g2.drawString(String.valueOf(i), offsetX * i - 2, offsetY - 5);
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine((offsetX) * i - 8, offsetY - 15, (offsetX) * i - 8, offsetY * (board.length + 1));
        }

        // print board game pieces
        for (int boardY = 1; boardY <= rows.size(); boardY++) {
            for (int boardX = 1; boardX <= rows.get(boardY-1); boardX++) {
                //print sticks
                g2.setStroke(new BasicStroke(2));
                g2.setColor(Color.gray);
                g2.drawLine(offsetX * boardX + 42, offsetY * boardY + 15, offsetX * boardX + 42, offsetY * boardY + gap + 80);


                //print line
//                g2.setStroke(new BasicStroke(2));
//                g2.setColor(Color.gray);
//                g2.drawLine(offsetX * boardX+42, offsetY * boardY+ 15, offsetX * boardX+42, offsetY * boardY + gap+80);
//                // if game piece is missing print red X on it
//                if (boardX > rows.get(boardY - 1)) {
//                    g2.setColor(Color.red);
//                    g2.drawLine(offsetX * boardX - markerSize + 20, offsetY * boardY + 110, offsetX * boardX + markerSize + 65 ,
//                            offsetY * boardY + gap);
//                    g2.drawLine(offsetX * boardX + markerSize+ 65, offsetY * boardY + 115, offsetX * boardX - markerSize + 20 ,
//                            offsetY * boardY + gap);
//
//                }
            }
        }
    }

    /**
     * get array and retiring the biggest value
     *
     * @param number the array
     * @return the biggest value
     */
    private int getBiggestNumber(int[] number) {
        int temp = 0;
        for (int i = 0; i < number.length; i++) {
            if (temp < number[i]) {
                temp = number[i];
            }
        }
        return temp;
    }
}