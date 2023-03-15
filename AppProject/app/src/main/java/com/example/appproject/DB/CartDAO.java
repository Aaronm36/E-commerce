package com.example.appproject.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appproject.Cart;

import java.util.List;

@Dao
public interface CartDAO {

    @Insert
    void insert(Cart... carts);

    @Update
    void update(Cart... carts);

    @Delete
    void delete(Cart carts);

    @Query("SELECT * FROM " + AppDatabase.CART_TABLE)
    List<Cart> getAllCartItems();
}
