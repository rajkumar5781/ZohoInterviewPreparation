package com.applications.railway_reservation_with_station;

public class Passangers {
    private String name;
    private int age;
    private String boardingStation;
    private String destination;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBoardingStation() {
        return boardingStation;
    }

    public void setBoardingStation(String boardingStation) {
        this.boardingStation = boardingStation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passangers(String name, int age, String boardingStation, String destination) {
        this.name = name;
        this.age = age;
        this.boardingStation = boardingStation;
        this.destination = destination;
    }
}
