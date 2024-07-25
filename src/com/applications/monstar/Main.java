package com.applications.monstar;

import java.util.*;

public class Main {
    private int[] current = new int[2];
    private List<Integer[]> path = new ArrayList<>();
    private int bulletCount=0;

    public int[] getCurrent() {
        return current;
    }

    public void setCurrent(int[] current) {
        this.current = current;
    }

    public List<Integer[]> getPath() {
        return path;
    }

    public void setPath(List<Integer[]> path) {
        this.path = path;
    }

    public int getBulletCount() {
        return bulletCount;
    }

    public void setBulletCount(int bulletCount) {
        this.bulletCount = bulletCount;
    }

    Main(int[] position){
        this.current = position;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        char[][] arr = {{'-','-','-','s'},{'-','t','m','t'},{'-','m','e','m'},{'-','-','-','-'}};
        int[] start = startPoint(arr);
        Main temp = doing(arr,start);
        if(temp!=null){
            printPath(temp);
        }
    }
    public static Main doing(char[][] arr,int[] start){
        Main s = new Main(start);
        Queue<Main> queue = new LinkedList<>();
        boolean[][] tempArr = new boolean[arr.length][arr[0].length];
        tempArr[start[0]][start[1]] = true;
        s.setPath(new ArrayList<>(){{add(new Integer[]{start[0],start[1]});}});
        queue.add(s);
                while(!queue.isEmpty()){
                    Main obj = queue.remove();
                    if(arr[obj.getCurrent()[0]][obj.getCurrent()[1]]=='e'){
                        return obj;
                    }
                    if(isValid(obj.getCurrent()[0]+1,obj.getCurrent()[1],arr,tempArr)){
                        tempArr[obj.getCurrent()[0]+1][obj.getCurrent()[1]] = true;
                        Main m = new Main(new int[]{obj.getCurrent()[0]+1,obj.getCurrent()[1]});
                        List<Integer[]> tempList = new ArrayList<>(obj.getPath());
                        tempList.add(new Integer[]{obj.getCurrent()[0]+1,obj.getCurrent()[1]});
                        m.setPath(tempList);
                        if(arr[obj.getCurrent()[0]+1][obj.getCurrent()[1]]=='t'){
                            m.setBulletCount(m.getBulletCount()+1);
                            queue.add(m);
                        }
                        else if(arr[obj.getCurrent()[0]+1][obj.getCurrent()[1]]=='m' && obj.getBulletCount()>0){
                            m.setBulletCount(m.getBulletCount()-1);
                            queue.add(m);
                        }
                        else{
                            queue.add(m);
                        }
                    }
                    if(isValid(obj.getCurrent()[0]-1,obj.getCurrent()[1],arr,tempArr)){
                        tempArr[obj.getCurrent()[0]-1][obj.getCurrent()[1]] = true;
                        Main m = new Main(new int[]{obj.getCurrent()[0]-1,obj.getCurrent()[1]});
                        List<Integer[]> tempList = new ArrayList<>(obj.getPath());
                        tempList.add(new Integer[]{obj.getCurrent()[0]-1,obj.getCurrent()[1]});
                        m.setPath(tempList);
                        if(arr[obj.getCurrent()[0]-1][obj.getCurrent()[1]]=='t'){
                            m.setBulletCount(m.getBulletCount()+1);
                            queue.add(m);
                        }
                        else if(arr[obj.getCurrent()[0]-1][obj.getCurrent()[1]]=='m' && obj.getBulletCount()>0){
                            m.setBulletCount(m.getBulletCount()-1);
                            queue.add(m);
                        }
                        else{
                            queue.add(m);
                        }
                    }
                    if(isValid(obj.getCurrent()[0],obj.getCurrent()[1]+1,arr,tempArr)){
                        tempArr[obj.getCurrent()[0]][obj.getCurrent()[1]+1] = true;
                        Main m = new Main(new int[]{obj.getCurrent()[0],obj.getCurrent()[1]+1});
                        List<Integer[]> tempList = new ArrayList<>(obj.getPath());
                        tempList.add(new Integer[]{obj.getCurrent()[0],obj.getCurrent()[1]+1});
                        m.setPath(tempList);
                        if(arr[obj.getCurrent()[0]][obj.getCurrent()[1]+1]=='t'){
                            m.setBulletCount(m.getBulletCount()+1);
                            queue.add(m);
                        }
                        else if(arr[obj.getCurrent()[0]][obj.getCurrent()[1]+1]=='m' && obj.getBulletCount()>0){
                            m.setBulletCount(m.getBulletCount()-1);
                            queue.add(m);
                        }
                        else{
                            queue.add(m);
                        }
                    }
                    if(isValid(obj.getCurrent()[0],obj.getCurrent()[1]-1,arr,tempArr)){
                        tempArr[obj.getCurrent()[0]][obj.getCurrent()[1]-1] = true;
                        Main m = new Main(new int[]{obj.getCurrent()[0],obj.getCurrent()[1]-1});
                        List<Integer[]> tempList = new ArrayList<>(obj.getPath());
                        tempList.add(new Integer[]{obj.getCurrent()[0],obj.getCurrent()[1]-1});
                        m.setPath(tempList);
                        if(arr[obj.getCurrent()[0]][obj.getCurrent()[1]-1]=='t'){
                            m.setBulletCount(m.getBulletCount()+1);
                            queue.add(m);
                        }
                        else if(arr[obj.getCurrent()[0]][obj.getCurrent()[1]-1]=='m' && obj.getBulletCount()>0){
                            m.setBulletCount(m.getBulletCount()-1);
                            queue.add(m);
                        }
                        else{
                            queue.add(m);
                        }
                    }
                }
        return null;
    }
    public static void printPath(Main temp){
        for(Integer[] a : temp.getPath()){
            System.out.println(a[0]+"--->"+a[1]);
        }
    }
    public static int[] startPoint(char[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(arr[i][j]=='s'){
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }
    public static boolean isValid(int i,int j,char[][] arr,boolean[][] tempArr){
        if(i<0 || i>=arr.length || j<0 || j>=arr[0].length){
            return false;
        }return arr[i][j]!='o' && !tempArr[i][j];
    }
}
