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
import com.example.appproject.databinding.ActivityDeleteAccountBinding;
import com.example.appproject.databinding.ActivityMainBinding;

public class DeleteAccountActivity extends AppCompatActivity {

    ActivityDeleteAccountBinding mActivityDeleteAccountBinding;

    private EditText mUser;
    private Button mDelete;

    private UserDAO mUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        mActivityDeleteAccountBinding = ActivityDeleteAccountBinding.inflate(getLayoutInflater());
        setContentView(mActivityDeleteAccountBinding.getRoot());

        getDatabase();

        mUser = mActivityDeleteAccountBinding.editTextAccountDelete;
        mDelete = mActivityDeleteAccountBinding.buttonDelete;

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeUser();
                Toast.makeText(DeleteAccountActivity.this, "Account has been deleted", Toast.LENGTH_SHORT).show();
                Intent intent = MainActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    private void removeUser(){
        String username = mUser.getText().toString();
        mUserDAO.deleteUser(username);
        Toast.makeText(this, "User has been removed", Toast.LENGTH_SHORT).show();
    }

    private void getDatabase(){
        mUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDAO();
    }


    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, DeleteAccountActivity.class);
        return intent;
    }
}