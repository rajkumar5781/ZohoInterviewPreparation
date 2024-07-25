package com.applications.toll;

import java.util.ArrayList;
import java.util.List;

public class Vehicles {
    private String type;
    private String source;
    private String destination;
    private int amount;
    private boolean isVIP;
    private List<Toll> crossedTolls;
    private int totalAmount;

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Toll> getCrossedTolls() {
        return crossedTolls;
    }

    public void setCrossedTolls(List<Toll> crossedTolls) {
        this.crossedTolls = crossedTolls;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public Vehicles(String type, String source, String destination) {
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.crossedTolls = new ArrayList<>();
    }

    public Vehicles(boolean isVIP, String destination, String source, String type) {
        this.isVIP = isVIP;
        this.destination = destination;
        this.source = source;
        this.type = type;
        this.crossedTolls = new ArrayList<>();
    }

public static void displayAllVehicles(List<Vehicles> vehiclesList){
        System.out.println("Vehicles type       TotalAmount    Tolls");
        for (Vehicles vehicles : vehiclesList){

            String s = "";
            for(Toll toll:vehicles.getCrossedTolls()){
                if (s == "") {
                    s=toll.getPlace();
                }
                else{
                    s = s+","+toll.getPlace();
                }
            }
//            vehicles.getCrossedTolls().stream().map((toll -> toll.getName())).toArray().toString();
//                    .forEach((toll -> {
//                if (s == "") {
//                    toll.getName();
//                }
//                else{
//                    s = s+","+toll.getName();
//                }
//            }
//            ));
            System.out.println(vehicles.getType()+"   "+vehicles.getAmount()+"    "+s);
        }
}
}
