package com.example.appproject.DB;

import androidx.room.TypeConverter;

import com.example.appproject.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListTypeConverter {
    @TypeConverter
    public static ArrayList<Item> stringToItemList(String data){
        Type arrayListType = new TypeToken<ArrayList<Item>>(){}.getType();
        return new Gson().fromJson(data, arrayListType);
    }

    @TypeConverter
    public static String itemListToString(ArrayList<Item> items){
        Gson gson = new Gson();
        return gson.toJson(items);
    }
}

