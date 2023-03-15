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
import com.example.appproject.databinding.ActivityCreateAccountBinding;

public class CreateAccountActivity extends AppCompatActivity {

    ActivityCreateAccountBinding mActivityCreateAccountBinding;
    private EditText mUsername;
    private EditText mPassword;
    private Button mButton;
    private UserDAO mUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        getDatabase();

        mActivityCreateAccountBinding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(mActivityCreateAccountBinding.getRoot());

        mPassword = mActivityCreateAccountBinding.editTextNewPassword;
        mUsername = mActivityCreateAccountBinding.editTextNewUsername;
        mButton = mActivityCreateAccountBinding.buttonSignup;

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitUser();
                Intent intent = MainActivity.intentFactory(getApplicationContext());
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

    private void submitUser(){
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();


        if(checkUsername()){
            User log = new User(username, password);
            mUserDAO.insert(log);
            Toast.makeText(CreateAccountActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "This username already exists please try again", Toast.LENGTH_SHORT).show();
        }



    }

    private boolean checkUsername(){
        String username = mUsername.getText().toString();


        if(mUserDAO.getUsersByUsername(username) == null){
            return true;
        } else {
            return false;
        }
    }

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, CreateAccountActivity.class);
        return intent;
    }



}