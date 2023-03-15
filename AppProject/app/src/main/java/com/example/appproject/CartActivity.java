package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appproject.DB.AppDatabase;
import com.example.appproject.DB.CartDAO;
import com.example.appproject.databinding.ActivityCartBinding;
import com.example.appproject.databinding.ActivityCreateAccountBinding;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding mActivityCartBinding;

    private TextView mText;
    private Button mBack;
    private CartDAO mCartDAO;
    private List<Cart> mCartList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getDatabase();

        mActivityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(mActivityCartBinding.getRoot());

        mText = mActivityCartBinding.textViewCartItems;
        mBack = mActivityCartBinding.buttonBack;


        refreshDisplay();




        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = StoreActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }


    private void getDatabase(){
        mCartDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getCartDAO();
    }


    private void refreshDisplay(){
        mCartList = mCartDAO.getAllCartItems();
        if(!mCartList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(Cart log : mCartList){
                sb.append(log.toString());
            }
            mText.setText(sb.toString());
        }else{
            mText.setText("no users");
        }
    }


    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, CartActivity.class);
        return intent;
    }


}