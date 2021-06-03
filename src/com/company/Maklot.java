package com.company;

import java.util.ArrayList;
public class Maklot {
    private Boolean[] maklot;

    public Maklot(int num) {
        maklot = new Boolean[num];
        for(int i  = 0; i<num ; i++) {
            maklot[i] =  true;
        }
    }

    public void removeNumOfMaklot(int num) {
        int count = 0;
        for(int i  = 0; i<maklot.length; i++) {
            if(maklot[i]) {
                maklot[i] = false;
                count++;
                if (count == num){
                    return;
                }
            }
        }
    }

    public boolean isEnough(int num) {
        int count= 0 ;
        for(int i  = 0; i<maklot.length ; i++) {
            if(maklot[i]) {
                count++;
            }
        }
        return count >= num;
    }

    public boolean isGotOne() {
        int count = 0;
        for(int i  = 0; i<maklot.length ; i++) {
            if(maklot[i]) {
                count++;
            }
        }
        return count == 1;
    }
    public boolean isEmpty() {
        int count = 0;
        for(int i  = 0; i<maklot.length ; i++) {
            if(maklot[i]) {
                count++;
            }
        }
        return count == 0;
    }
//    public boolean isGotMoreThanOneOrZero() {
//        int count = 0;
//        for(int i  = 0; i<maklot.length ; i++) {
//            if(maklot[i]) {
//                count++;
//            }
//        }
//        return count > 1 || count == 0;
//    }

    public boolean isAllOut() {
        for(int i  = 0; i<maklot.length ; i++) {
            if(maklot[i])
                return false;
        }
        return true;
    }

    public int getAmount() {
        return maklot.length;
    }

    public int getLeftAmount(){
        int count= 0;
        for (Boolean makel:maklot) {
            if (makel)
                count++;
        }
        return count;
    }

    public void addNumOfMaklot(int x) {
        int count = 0;
        for (int i = 0; i<this.getMaklot().length;i++) {
            if (!this.getMaklot()[i]) {
                maklot[i] = true;
                count++;
                if (count == x) {
                    return;
                }
            }
        }
    }

    public Boolean[] getMaklot() {
        return maklot;
    }

    public boolean isThere(int index) {
        return maklot[index];
    }

}
