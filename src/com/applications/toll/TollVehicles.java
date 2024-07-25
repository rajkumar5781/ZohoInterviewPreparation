package com.applications.toll;

public class TollVehicles {
    private int price;
    private String type;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TollVehicles(int price, String type) {
        this.price = price;
        this.type = type;
    }
}
