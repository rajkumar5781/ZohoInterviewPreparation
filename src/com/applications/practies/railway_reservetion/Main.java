package com.applications.practies.railway_reservetion;

import com.applications.liftproblem.Lift;
import com.applications.railway_reservation_with_station.Passangers;

import java.util.*;

public class Main {
    public static Passanger[] confirmedTickets = new Passanger[63];
    public static Passanger[] RACTickets = new Passanger[18];
    public static Passanger[] waitingTickets = new Passanger[10];
    public static int confirmedTicketsCount = 63;
    public static int RACTicketsCount = 18;
    public static int waitingTicketsCount = 10;
    public static List<Integer> lowerSeats = List.of(1);
    public static List<Integer> middleSeats = List.of(1);
    public static List<Integer> upperSeats = List.of(1);
    public static HashMap<Integer,Passanger[]> pnoAllocate = new HashMap<>();
    public static TreeMap<Integer,Booking> confirmedTicket = new TreeMap<>();
    public static TreeMap<Integer,Booking> RACTicket = new TreeMap<>();
    public static Queue<Booking> waitingTicket = new LinkedList<>();
    public static int PNRNumber = 1;
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        while(true) {
            System.out.println("Enter the option:");
            System.out.println("1.Book");
            System.out.println("2.Cancel");
            System.out.println("3.Print booked tickets");
            System.out.println("4.Print available tickets");
            int option = sc.nextInt();
            if(option<1 || option>4){
                System.out.println("Invalid input");
                continue;
            }
            switch (option){
                case 1: getBookingDetails(); break;
                case 2: cancel(); break;
                case 3: break;
                case 4: break;
            }
        }
    }
    public static void getBookingDetails(){
        System.out.println("Enter the passangers count:");
        int count = sc.nextInt();
        Passanger[] passangers = new Passanger[count];
        for(int i=0;i<count;i++){
            System.out.println("Enter the name:");
            String name = sc.next();
            System.out.println("Enter the age:");
            int age = sc.nextInt();
            System.out.println("Enter the gender:");
            char gender = sc.next().charAt(0);
            System.out.println("Enter the berth preference:");
            String berthPreference = sc.next();
            passangers[i] = new Passanger(name,age,gender,berthPreference);
        }
        if(confirmedTicketsCount>=count){
            confirmSeatBooking(passangers);
            pnoAllocate.put(PNRNumber++,passangers);
        }
        else if(RACTicketsCount*2>=count){
            RACSeatAllocate(passangers);
        } else if (waitingTicketsCount>=count) {
            waitingSeatAllocate(passangers);
        }
    }
    public static void confirmSeatBooking(Passanger[] passangers){
        for(int i=0;i<passangers.length;i++){
            if(lowerSeats.size()<confirmedTickets.length/3 && passangers[i].getBerthPreference().equals("L")){
//                    passangers[i].setAlloted("L");
//                    lowerSeats.add(lowerSeats.size()+1);
//                    passangers[i].setSeatNum(confirmedTickets.length-confirmedTicketsCount);
//                    confirmedTickets[confirmedTickets.length-confirmedTicketsCount] = passangers[i];
                passangers[i] = seatAllocate(passangers[i],"L",lowerSeats);
            }
            else if(middleSeats.size()<confirmedTickets.length/3 && passangers[i].getBerthPreference().equals("M")){
//                    middleSeats.add(middleSeats.size()+1);
//                    passangers[i].setAlloted("M");
//                    passangers[i].setSeatNum(confirmedTickets.length-confirmedTicketsCount);
//                    confirmedTickets[confirmedTickets.length-confirmedTicketsCount] = passangers[i];
                passangers[i] = seatAllocate(passangers[i],"M",middleSeats);
            }
            else if(upperSeats.size()<confirmedTickets.length/3 && passangers[i].getBerthPreference().equals("U")){
//                    upperSeats.add(upperSeats.size()+1);
//                    passangers[i].setAlloted("U");
//                    passangers[i].setSeatNum(confirmedTickets.length-confirmedTicketsCount);
//                    confirmedTickets[confirmedTickets.length-confirmedTicketsCount] = passangers[i];
                passangers[i] = seatAllocate(passangers[i],"U",upperSeats);
            }
            else{
                if(lowerSeats.size()<confirmedTickets.length/3){
//                        lowerSeats.add(lowerSeats.size()+1);
//                        passangers[i].setAlloted("L");
//                        passangers[i].setSeatNum(confirmedTickets.length-confirmedTicketsCount);
//                        confirmedTickets[confirmedTickets.length-confirmedTicketsCount] = passangers[i];
                    passangers[i] = seatAllocate(passangers[i],"L",lowerSeats);
                }
                else if(middleSeats.size()<confirmedTickets.length/3){
//                        middleSeats.add(middleSeats.size()+1);
//                        passangers[i].setAlloted("M");
//                        passangers[i].setSeatNum(confirmedTickets.length-confirmedTicketsCount);
//                        confirmedTickets[confirmedTickets.length-confirmedTicketsCount] = passangers[i];
                    passangers[i] = seatAllocate(passangers[i],"M",middleSeats);
                }
                else if(upperSeats.size()<confirmedTickets.length/3){
//                        upperSeats.add(upperSeats.size()+1);
//                        passangers[i].setAlloted("U");
//                        passangers[i].setSeatNum(confirmedTickets.length-confirmedTicketsCount);
//                        confirmedTickets[confirmedTickets.length-confirmedTicketsCount] = passangers[i];
                    passangers[i] = seatAllocate(passangers[i],"U",upperSeats);
                }
            }
        }
    }
    public static void RACSeatAllocate(Passanger[] passangers){
        for(int i=0;i<passangers.length;i++){
            RACTickets[RACTickets.length-(RACTicketsCount%2+RACTicketsCount/2)] = passangers[i];
            RACTicketsCount--;
        }
    }
    public static void waitingSeatAllocate(Passanger[] passangers){
        for(int i=0;i<passangers.length;i++) {
            waitingTickets[waitingTickets.length-waitingTicketsCount] = passangers[i];
            waitingTicketsCount--;
        }
    }
    public static Passanger seatAllocate(Passanger passanger,String seat,List<Integer> list){
        passanger.setAlloted(seat);
        list.add(list.size()+1);
        passanger.setSeatNum(confirmedTickets.length-confirmedTicketsCount);
        confirmedTickets[confirmedTickets.length-confirmedTicketsCount] = passanger;
        confirmedTicketsCount--;
        return passanger;
    }
    public static void cancel(){
        System.out.println("Enter the PNR number:");
        int pnrNum = sc.nextInt();
        if(pnoAllocate.getOrDefault(pnrNum,null)==null){
            System.out.println("Invalid PNR number...");
            return;
        }

    }
}
