package com.applications.snakeandladder;

public class Snake {
    private int[] snakeStart;
    private int[] snakeEnd;

    public Snake(int[] snakeStart, int[] snakeEnd) {
        this.snakeStart = snakeStart;
        this.snakeEnd = snakeEnd;
    }

    public int[] getSnakeStart() {
        return snakeStart;
    }

    public void setSnakeStart(int[] snakeStart) {
        this.snakeStart = snakeStart;
    }

    public int[] getSnakeEnd() {
        return snakeEnd;
    }

    public void setSnakeEnd(int[] snakeEnd) {
        this.snakeEnd = snakeEnd;
    }
}
