package com.applications.chess_tournament;

import java.util.*;

public class Main {
    public static int numOfTournament,numOfPlayer;
    public static Players[] players;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number if tournament:");
        numOfTournament = sc.nextInt();
        System.out.print("Enter the number of players:");
        numOfPlayer = sc.nextInt();
        players = new Players[numOfPlayer];
        initializePlayers();
        for(int i=0;i<numOfTournament;i++){
            if(i==0){
                matchPlayed();
            }
            else{
                quickSort(0,numOfPlayer-1);
                bestPlayerMatch();
            }
            System.out.println("---------------------------");
        }
        quickSort(0,numOfPlayer-1);
        printPlayerScore();
        System.out.println(STR."Winner of the match is score:\{players[0].getPoints()}");
    }
    public static void initializePlayers(){
        for(int i=0;i<numOfPlayer;i++){
            players[i] = new Players();
        }
    }
    public static void matchPlayed(){
        List<Integer> playedPlayers = new ArrayList<>();
        for(int i=0;i<numOfPlayer/2;i++){
            int first = getRandomVal(numOfPlayer,playedPlayers);
            playedPlayers.add(first);
            int second = getRandomVal(numOfPlayer,playedPlayers);
            playedPlayers.add(second);
            int gameDesition = winGame();
            switch (gameDesition){
                case 0:{
                    playerUpdate(first,second);
                    players[first].setPoints(players[first].getPoints()+1);
                    System.out.println(first+" VS "+second+"the winner is 1 player..");
                    break;
                }
                case 1:{
                    playerUpdate(first,second);
                    players[second].setPoints(players[second].getPoints()+1);
                    System.out.println(first+" VS "+second+"the winner is 2 player..");
                    break;
                }
                case 2:{
                    playerUpdate(first,second);
                    players[first].setPoints(players[first].getPoints()+0.5);
                    players[second].setPoints(players[second].getPoints()+0.5);
                    System.out.println(first+" VS "+second+"the match is draw..");
                    break;
                }
            }
        }
        if(numOfPlayer%2==1){
            int index = getRandomVal(numOfPlayer,playedPlayers);
            players[index].setPoints(players[index].getPoints()+1);
            System.out.println(index+"the winner is 1 player..");
        }
    }
    public static void bestPlayerMatch(){
        List<Integer> playedPlayers = new ArrayList<>();
        for(int i=0;i<numOfPlayer/2;i++){
            int first = getRandomVal(numOfPlayer,playedPlayers);
            playedPlayers.add(first);
            int second = bestPlayer(first,playedPlayers);
            playedPlayers.add(second);
            if(second==-1){
                continue;
            }
            int gameDesition = winGame();
            switch (gameDesition){
                case 0:{
                    playerUpdate(first,second);
                    players[first].setPoints(players[first].getPoints()+1);
                    System.out.println(first+" VS "+second+"the winner is 1 player..");
                    break;
                }
                case 1:{
                    playerUpdate(first,second);
                    players[second].setPoints(players[second].getPoints()+1);
                    System.out.println(first+" VS "+second+"the winner is 2 player..");
                    break;
                }
                case 2:{
                    playerUpdate(first,second);
                    players[first].setPoints(players[first].getPoints()+0.5);
                    players[second].setPoints(players[second].getPoints()+0.5);
                    System.out.println(first+" VS "+second+"the match is draw..");
                    break;
                }
            }
        }
        if(numOfPlayer%2==1){
            int index = getRandomVal(numOfPlayer,playedPlayers);
            players[index].setPoints(players[index].getPoints()+1);
            System.out.println(index+"the winner is 1 player..");
        }
    }
    public static int bestPlayer(int index,List<Integer> playedPlayers){
        Players max = null,min = null;
        int minIndex = -1,maxIndex = -1;
        for(int i=0;i<numOfPlayer;i++){
            if(min==null && !players[index].getOpponents().contains(players[i]) && !playedPlayers.contains(i)){
                max = players[i];
                min = players[i];
                minIndex = i;
                maxIndex = i;
            }
            if(min!=null && !players[index].getOpponents().contains(players[i]) && min.getPoints()<players[i].getPoints() && players[index].getPoints()>=players[i].getPoints() && !playedPlayers.contains(i)){
                min = players[i];
                minIndex = i;
            }
            if(min!=null && !players[index].getOpponents().contains(players[i]) && max.getPoints()>players[i].getPoints() && players[index].getPoints()<=players[i].getPoints() && !playedPlayers.contains(i)){
                max = players[i];
                maxIndex = i;
            }
        }
        if (min == null) {
            return -1;
        }
//        System.out.println(min.getPoints()+"--->"+max.getPoints());
        return Math.abs(min.getPoints()-players[index].getPoints()) >= Math.abs(max.getPoints()-players[index].getPoints()) ? maxIndex : minIndex;
    }
    public static void playerUpdate(int first,int second){
        List<Players> firstPlayer = new ArrayList<>(players[first].getOpponents());
        firstPlayer.add(players[second]);
        players[first].setOpponents(firstPlayer);
        List<Players> secondPlayer = new ArrayList<>(players[second].getOpponents());
        secondPlayer.add(players[first]);
        players[second].setOpponents(secondPlayer);
    }
    public static int getRandomVal(int max, List<Integer> list){
        int index = -1;
        while(index==-1 || list.contains(index)){
            index = getRandom(max);
        }
        return index;
    }
    public static int getRandom(int max){
        Random random = new Random();
        return random.nextInt(max);
    }
    public static int winGame(){
        return new Random().nextInt(3);
    }
    public static void quickSort(int start,int end){
        if(start<end){
            int p = partition(start,end);
            quickSort(start,p-1);
            quickSort(p+1,end);
        }
    }
    public static int partition(int start,int end){
        int j=start-1;
        Players pivot = players[end];
        for(int i=start;i<end;i++){
            if(players[i].getPoints()>pivot.getPoints()){
                j++;
                Players temp = players[i];
                players[i] = players[j];
                players[j] = temp;
            }
        }
        j++;
        players[end] = players[j];
        players[j] = pivot;
        return j;
    }
    public static void printPlayerScore(){
        for(int i=0;i<numOfPlayer;i++){
            System.out.println(players[i].getPoints());
        }
    }
}
