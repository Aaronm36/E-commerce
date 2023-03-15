package com.example.appproject.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.appproject.Cart;
import com.example.appproject.Item;
import com.example.appproject.User;

@Database(entities = {User.class, Item.class, Cart.class}, version = 1)
@TypeConverters(ListTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "LOGIN_DATABASE";
    public static final String USER_TABLE = "USER_TABLE";
    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String CART_TABLE = "CART_TABLE";

    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract UserDAO getUserDAO();
    public abstract ItemDAO getItemDAO();
    public abstract CartDAO getCartDAO();

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME).build();
                }
            }
        }
        return instance;
    }
}
