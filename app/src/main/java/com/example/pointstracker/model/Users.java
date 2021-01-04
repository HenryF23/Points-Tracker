package com.example.pointstracker.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;

public class Users implements Iterable<User> {
    private static Users instance;
    private ArrayList<User> myUsers = new ArrayList<>();

    public static Users getInstance(){
        if(instance == null)
            instance = new Users();
        return instance;
    }

    public void addUser(User user) {
        myUsers.add(user);
    }

    public void removeUser(int index) {
        myUsers.remove(index);
    }

    public User getUser(int index) {
        return myUsers.get(index);
    }

    public void updateUser(int index, User user) {
        myUsers.set(index, user);
    }

    public void setUserScore(int index, int score) {
        myUsers.get(index).setPoints(score);
    }

    public void removeAllUsers() {
        myUsers.clear();
    }

    public ArrayList<User> getMyUsers() {
        return myUsers;
    }

    public void removeAllScores() {
        for (User user : myUsers) {
            user.setPoints(0);
            user.setNumLandlordsLose(0);
            user.setNumLandlordsWin(0);
            user.setNumPLose(0);
            user.setNumPWin(0);
        }
    }

    public void updateScoreLandlordWin(boolean isWin, int index) {
        int n = 0;

        for(User user : myUsers) {
            if(n == index) {
                if(isWin){
                    user.setPoints(user.getPoints() + 2);
                    user.setNumLandlordsWin(user.getNumLandlordsWin() + 1);
                } else {
                    user.setPoints(user.getPoints() - 2);
                    user.setNumLandlordsLose(user.getNumLandlordsLose() + 1);
                }
            }
            else {
                if(!isWin) {
                    user.setPoints(user.getPoints() + 1);
                    user.setNumPWin(user.getNumPWin() + 1);
                } else {
                    user.setPoints(user.getPoints() - 1);
                    user.setNumPLose(user.getNumPLose() + 1);
                }
            }
            n++;
        }
    }

    @NonNull
    @Override
    public Iterator<User> iterator() {
        return myUsers.iterator();
    }
}
