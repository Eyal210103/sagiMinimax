package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        GameManager gm = new GameManager(4);
        Ai ai = new Ai();
        Frame.openGame(gm,ai);
    }
}
