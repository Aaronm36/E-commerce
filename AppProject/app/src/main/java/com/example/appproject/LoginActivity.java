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
import android.widget.Toast;

import com.example.appproject.DB.AppDatabase;
import com.example.appproject.DB.UserDAO;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    // added these two lines
    private static final String USER_ID_KEY = "com.example.appproject.userIdKey";
    private static final String PREFRENCE_KEY = "com.example.appproject.PrefrenceKey" ;

    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mButton;

    private UserDAO mUserDAO;

    private String mUsername;
    private String mPassword;

    private User mUser;

    // added private int mUserId
    private int mUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //added check for users

        getDatabase();

        wireupDisplay();

        checkForUser();

    }

    private void wireupDisplay() {
        mUsernameField = findViewById(R.id.editTextUserName);
        mPasswordField = findViewById(R.id.editTextPassword);
        mButton = findViewById(R.id.buttonLogin2);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                if(checkForUserInDatabase()){
                    if(!validatePassword()){
                        Toast.makeText(LoginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    } else {
                        isAdmin();
                    }
                }
            }
        });
    }

    private void isAdmin(){
        if(mUser != null && mUser.getUserName().equals("Admin2")){
            Intent intent = AdminActivity.intentFactory(getApplicationContext());
            startActivity(intent);
        } else {
            Intent intent = StoreActivity.intentFactory(getApplicationContext());
            startActivity(intent);
        }
    }

    private boolean validatePassword(){
        return mUser.getPassword().equals(mPassword);
    }

    private void getValuesFromDisplay(){
        mUsername = mUsernameField.getText().toString();
        mPassword = mPasswordField.getText().toString();
    }

    private boolean checkForUserInDatabase(){
        mUser = mUserDAO.getUsersByUsername(mUsername);
        if(mUser == null){
            Toast.makeText(this, "no user " + mUsername + " found", Toast.LENGTH_SHORT).show();
            return false;
        }
           return true;

    }

    private void getDatabase(){
        mUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDAO();
    }

    // added checkforUser and intent factory at the bottom
    private void checkForUser() {
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);

        if(mUserId != -1){
            return;
        }

        SharedPreferences preferences = this.getSharedPreferences(PREFRENCE_KEY, Context.MODE_PRIVATE);
        mUserId = preferences.getInt(USER_ID_KEY, -1);

        if(mUserId != -1){
            return;
        }

        List<User> users = mUserDAO.getAllUsers();
        if(users.size() <= 0){ //important area
            User adminUser = new User("Admin2", "Admin2");
            User defaultUser = new User("testuser1", "testuser1");
            mUserDAO.insert(adminUser, defaultUser);
        }
    }



    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }
}