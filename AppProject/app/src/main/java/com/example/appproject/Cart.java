package com.example.appproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.appproject.DB.AppDatabase;

import java.util.List;

@Entity(tableName = AppDatabase.CART_TABLE)
public class Cart {

    @PrimaryKey(autoGenerate = true)
    private int mCartId;
    private String itemName;

    public Cart(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCartId() {
        return mCartId;
    }

    public void setCartId(int cartId) {
        mCartId = cartId;
    }

    public String toString() {
        return itemName + "\n";
    }
}
