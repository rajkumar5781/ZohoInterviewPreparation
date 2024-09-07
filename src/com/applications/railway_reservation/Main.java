//package com.applications.railway_reservation;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//    public static Scanner sc;
//    public static Coach coach;
//    public static HashMap<Integer,PersonInfo[]> booking = new HashMap<>();
//    public static int pnrNum = 1;
////    public static
//    public static void main(String[] args){
//        sc =new Scanner(System.in);
//        coach = new Coach(63,18,10);
//        while (true){
//            System.out.println("1.Book");
//            System.out.println("2.cancel");
//            System.out.println("3.Print booked tickets");
//            System.out.println("4.Print available tickets");
//            int choose = sc.nextInt();
//            switch (choose){
//                case 1:{
//                    bookTicket();
//                    break;
//                }
//                case 2:{
//                    break;
//                }
//                case 3:{
//                    break;
//                }
//                case 4:{
//                    printSeats();
//                    break;
//                }
//            }
//        }
//    }
//    public static void printSeats(){
//        System.out.println("Available seats:"+availableSeatCount(coach.getLowerSeats(),coach.getLowerSeatsCount()));
//        System.out.println("Available seats:"+availableSeatCount(coach.getRACSeat(),coach.getRACSeatsCount()));
//        System.out.println("Available seats:"+availableSeatCount(coach.getWaitingSeat(),coach.getWaitingSeatsCount()));
//    }
//    public static int availableSeatCount(List<Seats> seats,int total){
//        return total-seats.size();
//    }
//    public static void bookTicket(){
//        System.out.println("1.Single ticket:");
//        System.out.println("2.Group ticket:");
//        int choose = sc.nextInt();
//        switch (choose){
//            case 1:{
//                PersonInfo[] personInfos = new PersonInfo[1];
//                personInfos[0] = getPersonDetail();
//                allocateSeats(personInfos);
//                break;
//            }
//            case 2:{
//                System.out.println("Enter the number of person to travel:");
//                int numPerson = sc.nextInt();
//                PersonInfo[] personInfos = new PersonInfo[numPerson];
//                int parentIndex = -1;
//                for(int i=0;i<numPerson;i++){
//                    PersonInfo personInfo = getPersonDetail();
//                    if(personInfo==null){
//                        if(parentIndex!=-1){
//                            ArrayList<PersonInfo> childList = new ArrayList<>(personInfos[parentIndex].getChildren());
//                            childList.add(personInfo);
//                            personInfos[parentIndex].setChildren(childList);
//                        }
//                    }
//                    else{
//                        personInfos[i] = personInfo;
//                        if(parentIndex==-1){
//                            parentIndex = i;
//                        }
//                    }
//                }
//                allocateSeats(personInfos);
//                break;
//            }
//        }
//    }
//    public static void allocateSeats(PersonInfo[] personInfos){
//
//        for(int i=0;i<personInfos.length;i++){
//            if(coach.getLowerSeatsCount()-coach.getLowerSeats().size()>0 || coach.getMiddleSeatsCount()-coach.getMiddleSeats().size()>0 || coach.getUpperSeatsCount()-coach.getUpperSeats().size()>0) {
//                if (personInfos[i].getPreferance() == 'L' && coach.getLowerSeatsCount() - coach.getLowerSeats().size() > 0) {
//                    coach.setLowerSeats();
//                }
//                else if (personInfos[i].getPreferance() == 'M' && coach.getMiddleSeatsCount() - coach.getMiddleSeats().size() > 0) {
//
//                }
//                else if (personInfos[i].getPreferance() == 'U' && coach.getUpperSeatsCount() - coach.getUpperSeats().size() > 0) {
//
//                }
//                else{
//                    if(coach.getLowerSeatsCount() - coach.getLowerSeats().size() > 0){
//
//                    }
//                    else if(coach.getMiddleSeatsCount() - coach.getMiddleSeats().size() > 0){
//
//                    }
//                    else{
//
//                    }
//                }
//            }
//            else if(coach.getRACSeat().size() - coach.getRACSeatsCount()>0){
//
//            }
//            else{
//
//            }
//        }
//        booking.put(pnrNum,personInfos);
//        pnrNum++;
//    }
//
//    public static PersonInfo getPersonDetail(){
//        System.out.println("Enter your name");
//        String name = sc.next();
//        System.out.println("Enter your age:");
//        int age = sc.nextInt();
//        System.out.println("Enter your preferance L,M,U");
//        char preferance = sc.next().charAt(0);
//        System.out.println("Enter your gender:");
//        String gender = sc.next();
//        if(age<5){
//            return null;
//        }
//        return new PersonInfo(name,age,preferance,gender);
//    }
//}
