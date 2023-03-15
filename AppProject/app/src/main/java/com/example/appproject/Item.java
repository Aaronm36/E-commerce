package com.example.appproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.appproject.DB.AppDatabase;

@Entity(tableName = AppDatabase.ITEM_TABLE)
public class Item {

    @PrimaryKey(autoGenerate = true)
    private int mItemId;
    private String mItemName;
    private String mItemPrice;
    private int mItemQuantity;

    public Item(String itemName, String itemPrice, int itemQuantity){
        mItemName = itemName;
        mItemPrice = itemPrice;
        mItemQuantity = itemQuantity;
    }

    public int getItemId() {
        return mItemId;
    }

    public void setItemId(int itemId) {
        mItemId = itemId;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }

    public String getItemPrice() {
        return mItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        mItemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return mItemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        mItemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "Item Name: " + mItemName + "\n" +
                "Item Price: $" + mItemPrice + "\n" +
                "Item Quantity: " + mItemQuantity + "\n"
                + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + "\n";
    }
}
