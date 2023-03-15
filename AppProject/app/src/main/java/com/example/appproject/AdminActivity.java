package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appproject.databinding.ActivityAdminBinding;
import com.example.appproject.databinding.ActivityStoreBinding;

public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding mActivityAdminBinding;

    private Button mItem;
    private Button mUsers;
    private Button mItems;
    private Button mLogout;
    private Button mRemoveUsers;
    private Button mRemoveItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mActivityAdminBinding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(mActivityAdminBinding.getRoot());

        mItem = mActivityAdminBinding.buttonItem;
        mUsers = mActivityAdminBinding.buttonUsers;
        mItems = mActivityAdminBinding.buttonItemList;
        mLogout = mActivityAdminBinding.buttonLogout;
        mRemoveUsers = mActivityAdminBinding.buttonRemoveUsers;
        mRemoveItems = mActivityAdminBinding.buttonRemoveItem;



        mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddItemActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        mUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = UsersActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        mItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ItemsActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });


        mRemoveUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = RemoveUsersActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        mRemoveItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = RemoveItemsActivity.intentFactory(getApplicationContext());
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
        Intent intent = new Intent(context, AdminActivity.class);
        return intent;
    }
}