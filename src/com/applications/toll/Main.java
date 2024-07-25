package com.applications.toll;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner sc;
    public static List<Vehicles> vehiclesList = new ArrayList<>();
    public static List<Toll> tollList = new ArrayList<>();
    public static void main(String[] args){
        sc = new Scanner(System.in);
        System.out.println("Enter the number of toll:");
        int total = sc.nextInt();
        Toll[] tolls = new Toll[total];
        for(int i=0;i<total;i++){
            tolls[i] = new Toll((char)('A'+i)+"");
            tolls[i].setName((char)('A'+i)+"");
        }
        while(true){
            System.out.println("1.Enter the vehicles details to travel:");
            System.out.println("2.Display the details of all tolls");
            System.out.println("3.Display the details of all vehicles");
            int choose = sc.nextInt();
            if(choose<1 || choose>3){
                System.out.println("Enter the correct option...");
            }
            else{
                switch (choose){
                    case 1:{
                        getVehiclesInfo(tolls);
                        break;
                    }
                    case 2:{
                        Toll.displayAllTolls(tolls);
                        break;
                    }
                    case 3:{
                        Vehicles.displayAllVehicles(vehiclesList);
                        break;
                    }
                }
            }
        }
    }
    public static void getVehiclesInfo(Toll[] tolls){
        System.out.println("Enter the vehicles type:");
        String type = sc.next();
        System.out.println("Enter the source:");
        String source = sc.next();
        System.out.println("Enter the destination:");
        String destination = sc.next();
        System.out.println("Your are VIP means 1:");
        int isVIP = sc.nextInt();
        int totalPrice = 0;
        tollList = new ArrayList<>();
        if(placesValidate(source,tolls) && placesValidate(destination,tolls)){
            int sourceIndex=findIndex(tolls,source),destinationIndex = findIndex(tolls,destination);
            if(sourceIndex<destinationIndex){
                if(destinationIndex-sourceIndex>(sourceIndex+tolls.length-destinationIndex)){
                    totalPrice = totlPrice(tolls,sourceIndex,destinationIndex,type,isVIP==1,false,type);
                }
                else{
                    totalPrice = totlPrice(tolls,sourceIndex,destinationIndex,type,isVIP==1,true,type);
                }
            } else if (sourceIndex>destinationIndex) {
                if(sourceIndex-destinationIndex>(destinationIndex+sourceIndex)){
                    totalPrice = totlPrice(tolls,sourceIndex,destinationIndex,type,isVIP==1,false,type);
                }
                else{
                    totalPrice = totlPrice(tolls,sourceIndex,destinationIndex,type,isVIP==1,true,type);
                }
            }
//            int totalPrice = totlPrice(tolls,source,destination,type,isVIP==1);
            System.out.println("Total price:"+totalPrice);
            Vehicles vehicles = new Vehicles(isVIP==1,source,destination,type);
            vehicles.setAmount(totalPrice);

            vehicles.setCrossedTolls(tollList);
            vehiclesList.add(vehicles);
//            applyTheTollPrices(tolls,source,destination,type,isVIP==1);
        }
        else{
            System.out.println("Enter the valid sources....");
        }
    }
    public static boolean placesValidate(String place,Toll[] tolls){
        for(Toll toll : tolls){
            if(toll.getPlace().equals(place)){
                return true;
            }
        }
        return false;
    }
    public static void applyTheTollPrices(Toll[] tolls,String source,String destination,String type,boolean isVIP){
        boolean isEnd = false,isStrar=false;
        for(int i=0;i<tolls.length && !isEnd;i++){
            if(!isStrar && tolls[i].getPlace().equals(source)){
                isStrar = true;
                List<TollVehicles> tollVehicles = new ArrayList<>(tolls[i].getVehiclesList() == null ? new ArrayList<>() : tolls[i].getVehiclesList());
                int amount = tolls[i].getVehicalPrices().get(type);
                if(isVIP){
                    amount = ((tolls[i].getVehicalPrices().get(type))/100)*tolls[i].getVipDiscount();
                }
                tollVehicles.add(new TollVehicles(amount,type));
                tolls[i].setTotalAmount(tolls[i].getTotalAmount()+amount);
                tolls[i].setVehiclesList(tollVehicles);
            }
            else if (isStrar && tolls[i].getPlace().equals(destination)) {
                List<TollVehicles> tollVehicles = new ArrayList<>(tolls[i].getVehiclesList()==null ? new ArrayList<>() : tolls[i].getVehiclesList());
                int amount = tolls[i].getVehicalPrices().get(type);
                if(isVIP){
                    amount = ((tolls[i].getVehicalPrices().get(type))/100)*tolls[i].getVipDiscount();
                }
                tollVehicles.add(new TollVehicles(amount,type));
                tolls[i].setTotalAmount(tolls[i].getTotalAmount()+amount);
                tolls[i].setVehiclesList(tollVehicles);
                return;
            }
            else if(isStrar){
                List<TollVehicles> tollVehicles = new ArrayList<>(tolls[i].getVehiclesList()==null ? new ArrayList<>() : tolls[i].getVehiclesList());
                int amount = tolls[i].getVehicalPrices().get(type);
                if(isVIP){
                    amount = ((tolls[i].getVehicalPrices().get(type))/100)*tolls[i].getVipDiscount();
                }
                tollVehicles.add(new TollVehicles(amount,type));
                tolls[i].setTotalAmount(tolls[i].getTotalAmount()+amount);
                tolls[i].setVehiclesList(tollVehicles);
            }
        }
    }
    public static int totlPrice(Toll[] tolls,int sourceIndex,int endIndex,String vehicleType,boolean isVIP,boolean isIn,String type){
        int total = 0;
        if(isIn) {
            for (int i = sourceIndex; i < endIndex; i++) {
                int amount = tolls[i].getVehicalPrices().get(vehicleType);
                if (isVIP) {
                    amount = ((tolls[i].getVehicalPrices().get(vehicleType) / 100) * tolls[i].getVipDiscount());
                }
                List<TollVehicles> tollVehicles = new ArrayList<>(tolls[i].getVehiclesList()==null ? new ArrayList<>() : tolls[i].getVehiclesList());
                tollVehicles.add(new TollVehicles(amount,type));
                tolls[i].setTotalAmount(tolls[i].getTotalAmount()+amount);
                tolls[i].setVehiclesList(tollVehicles);
                tollList.add(tolls[i]);
                total = total + amount;
            }
        }
        else{
            boolean destinationReadched = false;
            for (int i = sourceIndex; i >=0; i--) {
                int amount = tolls[i].getVehicalPrices().get(vehicleType);
                if (isVIP) {
                    amount = ((tolls[i].getVehicalPrices().get(vehicleType) / 100) * tolls[i].getVipDiscount());
                }
                total = total + amount;
                List<TollVehicles> tollVehicles = new ArrayList<>(tolls[i].getVehiclesList()==null ? new ArrayList<>() : tolls[i].getVehiclesList());
                tollVehicles.add(new TollVehicles(amount,type));
                tolls[i].setTotalAmount(tolls[i].getTotalAmount()+amount);
                tolls[i].setVehiclesList(tollVehicles);
                tollList.add(tolls[i]);
                if(i==endIndex){
                    destinationReadched = true;
                    break;
                }
            }
            if(!destinationReadched){
                for (int i = tolls.length-1; i>endIndex; i--) {
                    int amount = tolls[i].getVehicalPrices().get(vehicleType);
                    if (isVIP) {
                        amount = ((tolls[i].getVehicalPrices().get(vehicleType) / 100) * tolls[i].getVipDiscount());
                    }
                    List<TollVehicles> tollVehicles = new ArrayList<>(tolls[i].getVehiclesList()==null ? new ArrayList<>() : tolls[i].getVehiclesList());
                    tollVehicles.add(new TollVehicles(amount,type));
                    tolls[i].setTotalAmount(tolls[i].getTotalAmount()+amount);
                    tolls[i].setVehiclesList(tollVehicles);
                    tollList.add(tolls[i]);
                    total = total + amount;
                }
            }
        }
        return total;
    }
    public static List<Toll> crossedTolls(Toll[] tolls,String start,String end){
        List<Toll> tollList = new ArrayList<>();
        boolean isEnd = false,isStrar=false;
        for(int i=0;i<tolls.length && !isEnd;i++){
            if(!isStrar && tolls[i].getPlace().equals(start)){
                isStrar = true;
                tollList.add(tolls[i]);
            }
            else if (isStrar && tolls[i].getPlace().equals(end)) {
                tollList.add(tolls[i]);
                return tollList;
            }
            else if(isStrar){
                tollList.add(tolls[i]);
            }
        }
        return tollList;
    }
    public static int findIndex(Toll[] tolls,String places){
        int index = -1;
        for(int i=0;i<tolls.length;i++){
            if(tolls[i].getPlace().equals(places)){
                index = i;
                break;
            }
        }
        return index;
    }
}
