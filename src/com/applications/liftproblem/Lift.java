package com.applications.liftproblem;

import java.util.List;

public class Lift {
    private int floor;
    private List<Integer> restrictFloor;
    private int liftNum;
    private boolean isMaintenance = false;
    private int numOfPerson=5;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public List<Integer> getRestrictFloor() {
        return restrictFloor;
    }

    public void setRestrictFloor(List<Integer> restrictFloor) {
        this.restrictFloor = restrictFloor;
    }

    public Lift(int floor, List<Integer> restrictFloor) {
        this.floor = floor;
        this.restrictFloor = restrictFloor;
    }

    public Lift(int floor,int liftNum) {
        this.floor = floor;
        this.liftNum = liftNum;
    }

    public Lift(int floor,int liftNum,boolean isMaintenance) {
        this.floor = floor;
        this.liftNum = liftNum;
        this.isMaintenance = isMaintenance;
        if(isMaintenance){
            this.floor = -1;
        }
    }

    public int getLiftNum() {
        return liftNum;
    }

    public void setLiftNum(int liftNum) {
        this.liftNum = liftNum;
    }

    public boolean isMaintenance() {
        return isMaintenance;
    }

    public void setMaintenance(boolean maintenance) {
        if(maintenance){
            this.floor = -1;
        }
        isMaintenance = maintenance;
    }

    public int getNumOfPerson() {
        return numOfPerson;
    }

    public void setNumOfPerson(int numOfPerson) {
        this.numOfPerson = numOfPerson;
    }

    public static void printLifts(Lift[] lifts){
        String lift = "",floors="";
        for(int i=0;i<lifts.length;i++){
            lift = lift+(i+1)+" ";
            floors = floors+lifts[i].getFloor()+" ";
        }
        System.out.println("Lift : "+lift);
        System.out.println("Floor : "+floors);
    }
}
