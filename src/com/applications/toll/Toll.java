package com.applications.toll;
import java.util.HashMap;
import java.util.List;

public class Toll {
    private HashMap<String,Integer> vehicalPrices;
    private int vipDiscount = 20;
    private String place;
    private List<TollVehicles> vehiclesList;
    private String name="";
    private int totalAmount;

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TollVehicles> getVehiclesList() {
        return vehiclesList;
    }

    public void setVehiclesList(List<TollVehicles> vehiclesList) {
        this.vehiclesList = vehiclesList;
    }

    public HashMap<String , Integer> getVehicalPrices() {
        return vehicalPrices;
    }

    public void setVehicalPrices(HashMap<String, Integer> vehicalPrices) {
        this.vehicalPrices = vehicalPrices;
    }

    public int getVipDiscount() {
        return vipDiscount;
    }

    public void setVipDiscount(int vipDiscount) {
        this.vipDiscount = vipDiscount;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    Toll(String place){
        vehicalPrices = new HashMap<>();
        vehicalPrices.put("car",100);
        vehicalPrices.put("bus",80);
        vehicalPrices.put("bike",50);
        this.place = place;
    }
    public static void displayAllTolls(Toll[] tolls){
        System.out.println("Name      TotalAmount");
        for(Toll toll:tolls){
            System.out.println(toll.getName()+"    "+toll.getTotalAmount());
        }
    }
}
