package com.applications.calltaxi;

public class Customer {
    private int customerId;
    private char pickUpPoint;
    private char dropPoint;
    private int pikcUpTime;
    private int travelAmount;

    public Customer(int customerId, char pickUpPoint, char dropPoint, int pikcUpTime,int travelAmount) {
        this.customerId = customerId;
        this.pickUpPoint = pickUpPoint;
        this.dropPoint = dropPoint;
        this.pikcUpTime = pikcUpTime;
        this.travelAmount = travelAmount;
    }

    public Customer() {
    }

    public int getTravelAmount() {
        return travelAmount;
    }

    public void setTravelAmount(int travelAmount) {
        this.travelAmount = travelAmount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public char getPickUpPoint() {
        return pickUpPoint;
    }

    public void setPickUpPoint(char pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }

    public char getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(char dropPoint) {
        this.dropPoint = dropPoint;
    }

    public int getPikcUpTime() {
        return pikcUpTime;
    }

    public void setPikcUpTime(int pikcUpTime) {
        this.pikcUpTime = pikcUpTime;
    }
}
