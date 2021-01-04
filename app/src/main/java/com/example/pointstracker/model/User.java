package com.example.pointstracker.model;

public class User {
    private String name;
    private int points;
    private int numLandlordsWin;
    private int numLandlordsLose;
    private int numPWin;
    private int numPLose;

    public User(String name) {
        this.name = name;
        this.points = 0;
        this.numLandlordsWin = 0;
        this.numLandlordsLose = 0;
        this.numPWin = 0;
        this.numPLose = 0;
    }

    public User(String name, int points, int numLandlordsWin, int numLandlordsLose, int numPWin, int numPLose) {
        this.name = name;
        this.points = points;
        this.numLandlordsWin = numLandlordsWin;
        this.numLandlordsLose = numLandlordsLose;
        this.numPWin = numPWin;
        this.numPLose = numPLose;
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

    public int getNumLandlordsWin() {
        return numLandlordsWin;
    }

    public void setNumLandlordsWin(int numLandlordsWin) {
        this.numLandlordsWin = numLandlordsWin;
    }

    public int getNumLandlordsLose() {
        return numLandlordsLose;
    }

    public void setNumLandlordsLose(int numLandlordsLose) {
        this.numLandlordsLose = numLandlordsLose;
    }

    public int getNumPWin() {
        return numPWin;
    }

    public void setNumPWin(int numPWin) {
        this.numPWin = numPWin;
    }

    public int getNumPLose() {
        return numPLose;
    }

    public void setNumPLose(int numPLose) {
        this.numPLose = numPLose;
    }
}
