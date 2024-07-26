package com.applications.calltaxi;

import java.util.ArrayList;
import java.util.List;

public class Taxi {
    private int totalEarning;
    private List<Customer> customerList;
    private int availableTime;
    private int currentPoint;
    private int taxiNum;
    private List<Booking> bookingList;

    public int getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(int totalEarning) {
        this.totalEarning = totalEarning;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public int getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(int availableTime) {
        this.availableTime = availableTime;
    }

    public int getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }

    public int getTaxiNum() {
        return taxiNum;
    }

    public void setTaxiNum(int taxiNum) {
        this.taxiNum = taxiNum;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public Taxi(int taxiNum) {
        this.totalEarning = 0;
        this.customerList = new ArrayList<>();
        this.availableTime = -1;
        this.currentPoint = 0;
        this.taxiNum = taxiNum;
        this.bookingList = new ArrayList<>();
    }
    public static void displayTaxi(Taxi[] taxis){
        for(int i=0;i<taxis.length;i++){
            if(taxis[i].getTotalEarning()>0){
                System.out.println("Taxi-"+(taxis[i].getTaxiNum()+1)+"      Total Earnings: Rs. "+taxis[i].getTotalEarning());
                for(Booking booking : taxis[i].getBookingList()){
                    System.out.println(booking.getBookingId()+"    "+booking.getCustomerId()+"    "+booking.getPickUpPoint()+"    "+booking.getDropPoint()+"    "+booking.getPikcUpTime()+"     "+(booking.getPikcUpTime()+Math.abs(booking.getPickUpPoint()-booking.getDropPoint()))+"   "+booking.getTravelAmount());
                }
            }
        }
    }
}
