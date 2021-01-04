package com.example.pointstracker.model;

public class User {
    private String name;
    private int points;
    private int numLandlords;

    public User(String name) {
        this.name = name;
        this.points = 0;
        this.numLandlords = 0;
    }

    public User(String name, int points, int numLL) {
        this.name = name;
        this.points = points;
        this.numLandlords = numLL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNumLandlords() {
        return numLandlords;
    }

    public void setNumLandlords(int numLandlords) {
        this.numLandlords = numLandlords;
    }
}
