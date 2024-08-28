package com.applications.snakeandladder;

import java.util.*;

public class Main {
    public static int snakeCount;
    public static Snake[] snakes;
    public static Ladder[] ladders;
    public static int ladderCount;
    public static Scanner sc;
    public static String[][] board;
    public static int boardSize = 10;
    public static Player[] players;
    public static void main(String[] args){
        sc = new Scanner(System.in);
        System.out.print("Enter the number of snakes count:");
        snakeCount = sc.nextInt();
        System.out.print("Enter the number of ladder count:");
        ladderCount = sc.nextInt();
        snakes = new Snake[snakeCount];
        ladders = new Ladder[ladderCount];
        board = new String[boardSize][boardSize];
        initializeSnake();
        initializeLadder();
        printSnake();
        System.out.println("Enter the number of input:");
        int input = sc.nextInt();
        System.out.println("Enter the number of player");
        int playerCount = sc.nextInt();
        players = new Player[playerCount];
        int[] arr = new int[input];
        initializeArr(arr);
        playGame(arr,0,0);
        playerPosition();
    }
    public static void playGame(int[] arr,int position,int player){
        int index = -1;
        for(int i=position;i<arr.length;i++){
            if(players[player]==null){
                players[player] = new Player(getValues(new int[]{0,0},arr[i]));
            }
            else{
                players[player].setPosition(getValues(players[player].getPosition(),arr[i]));
            }
            if(arr[i]==1 || arr[i]==3 || arr[i]==5){
                index = i+1;
                break;
            }
        }
        if(index<arr.length-1){
            player++;
            if(player==players.length){
                player = 0;
            }
            if(index!=-1) {
                playGame(arr, index, player);
            }
        }
    }
    public static void playerPosition(){
        for (int i=0;i< players.length;i++){
            int x = 0,y=0;
            if(players[i]!=null){
                x = players[i].getPosition()[0];
                y = players[i].getPosition()[1];
            }
            System.out.println((i+1)+" player position X :"+ x +" position Y:"+y);
        }
    }
    public static int[] getValues(int[] position,int num){
        if(position[1]+num>=boardSize){
            return new int[]{position[0]+1,(position[1]+num)%boardSize};
        }
        return new int[]{position[0],(position[1]+num)};
    }
    public static void initializeArr(int[] arr){
        for(int i=0;i<arr.length;i++){
            arr[i] = sc.nextInt();
        }
    }
    public static void initializeSnake(){
        for(int i=0;i<snakeCount;i++){
            System.out.print("Enter the snake start X position:");
            int startX = sc.nextInt();
            System.out.print("Enter the snake Start Y position:");
            int startY = sc.nextInt();
            System.out.print("Enter the snake end X position:");
            int endX = sc.nextInt();
            System.out.print("Enter the snake end Y position:");
            int endY = sc.nextInt();
            snakes[i] = new Snake(new int[]{startX,startY},new int[]{endX,endY});
            assignSnake(new int[]{startX,startY},new int[]{endX,endY},i+1);
        }
    }
    public static void initializeLadder(){
        for(int i=0;i<ladderCount;i++){
            System.out.print("Enter the ladder start X position:");
            int startX = sc.nextInt();
            System.out.print("Enter the ladder Start Y position:");
            int startY = sc.nextInt();
            System.out.print("Enter the ladder end X position:");
            int endX = sc.nextInt();
            System.out.print("Enter the ladder end Y position:");
            int endY = sc.nextInt();
            ladders[i] = new Ladder(new int[]{startX,startY},new int[]{endX,endY});
            assignLadder(new int[]{startX,startY},new int[]{endX,endY},i+1);
        }
    }
    public static void assignSnake(int[] start,int[] end,int count){
        List<Integer[]> queue = sortPath(start,end);
        for(int i=0;i<queue.size();i++){
            if(queue.get(i)[0]==start[0] && queue.get(i)[1]==start[1]){
                board[queue.get(i)[0]][queue.get(i)[1]]="SS"+count;
            }
            else if(queue.get(i)[0]==end[0] && queue.get(i)[1]==end[1]){
                board[queue.get(i)[0]][queue.get(i)[1]]="SE"+count;
            }
            else{
                board[queue.get(i)[0]][queue.get(i)[1]]="S";
            }
        }
    }
    public static void assignLadder(int[] start,int[] end,int count){
        List<Integer[]> queue = sortPath(start,end);
        for(int i=0;i<queue.size();i++){
            if(queue.get(i)[0]==start[0] && queue.get(i)[1]==start[1]){
                board[queue.get(i)[0]][queue.get(i)[1]]="LS"+count;
            }
            else if(queue.get(i)[0]==end[0] && queue.get(i)[1]==end[1]){
                board[queue.get(i)[0]][queue.get(i)[1]]="LE"+count;
            }
        }
    }
    public static List<Integer[]> sortPath(int[] start,int[] end){
        Integer[] arr = Arrays.stream(start).boxed().toArray(Integer[]::new);
        Path path = new Path(start,new ArrayList<>(){{ add(arr);}});
        Queue<Path> queue = new LinkedList<>();
        queue.add(path);
        boolean[][] tempArray = new boolean[boardSize][boardSize];
        tempArray[start[0]][start[1]] = true;
        while(!queue.isEmpty()){
            path = queue.remove();
            if(path.getArr()[0]==end[0] && path.getArr()[1]==end[1]){
                break;
            }
            if(path.getArr()[0]+1>-1 && path.getArr()[0]+1<boardSize && !tempArray[path.getArr()[0]+1][path.getArr()[1]]){
                tempArray[path.getArr()[0]+1][path.getArr()[1]] = true;
                Integer[] arr1 = Arrays.stream(new int[]{path.getArr()[0]+1,path.getArr()[1]}).boxed().toArray(Integer[]::new);
                List<Integer[]> list = new ArrayList<>(path.getList());
                list.add(arr1);
                queue.add(new Path(new int[]{path.getArr()[0]+1,path.getArr()[1]},list));
            }
            if(path.getArr()[0]-1>-1 && path.getArr()[0]-1<boardSize && !tempArray[path.getArr()[0]-1][path.getArr()[1]]){
                tempArray[path.getArr()[0]-1][path.getArr()[1]] = true;
                Integer[] arr1 = Arrays.stream(new int[]{path.getArr()[0]-1,path.getArr()[1]}).boxed().toArray(Integer[]::new);
                List<Integer[]> list = new ArrayList<>(path.getList());
                list.add(arr1);
                queue.add(new Path(new int[]{path.getArr()[0]-1,path.getArr()[1]},list));
            }
            if(path.getArr()[1]+1>-1 && path.getArr()[1]+1<boardSize && !tempArray[path.getArr()[0]][path.getArr()[1]+1]){
                tempArray[path.getArr()[0]][path.getArr()[1]+1] = true;
                Integer[] arr1 = Arrays.stream(new int[]{path.getArr()[0],path.getArr()[1]+1}).boxed().toArray(Integer[]::new);
                List<Integer[]> list = new ArrayList<>(path.getList());
                list.add(arr1);
                queue.add(new Path(new int[]{path.getArr()[0],path.getArr()[1]+1},list));
            }
            if(path.getArr()[1]-1>-1 && path.getArr()[1]-1<boardSize && !tempArray[path.getArr()[0]][path.getArr()[1]-1]){
                tempArray[path.getArr()[0]][path.getArr()[1]-1] = true;
                Integer[] arr1 = Arrays.stream(new int[]{path.getArr()[0],path.getArr()[1]-1}).boxed().toArray(Integer[]::new);
                List<Integer[]> list = new ArrayList<>(path.getList());
                list.add(arr1);
                queue.add(new Path(new int[]{path.getArr()[0],path.getArr()[1]-1},list));
            }
        }
        return path.getList();
    }
    public static boolean check(int x,int y,boolean[][] tempArray){
        if(x>-1 && x<tempArray.length){
            return true;
        }
        return false;
    }
    public static void printSnake(){
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(((String)(board[i][j]))==null){
                    System.out.print("* ");
                }
                else {
                    System.out.print(board[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
}
