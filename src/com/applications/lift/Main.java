package com.applications.lift;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int liftCount=0;
    public static int numOfFloor=0;
    public static Lift[] lifts;
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the number of lift:");
            liftCount = sc.nextInt();
            System.out.println("Enter the number of floors");
            numOfFloor = sc.nextInt();
            lifts = new Lift[liftCount];
            initializedLifts();
            Lift.printLifts(lifts);
            lifts[0].setRestrictFloor(new ArrayList<>(){{add(3);add(5);}});
            lifts[1].setRestrictFloor(new ArrayList<>(){{add(4);add(6);}});
            while(true){
                System.out.println("Enter the number of input:");
                int input = sc.nextInt();
                int[][] inputs = new int[input][2];
                for(int i=0;i<input;i++){
                    System.out.println("Enter the input:");
                    inputs[i][0] = sc.nextInt();
                    inputs[i][1]= sc.nextInt();
                }
                operateLift(inputs);
//                int index = nearestLift(lifts,form,to);
//                System.out.println(index);
//                lifts[index].setFloor(to);
                Lift.printLifts(lifts);
            }
        }
    public static void initializedLifts(){
        for(int i=0;i<liftCount;i++){
            lifts[i] = new Lift(0,i+1);
        }
    }
    public static int nearestLift(Lift[] lifts,int from,int to){
        List<Lift> tempLifts = new LinkedList<>();
        int nearest = Integer.MAX_VALUE,index=-1;
        int stops = Integer.MAX_VALUE;
        for(int i=0;i<lifts.length;i++){
//            nearest>Math.abs(lifts[i].getFloor()-from) &&
            if(lifts[i].isMaintenance() || lifts[i].getPersonCount()>=lifts[i].getNumOfPerson()){
                continue;
            }
            if(((lifts[i].getRestrictFloor()!=null && !lifts[i].getRestrictFloor().contains(from) && !lifts[i].getRestrictFloor().contains(to) && stops>stopsCount(lifts[i],from,to)) || lifts[i].getRestrictFloor()==null && stops>stopsCount(lifts[i],from,to))){
                tempLifts = new ArrayList<>();
                tempLifts.add(lifts[i]);
                index = i;
                nearest = Math.abs(lifts[i].getFloor()-from);
//                if(lifts[i].getRestrictFloor()!=null) {
                stops = stopsCount(lifts[i], from, to);
//                    System.out.println(stops);
//                }
            }
//            nearest==Math.abs(lifts[i].getFloor()-from) &&
            else if(((lifts[i].getRestrictFloor()!=null && !lifts[i].getRestrictFloor().contains(from) && !lifts[i].getRestrictFloor().contains(to) && stops==stopsCount(lifts[i],from,to)) || lifts[i].getRestrictFloor()==null && stops==stopsCount(lifts[i],from,to))){
                tempLifts.add(lifts[i]);
            }
        }
        if(tempLifts.size()>1){
            int min = Integer.MAX_VALUE;
            for(Lift l : tempLifts){
                if((from-l.getFloor()>=0) && from>to && min>from-l.getFloor()){
                    min = from-l.getFloor();
                    index = l.getLiftNum()-1;
                }
                if((l.getFloor()-from>=0) && from<to && min>from-l.getFloor()){
                    min = l.getFloor()-from;
                    index = l.getLiftNum()-1;
                }
            }
        }
        return index;
    }
    public static int stopsCount(Lift lifts,int from,int to){
        int count = 0,start=from;
        if(lifts.getRestrictFloor()!=null && (lifts.getRestrictFloor().contains(start) || lifts.getRestrictFloor().contains(to))){
            return -1;
        }
        int floor = lifts.getFloor();
        if(from>floor) {
            while (from > floor) {
                floor++;
                if (lifts.getRestrictFloor() == null || !lifts.getRestrictFloor().contains(floor)) {
                    count++;
                }
            }
        }
        else if(floor>from){
            while (from < floor) {
                floor--;
                if (lifts.getRestrictFloor() == null || !lifts.getRestrictFloor().contains(floor)) {
                    count++;
                }
            }
        }
        if(from>to) {
            while (start > to) {
                start--;
                if (lifts.getRestrictFloor()==null || !lifts.getRestrictFloor().contains(start)) {
                    count++;
                }
            }
        }
        else{
            while (start < to) {
                start++;
                if (lifts.getRestrictFloor()==null || !lifts.getRestrictFloor().contains(start)) {
                    count++;
                }
            }
        }
        return count;
    }
    public static void operateLift(int[][] inputs){
            int traveladPersonCount = 0;
            for(int i=0;i<inputs.length;i++){
                int index = nearestLift(lifts,inputs[i][0],inputs[i][1]);
                if(index==-1){
                    continue;
                }
                traveladPersonCount++;
                lifts[index].setPersonCount(lifts[index].getPersonCount()+1);
                if(lifts[index].getTempFloor()<=inputs[i][1]){
                    lifts[index].setTempFloor(inputs[i][1]);
                }
            }
            for(int i=0;i<lifts.length;i++){
                if(lifts[i].getTempFloor()>=0){
                    lifts[i].setFloor(lifts[i].getTempFloor());
                    lifts[i].setTempFloor(0);
                    lifts[i].setPersonCount(0);
                }
            }
            System.out.println("Number of traveled persons:"+traveladPersonCount);
    }
}
