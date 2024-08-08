package com.applications.railway_reservation_with_station;

public class Station {
    private Train train;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
    Station(Train train){
        this.train = train;
    }
}
