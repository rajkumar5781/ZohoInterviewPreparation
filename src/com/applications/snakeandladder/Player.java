package com.applications.snakeandladder;

public class Player {
    private int[] position;

    public Player(int[] values) {
        this.position = values;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
}
