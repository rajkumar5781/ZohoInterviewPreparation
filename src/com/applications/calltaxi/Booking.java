package com.applications.calltaxi;

public class Booking extends Customer{
    private int bookingId;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

//    public Booking(int customerId, char pickUpPoint, char dropPoint, int pikcUpTime, int travelAmount,int bookingId) {
//        super(customerId, pickUpPoint, dropPoint, pikcUpTime, travelAmount,bookingId);
//        this.bookingId = bookingId;
//
//    }

    public Booking(int customerId, char pickUpPoint, char dropPoint, int pikcUpTime, int travelAmount, int bookingId) {
        super(customerId, pickUpPoint, dropPoint, pikcUpTime, travelAmount);
        this.bookingId = bookingId;
    }

    public Booking(int bookingId) {
        this.bookingId = bookingId;
    }
}
