package com.applications.railway_reservation_with_station;

import java.util.*;

public class Main {
    public static HashMap<Integer,ConfiremedPassangers[]> pnr;
    public static TreeMap<Integer,ConfiremedPassangers[]> WaitingLists;
    public static Scanner sc;
    public static Station[][] stations;
    public static ArrayList<String> stationName;
    public static int pnrNum = 1;
    public static int waitingListId = 1;
    public static void main(String[] args){
        pnr = new HashMap<>();
        sc = new Scanner(System.in);
        stationName = new ArrayList<>();
        WaitingLists = new TreeMap<>();
        initializeStation();
        while(true){
            System.out.println("Book ticket press 1:");
            System.out.println("cancel ticket press 2:");
            int choose = sc.nextInt();
            switch (choose){
                case 1:{
                    bookTicket();
                    break;
                }
                case 2:{
                    getCancelTicketsDetails();
                    break;
                }
            }
            printStation();
        }
    }
    public static void initializeStation(){
        System.out.println("Enter the number of station:");
        int station_Count = sc.nextInt();
        stations = new Station[station_Count][station_Count];
        for(int i=0;i<station_Count;i++){
            for(int j=0;j<station_Count;j++){
                stations[i][j] = new Station(new Train(8,2));
            }
        }
        for(int i=0;i<station_Count;i++){
            stationName.add(sc.next());
        }
    }
    public static void printStation(){
        System.out.print("  ");
        for(int i=1;i<=stations[0][0].getTrain().getTotalSeats();i++){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i=0;i<stations.length;i++){
            System.out.print(stationName.get(i)+" ");
            for(int j=1;j<=stations[i][i].getTrain().getTotalSeats();j++){
                if( stations[i][i].getTrain().getBookedSeats().contains(j)){
                    System.out.print("* ");
                }
                else{
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
    public static void bookTicket(){
        System.out.println("Group booking press 1:");
        System.out.println("Single booking press 2:");
        int choose = sc.nextInt();
        switch (choose){
            case 1:{
                groupBooking();
                break;
            }
            case 2:{
                singleBooking();
                break;
            }
        }
    }
    public static void groupBooking(){
        System.out.println("Enter the number of persons to book:");
        int num = sc.nextInt();
        int[] places = getSourceAndDestination();
        Passangers[] passangers = new Passangers[num];
        ArrayList<Integer> allocateSeats = isSeatsAvailable(places,num);
        if(allocateSeats.size()<num){
            allocateSeats = isWaitingAvailable(places,num);
            if(allocateSeats.size()<=num){
                passangers = getPassangersDetails(num,places);
//                WaitingLists.put(waitingListId,passangers);
                bookWaitingSeats(places,allocateSeats,num,passangers);
            }
        }
        else {
            passangers = getPassangersDetails(num,places);
            bookSeats(places,allocateSeats,num,passangers);
//            pnr.put(pnrNum,passangers);
            pnrNum++;
        }
    }
    public static Passangers[] getPassangersDetails(int num,int[] places){
        Passangers[] passangers = new Passangers[num];
        for (int i = 0; i < num; i++) {
            System.out.println("Enter the name");
            String name = sc.next();
            System.out.println("Enter the age:");
            int age = sc.nextInt();
            passangers[i] = new Passangers(name,age,stationName.get(places[0]),stationName.get(places[1]));
        }
        return passangers;
    }
    public static void singleBooking(){
        int[] places = getSourceAndDestination();
        Passangers[] passangers = new Passangers[1];
        ArrayList<Integer> allocateSeats = isSeatsAvailable(places,1);
        if(allocateSeats.size()<1){
            allocateSeats = isWaitingAvailable(places,1);
            if(allocateSeats.size()<=1){
                passangers = getPassangersDetails(1,places);

                bookWaitingSeats(places,allocateSeats,1,passangers);
            }
        }
        else {
            passangers = getPassangersDetails(1,places);
            bookSeats(places,allocateSeats,1,passangers);
//            pnr.put(pnrNum,passangers);
            pnrNum++;
        }
    }
    public static int[] getSourceAndDestination(){
        System.out.println("Enter the source:");
        String source = sc.next();
        System.out.println("Enter the destination:");
        String destination = sc.next();
        return new int[]{stationName.indexOf(source),stationName.indexOf(destination)};
    }
    public static ArrayList<Integer> isSeatsAvailable(int[] places,int num){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1;i<=stations[0][0].getTrain().getTotalSeats();i++) {
            list.add(i);
        }
        for(int i=places[0];i<places[1];i++){
            for(int j : stations[0][i].getTrain().getBookedSeats()){
                if(list.contains(j)){
                    list.remove(list.indexOf(j));
                }
            }
            if(list.size()<num){
                return new ArrayList<>();
            }
        }
        return list;
    }
    public static ArrayList<Integer> isWaitingAvailable(int[] places,int num){
        ArrayList<Integer> list = new ArrayList<>();


        for(int i=1;i<=stations[0][0].getTrain().getWaitingSeats();i++) {
            list.add(i);
        }
        for(int i=places[0];i<places[1];i++){
            for(int j : stations[0][i].getTrain().getWaitingList()){
                if(list.contains(j)){
                    list.remove(list.indexOf(j));
                }
            }
            if(list.size()<num){
                return new ArrayList<>();
            }
        }
        return list;
    }
    public static ArrayList<Integer> allocateSeats(int num,int total){
        ArrayList<Integer> allocateSeat = new ArrayList<>(stations[num][num].getTrain().getBookedSeats());
        for(int i=1,count=0;i<=stations[num][num].getTrain().getTotalSeats() && count<total;i++){
            if(!allocateSeat.contains(i)){
                allocateSeat.add(i);
                count++;
            }
        }
        return allocateSeat;
    }
    public static void bookWaitingSeats(int[] places,ArrayList<Integer> allocateSeats,int num,Passangers[] passangers){
//        ArrayList<Integer> seats = new ArrayList<>();
//        int count = 0;
//
//        for(int i=0;count<num && i<allocateSeats.size();i++,count++){
//            seats.add(allocateSeats.get(i));
//        }

        ArrayList<Integer> seats = new ArrayList<>();
        ConfiremedPassangers[] confiremedPassangers = new ConfiremedPassangers[passangers.length];
        int count = 0;
        for(int i=0;count<num && i<allocateSeats.size();i++,count++){
            seats.add(allocateSeats.get(i));
            confiremedPassangers[count] = new ConfiremedPassangers(allocateSeats.get(i),passangers[count]);
        }
        for(int i=0;i<stationName.size();i++){
            for(int j=places[0];j<places[1];j++){
                ArrayList<Integer> a = new ArrayList<>(stations[i][j].getTrain().getWaitingList());
                a.addAll(seats);
                stations[i][j].getTrain().setWaitingList(a);
                TreeMap<Integer,Passangers[]> waitingList = new TreeMap<>(stations[i][j].getTrain().getWaitingLists());
                waitingList.put(waitingListId,passangers);
                stations[i][j].getTrain().setWaitingLists(waitingList);
            }
        }
        WaitingLists.put(waitingListId,confiremedPassangers);
        waitingListId++;
    }
    public static void bookSeats(int[] places,ArrayList<Integer> allocateSeats,int num,Passangers[] passangers){
        ArrayList<Integer> seats = new ArrayList<>();
        ConfiremedPassangers[] confiremedPassangers = new ConfiremedPassangers[passangers.length];
        int count = 0;
        for(int i=0;count<num && i<allocateSeats.size();i++,count++){
            seats.add(allocateSeats.get(i));
            confiremedPassangers[count] = new ConfiremedPassangers(allocateSeats.get(i),passangers[count]);
        }
        for(int i=0;i<stationName.size();i++){
            for(int j=places[0];j<places[1];j++){
                ArrayList<Integer> a = new ArrayList<>(stations[i][j].getTrain().getBookedSeats());
                a.addAll(seats);
                stations[i][j].getTrain().setBookedSeats(a);
            }
        }
        pnr.put(pnrNum,confiremedPassangers);
    }
    public static void getCancelTicketsDetails(){
        System.out.println("Enter your pnr number:");
        int pnrNum = sc.nextInt();
        if(!pnr.containsKey(pnrNum)){
            System.out.println("Invalid pnr number...");
        }
        ConfiremedPassangers[] confiremedPassangers = pnr.get(pnrNum);
        int[] places =new int[]{stationName.indexOf(confiremedPassangers[0].getBoardingStation()),stationName.indexOf(confiremedPassangers[0].getDestination())};
        cancelTickets(places,confiremedPassangers);
    }
    public static void cancelTickets(int[] places,ConfiremedPassangers[] confiremedPassangers){
        ArrayList<Integer> tempList = new ArrayList<>();
        for(ConfiremedPassangers confiremedPassangers1 : confiremedPassangers){
            tempList.add(confiremedPassangers1.getSeatNum());
        }
        cancelSeats(places,tempList);
        while(true){
            int index = waitingToConfirm();
            if(index==-1){
                break;
            }
            ConfiremedPassangers[] confiremedPassangers1 = WaitingLists.get(index);

            int[] waitingListPlaces = new int[] {stationName.indexOf(confiremedPassangers1[0].getBoardingStation()),stationName.indexOf(confiremedPassangers1[0].getDestination())};
            ArrayList<Integer> allocateSeats = isSeatsAvailable(places,WaitingLists.get(index).length);
            bookSeats(waitingListPlaces,allocateSeats,WaitingLists.get(index).length,WaitingLists.get(index));
            removeWaitingList(WaitingLists.get(index));
            WaitingLists.remove(index);
        }
    }
    public static void cancelSeats(int[] places,ArrayList<Integer> allocateSeats){
        for(int i=0;i<stationName.size();i++){
            for(int j=places[0];j<places[1];j++){
                ArrayList<Integer> a = new ArrayList<>(stations[i][j].getTrain().getBookedSeats());
                for (int k : allocateSeats){
                    if(a.contains(k)){
                        a.remove(a.indexOf(k));
                    }
                }
                stations[i][j].getTrain().setBookedSeats(a);
            }
        }
    }
    public static int waitingToConfirm(){
        for(Map.Entry m : WaitingLists.entrySet()){
            Passangers[] passangers = (Passangers[]) m.getValue();

            int[] places = new int[]{stationName.indexOf(passangers[0].getBoardingStation()),stationName.indexOf(passangers[0].getDestination())};
            int num = passangers.length;
            ArrayList<Integer> allocateSeats = isSeatsAvailable(places,num);
            if(allocateSeats.size()>=num){
                return (int)m.getKey();
            }
        }
        return -1;
    }
    public static void removeWaitingList(ConfiremedPassangers[] confiremedPassangers){
        int[] places = new int[]{stationName.indexOf(confiremedPassangers[0].getBoardingStation()),stationName.indexOf(confiremedPassangers[0].getDestination())};
        ArrayList<Integer> seats = new ArrayList<>();
        for(int i=0;i<confiremedPassangers.length;i++){
            seats.add(confiremedPassangers[i].getSeatNum());
        }
        for(int i=0;i<stationName.size();i++){
            for(int j=places[0];j<places[1];j++){
                ArrayList<Integer> a = new ArrayList<>(stations[i][j].getTrain().getWaitingList());
                a.removeAll(seats);
                stations[i][j].getTrain().setWaitingList(a);
//                TreeMap<Integer,Passangers[]> waitingList = new TreeMap<>(stations[i][j].getTrain().getWaitingLists());
//                waitingList.put(waitingListId,passangers);
//                stations[i][j].getTrain().setWaitingLists(waitingList);
            }
        }
//        waitingListId++;
    }
}
