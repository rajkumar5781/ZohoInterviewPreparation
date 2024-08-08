package com.applications.railway_reservation_with_station;

public class ConfiremedPassangers extends Passangers{

    private int seatNum;

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }


    ConfiremedPassangers(int seatNum,Passangers passangers){
        super(passangers.getName(),passangers.getAge(),passangers.getBoardingStation(),passangers.getDestination());
        this.seatNum = seatNum;
    }

    public ConfiremedPassangers(String name, int age, String boardingStation, String destination) {
        super(name, age, boardingStation, destination);
    }
}
