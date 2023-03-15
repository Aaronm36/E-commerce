package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appproject.DB.AppDatabase;
import com.example.appproject.DB.UserDAO;
import com.example.appproject.databinding.ActivityUsersBinding;

import java.util.List;

public class UsersActivity extends AppCompatActivity {

    ActivityUsersBinding mActivityUsersBinding;

    private TextView mUsers;
    private Button mBack;

    private UserDAO mUserDAO;

    private List<User> mUserList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mActivityUsersBinding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(mActivityUsersBinding.getRoot());

        mUsers = mActivityUsersBinding.textView;
        mBack = mActivityUsersBinding.buttonReturn;

        mUsers.setMovementMethod(new ScrollingMovementMethod());

        getDatabase();

        refreshDisplay();

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


    private void refreshDisplay(){
        mUserList = mUserDAO.getAllUsers();
        if(!mUserList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(User log : mUserList){
                sb.append(log.toString());
            }
            mUsers.setText(sb.toString());
        }else{
            mUsers.setText("no users");
        }
    }

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, UsersActivity.class);
        return intent;
    }
}