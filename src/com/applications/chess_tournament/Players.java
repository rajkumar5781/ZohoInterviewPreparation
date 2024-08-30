package com.applications.chess_tournament;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private List<Players> opponents;
    private double points = 0;

    public Players() {
        this.opponents = new ArrayList<>();
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public List<Players> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Players> opponents) {
        this.opponents = opponents;
    }
}
