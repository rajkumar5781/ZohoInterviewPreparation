package com.applications.railway_reservation;

import java.util.ArrayList;
import java.util.Arrays;

public class Coach {
    private int lowerSeatsCount;
    private int middleSeatsCount;
    private int upperSeatsCount;
    private int RACSeatsCount;
    private int waitingSeatsCount;
    private ArrayList<Integer> lowerSeats;
    private ArrayList<Integer> middleSeats;
    private ArrayList<Integer> upperSeats;
    private ArrayList<Integer> RACSeat;
    private ArrayList<Integer> waitingSeat;

    public int getLowerSeatsCount() {
        return lowerSeatsCount;
    }

    public void setLowerSeatsCount(int lowerSeatsCount) {
        this.lowerSeatsCount = lowerSeatsCount;
    }

    public int getMiddleSeatsCount() {
        return middleSeatsCount;
    }

    public void setMiddleSeatsCount(int middleSeatsCount) {
        this.middleSeatsCount = middleSeatsCount;
    }

    public int getUpperSeatsCount() {
        return upperSeatsCount;
    }

    public void setUpperSeatsCount(int upperSeatsCount) {
        this.upperSeatsCount = upperSeatsCount;
    }

    public int getRACSeatsCount() {
        return RACSeatsCount;
    }

    public void setRACSeatsCount(int RACSeatsCount) {
        this.RACSeatsCount = RACSeatsCount;
    }

    public int getWaitingSeatsCount() {
        return waitingSeatsCount;
    }

    public void setWaitingSeatsCount(int waitingSeatsCount) {
        this.waitingSeatsCount = waitingSeatsCount;
    }

    public Coach(int confirmSeatsCount, int RACSeatsCount, int waitingSeatsCount) {
        this.RACSeatsCount = RACSeatsCount;
        this.waitingSeatsCount = waitingSeatsCount;
        this.lowerSeatsCount = confirmSeatsCount;
        this.upperSeatsCount = confirmSeatsCount;
        this.middleSeatsCount = confirmSeatsCount;
        this.lowerSeats = new ArrayList<>(Arrays.asList(confirmSeatsCount));
        this.middleSeats = new ArrayList<>(Arrays.asList(confirmSeatsCount));
        this.upperSeats = new ArrayList<>(Arrays.asList(confirmSeatsCount));
    }
}
