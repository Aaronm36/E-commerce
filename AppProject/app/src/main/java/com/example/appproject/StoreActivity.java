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

import com.example.appproject.DB.AppDatabase;
import com.example.appproject.DB.ItemDAO;
import com.example.appproject.DB.UserDAO;
import com.example.appproject.databinding.ActivityMainBinding;
import com.example.appproject.databinding.ActivityStoreBinding;

import java.util.List;

public class StoreActivity extends AppCompatActivity {

    ActivityStoreBinding mActivityStoreBinding;

    private Button mSearch;
    private Button mOrder;
    private Button mCart;
    private Button mItems;
    private Button mLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        mActivityStoreBinding = ActivityStoreBinding.inflate(getLayoutInflater());
        setContentView(mActivityStoreBinding.getRoot());

        mSearch = mActivityStoreBinding.buttonBuy;
        mOrder = mActivityStoreBinding.buttonOrder;
        mCart = mActivityStoreBinding.buttonCart;
        mItems =mActivityStoreBinding.buttonAllItems;
        mLogout = mActivityStoreBinding.buttonLogout2;

        mItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = allItemsActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SearchActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CancelActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        mCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CartActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LoginActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

    }


    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, StoreActivity.class);
        return intent;
    }
}