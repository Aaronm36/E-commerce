package com.example.appproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import com.example.appproject.DB.AppDatabase;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = AppDatabase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int mUserId;
    private String mUserName;
    private String mPassword;
    private ArrayList<Item> userCart;

    public User(String userName, String password) {
        mUserName = userName;
        mPassword = password;
        userCart = new ArrayList<>();
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public ArrayList<Item> getUserCart() {
        return userCart;
    }

    public void setUserCart(ArrayList<Item> userCart) {
        this.userCart = userCart;
    }

    @Override
    public String toString() {
        return "Username: " + mUserName + "\n";
    }
}
