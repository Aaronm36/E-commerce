package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appproject.DB.AppDatabase;
import com.example.appproject.DB.UserDAO;
import com.example.appproject.databinding.ActivityRemoveUsersBinding;
import com.example.appproject.databinding.ActivityUsersBinding;

import java.util.List;

public class RemoveUsersActivity extends AppCompatActivity {

    ActivityRemoveUsersBinding mActivityRemoveUsersBinding;

    private EditText mUser;
    private Button mRemove;
    private Button mBack;

    private UserDAO mUserDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_users);

        mActivityRemoveUsersBinding = ActivityRemoveUsersBinding.inflate(getLayoutInflater());
        setContentView(mActivityRemoveUsersBinding.getRoot());

        mUser = mActivityRemoveUsersBinding.editTextUsername;
        mRemove = mActivityRemoveUsersBinding.buttonRemoveUser3;
        mBack = mActivityRemoveUsersBinding.buttonL;

        getDatabase();

        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeUser();
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AdminActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });


    }

    private void getDatabase(){
        mUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDAO();
    }

    private void removeUser(){
        String username = mUser.getText().toString();
        mUserDAO.deleteUser(username);
        Toast.makeText(this, "User has been removed", Toast.LENGTH_SHORT).show();
    }

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, RemoveUsersActivity.class);
        return intent;
    }
}