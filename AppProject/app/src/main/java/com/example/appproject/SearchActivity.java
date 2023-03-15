package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appproject.DB.AppDatabase;
import com.example.appproject.DB.CartDAO;
import com.example.appproject.DB.ItemDAO;
import com.example.appproject.DB.UserDAO;
import com.example.appproject.databinding.ActivitySearchBinding;
import com.example.appproject.databinding.ActivityStoreBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.appproject.userIdKey";
    private static final String PREFRENCE_KEY = "com.example.appproject.PrefrenceKey" ;

    ActivitySearchBinding mActivitySearchBinding;

    private TextView mItems;
    private EditText msearchItems;
    private Button mSearch;
    private Button mBack;

    private ItemDAO mItemDAO;
    private List<Item> mItemList;
//    ArrayList<String> itemCart;
//
//    User user;
//
    private UserDAO mUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mActivitySearchBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(mActivitySearchBinding.getRoot());

        mItems = mActivitySearchBinding.textViewItems;
        msearchItems = mActivitySearchBinding.editTextItemName;
        mSearch = mActivitySearchBinding.buttonSearch;
        mBack = mActivitySearchBinding.buttonBack;

        getItemDatabase();
        getUserDatabase();

//        setUpSharedPreferences();

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshDisplay();
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = StoreActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    private void getItemDatabase(){
        mItemDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getItemDAO();
    }

    private void getUserDatabase(){
        mUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDAO();

    }

    private void refreshDisplay(){
        String name = msearchItems.getText().toString();
        mItemList = mItemDAO.getItemsByName(name);
        if(!mItemList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(Item log : mItemList){
                sb.append(log.toString());
            }
            mItems.setText(sb.toString());
        }else{
            mItems.setText(R.string.no_items);
        }

//        itemCart = new ArrayList<>();
//        for(Item item : mItemList){
//            if(item.getItemName().isEmpty()){
//                continue;
//            }
//            itemCart.add(item.getItemName());
//        }

//        itemCart.toArray();
//
//        Item cartItem = mItemDAO.getItemByName(name);
//
//        user.getUserCart().add(cartItem);
//        mUserDAO.update(user);
    }

//    private void setUpSharedPreferences(){
//        SharedPreferences preferences = getSharedPreferences(PREFRENCE_KEY, Context.MODE_PRIVATE);
//        int userId = preferences.getInt(USER_ID_KEY, -1);
//
//        if(userId != -1){
//            user = mUserDAO.getUserByUserId(userId);
//        }
//    }




    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }
}