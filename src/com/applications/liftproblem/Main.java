package com.applications.liftproblem;

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
        lifts[0].setRestrictFloor(new ArrayList<>(){{add(6);add(7);add(8);add(9);add(10);}});
        lifts[1].setRestrictFloor(new ArrayList<>(){{add(6);add(7);add(8);add(9);add(10);}});
        lifts[2].setRestrictFloor(new ArrayList<>(){{add(1);add(2);add(3);add(4);add(5);}});
        lifts[3].setRestrictFloor(new ArrayList<>(){{add(1);add(2);add(3);add(4);add(5);}});
        lifts[0].setMaintenance(true);
        while(true){
            System.out.println("Enter the from position:");
            int form = sc.nextInt();
            System.out.println("Enter the to position:");
            int to = sc.nextInt();
            int index = nearestLift(lifts,form,to);
            System.out.println(index);
            lifts[index].setFloor(to);
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
            if(lifts[i].isMaintenance()){
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
}
