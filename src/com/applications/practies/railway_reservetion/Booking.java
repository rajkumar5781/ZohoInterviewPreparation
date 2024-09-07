package com.applications.practies.railway_reservetion;

import com.applications.railway_reservation_with_station.Passangers;

public class Booking {
    private int[] seats;
    private Passangers[] passangers;

    public Booking(int[] seats, Passangers[] passangers) {
        this.seats = seats;
        this.passangers = passangers;
    }

    public int[] getSeats() {
        return seats;
    }

    public void setSeats(int[] seats) {
        this.seats = seats;
    }

    public Passangers[] getPassangers() {
        return passangers;
    }

    public void setPassangers(Passangers[] passangers) {
        this.passangers = passangers;
    }

//    public static void
}
