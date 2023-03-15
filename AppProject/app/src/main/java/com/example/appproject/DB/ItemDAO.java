package com.example.appproject.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appproject.Item;
import com.example.appproject.User;

import java.util.List;

@Dao
public interface ItemDAO {

    @Insert
    void insert(Item... Items);

    @Update
    void update(Item... items);

    @Delete
    void delete(Item items);

    @Query("SELECT * FROM " + AppDatabase.ITEM_TABLE)
    List<Item> getAllItems();

    @Query("SELECT * FROM " + AppDatabase.ITEM_TABLE + " WHERE mItemName = :itemName ")
    List<Item> getItemsByName(String itemName);

    @Query("SELECT * FROM " + AppDatabase.ITEM_TABLE + " WHERE mItemName = :itemName ")
    Item getItemByName(String itemName);

    @Query("SELECT * FROM " + AppDatabase.ITEM_TABLE + " WHERE mItemId = :itemName ")
    int getItemId(String itemName);

    @Query("DELETE FROM " + AppDatabase.ITEM_TABLE + " WHERE mItemName = :itemName")
    void deleteItem(String itemName);
}
