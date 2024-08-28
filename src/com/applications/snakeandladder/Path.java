package com.applications.snakeandladder;

import java.util.List;

public class Path {
    private int[] arr;
    List<Integer[]> list;

    public Path(int[] arr, List<Integer[]> list) {
        this.arr = arr;
        this.list = list;
    }

    public List<Integer[]> getList() {
        return list;
    }

    public void setList(List<Integer[]> list) {
        this.list = list;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }
}
