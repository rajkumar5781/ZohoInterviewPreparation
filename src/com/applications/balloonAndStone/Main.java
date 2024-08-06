package com.applications.balloonAndStone;

import java.security.PublicKey;
import java.util.*;

public class Main {
    public static Scanner sc = null;
    public static char[][] board;
    public static Queue<int[]> queue;
    public static ArrayList<int[]> list;
    public static boolean[][] arr;
    public static void main(String[] args){
        sc = new Scanner(System.in);
        System.out.println("Enter the board row size:");
        int row = sc.nextInt();
        System.out.println("Enter the board col size:");
        int col = sc.nextInt();
        queue = new LinkedList<>();
        board = new char[row][col];
        list = new ArrayList<>();
        arr = new boolean[row][col];
        Arrays.stream(board).forEach(a->Arrays.fill(a,'-'));
        printBoard();
        gameStart();
    }
    public static void gameStart(){
        while(true) {
            System.out.println("Do you wish to continue(Y/N)  :");
            char ch = sc.next().charAt(0);
            if(ch=='N'){
                break;
            }
            System.out.println("Enter the column number:  ");
            int col = sc.nextInt();
            if(!validateCol(col)){
                System.out.println("Not available column...");
                break;
            }
            System.out.println("Choose drop balloon or rack(B/R)   :");
            char choose = sc.next().charAt(0);
            drop(choose,col);
            printBoard();
        }
    }
    public static void drop(char c,int index){
        int i = 0;
        for(i=0;i<board.length;i++){
            if(board[i][index]!='-'){
                break;
            }
        }
        board[i-1][index] = c;
        if(c=='R'){
            breakBalloon(i-1,index);
        }
    }
    public static void breakBalloon(int row,int col){
        int count = 0,i=row;
        for(i=row;i<board.length;i++){
                if(board[i][col]!='R'){
                    break;
                }
                count++;
        }
        if(count>1){
            boolean status = false;
            for(int j=i;j<board.length;j++){
                if(board[j][col]!='B') {
                    break;
                }
                status = true;
                board[j][col] = '*';
                queue.add(new int[]{j,col});
            }

            if(status) {
                while(!queue.isEmpty()) {
                    int[] a = queue.remove();
                    moveDown(a);
                }
            }
            breakBalloon(i-1,col);
        }
    }
    public static void moveDown(int[] col) {
        int j=col[0];
        for(j=col[0];j>0;j--){
            board[j][col[1]] = board[j-1][col[1]];
        }
        if(j==0){
            board[0][col[1]] = '-';
        }
        if(col[0]-1>=0 && col[1]+1<board[0].length && board[col[0]-1][col[1]+1]=='B'){
            queue.add(new int[]{col[0]-1,col[1]+1});
        }
        if(col[0]-1>=0 && col[1]-1>=0 && board[col[0]-1][col[1]-1]=='B'){
            queue.add(new int[]{col[0]-1,col[1]-1});
        }
        if(col[0]+1<board.length && col[1]-1>=0 && board[col[0]+1][col[1]-1]=='B'){
            queue.add(new int[]{col[0]+1,col[1]-1});
        }
        if(col[0]+1<board.length && col[1]+1<board[0].length && board[col[0]+1][col[1]+1]=='B'){
            queue.add(new int[]{col[0]+1,col[1]+1});
        }
    }
//    public static void moveDown(int col){
//        for(int i=1;i<board.length;i++){
//            if(board[i][col]=='*'){
//                if(i-1>=0 && board[i-1][col]=='B'){
//
//                }
//                if(i-1>=0 && col-1>0 && board[i-1][col-1]=='B'){
//
//                }
//                if(i+1<board.length && board[i+1][col]=='B'){
//
//                }
//                if(i+1<board.length && col+1<board[0].length && board[i+1][col+1]=='B'){
//
//                }
//                int j=i;
//                for(j=i;j>0;j--){
//                    board[j][col] = board[j-1][col];
//                }
//                if(j==0){
//                    board[0][col] = '-';
//                }
//            }
//        }
//    }
    public static boolean validateCol(int col){
        return board[0].length>=col && board[0][col]=='-';
    }
    public static void printBoard(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void resetArr(){
        Arrays.stream(arr).forEach(a->Arrays.fill(a,false));
    }
}
