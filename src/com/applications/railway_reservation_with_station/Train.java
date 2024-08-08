package com.applications.railway_reservation_with_station;

import java.util.ArrayList;
import java.util.TreeMap;

public class Train {
    private int totalSeats;
    private int confirmedSeats;
    private int waitingSeats;
    private ArrayList<Integer> bookedSeats;
    private ArrayList<Integer> waitingList;
    private TreeMap<Integer,Passangers[]> waitingLists;

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getConfirmedSeats() {
        return confirmedSeats;
    }

    public void setConfirmedSeats(int confirmedSeats) {
        this.confirmedSeats = confirmedSeats;
    }

    public ArrayList<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(ArrayList<Integer> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public ArrayList<Integer> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(ArrayList<Integer> waitingList) {
        this.waitingList = waitingList;
    }

    public int getWaitingSeats() {
        return waitingSeats;
    }

    public void setWaitingSeats(int waitingSeats) {
        this.waitingSeats = waitingSeats;
    }

    public TreeMap<Integer, Passangers[]> getWaitingLists() {
        return waitingLists;
    }

    public void setWaitingLists(TreeMap<Integer, Passangers[]> waitingLists) {
        this.waitingLists = waitingLists;
    }

    public Train(int totalSeats, int waitingSeats) {
        this.totalSeats = totalSeats;
        this.waitingSeats = waitingSeats;
        this.bookedSeats = new ArrayList<>();
        this.waitingList = new ArrayList<>();
        this.waitingLists = new TreeMap<>();
    }
}
