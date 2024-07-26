package com.applications.calltaxi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner sc;
    public static Taxi[] taxis;
    public static int taxiCount = 4;
    public static HashMap<Integer,Customer> customerHashMap = new HashMap<>();
    public static int totalPoint = 5;
    public static int bookingId = 1;
    public static void main(String[] args){
        sc = new Scanner(System.in);
        taxis = new Taxi[taxiCount];
        initTaxis();
        while(true){
            System.out.println("1.Call taxi booking");
            System.out.println("2.Display the Taxi details");
            int choose = sc.nextInt();
            switch (choose){
                case 1:{
                    getInputs();
                    break;
                }
                case 2:{
                    Taxi.displayTaxi(taxis);
                    break;
                }
            }
        }
    }
    public static void initTaxis(){
        for(int i=0;i<taxiCount;i++){
            taxis[i] = new Taxi(i);
        }
    }
    public static void getInputs(){
        System.out.println("Enter the customerId");
        int id = sc.nextInt();
        if(customerHashMap.getOrDefault(id,null)!=null){
            System.out.println("The customerId already there...");
            return;
        }
        System.out.println("Pickup Point:");
        char pickPoint = sc.next().charAt(0);
        System.out.println("Drop Point:");
        char dropPoint = sc.next().charAt(0);
        System.out.println("Pickup Time:");
        int time = sc.nextInt();
        int index = nearestTaxi(pickPoint-65,time);
        if(index!=-1){
            int amount = getAmount(pickPoint-65,dropPoint-65);
            Customer customer = new Customer(id,pickPoint,dropPoint,time,amount);
            Booking booking = new Booking(id,pickPoint,dropPoint,time,amount,bookingId++);
            customerHashMap.put(id,customer);
            List<Booking> bookingList = new ArrayList<>(taxis[index].getBookingList());
            bookingList.add(booking);
//            customerList.add(customer);
            taxis[index].setTotalEarning(taxis[index].getTotalEarning()+amount);
//            taxis[index].setCustomerList(customerList);
            taxis[index].setBookingList(bookingList);
            if(taxis[index].getAvailableTime()==-1){
                taxis[index].setAvailableTime(pickPoint-65);
            }
            taxis[index].setCurrentPoint(dropPoint-65);
//            (booking.getPikcUpTime()+Math.abs(booking.getPickUpPoint()-booking.getDropPoint()))
//            taxis[index].getAvailableTime()+Math.abs((pickPoint-65)-(dropPoint-65))
            taxis[index].setAvailableTime((booking.getPikcUpTime()+Math.abs(booking.getPickUpPoint()-booking.getDropPoint())));
            System.out.println("Taxi can be allotted.");
            System.out.println("Taxi-"+(index+1)+" is allotted");
        }
    }
    public static int nearestTaxi(int pickUpPoint,int pickUpTime){
        int index = -1,min = Integer.MAX_VALUE;
        List<Taxi> tempTaxi = new ArrayList<>();
        for(int i=0;i<taxiCount;i++){
            if(pickUpTime>taxis[i].getAvailableTime() && min>Math.abs(pickUpPoint-taxis[i].getCurrentPoint())){
                tempTaxi = new ArrayList<>();
                tempTaxi.add(taxis[i]);
                min = Math.abs(pickUpPoint-taxis[i].getCurrentPoint());
            } else if (pickUpTime>taxis[i].getAvailableTime() && min==Math.abs(pickUpPoint-taxis[i].getCurrentPoint())) {
                tempTaxi.add(taxis[i]);
            }
        }
        if(!tempTaxi.isEmpty() && tempTaxi.size()>1){
            int amount = Integer.MAX_VALUE;
            Taxi temp = null;
            for(Taxi taxi : tempTaxi){
                if(amount>taxi.getTotalEarning()){
                    temp = taxi;
                    amount = taxi.getTotalEarning();
                }
            }
            if(temp!=null){
                index = temp.getTaxiNum();
            }
        }
        else if(!tempTaxi.isEmpty()){
            index = tempTaxi.get(0).getTaxiNum();
        }
        return index;
    }
    public static int getAmount(int pickPoint,int dropPoint){
        int amount = 0;
        if(pickPoint>dropPoint){
            for(int i=pickPoint;i>=dropPoint;i--){
                amount = amount + 200;
            }
        }
        else{
            for(int i=pickPoint;i<=dropPoint;i++){
                amount = amount + 200;
            }
        }
        return amount;
    }
}
