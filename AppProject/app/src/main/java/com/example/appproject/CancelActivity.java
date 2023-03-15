package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appproject.DB.AppDatabase;
import com.example.appproject.DB.ItemDAO;
import com.example.appproject.databinding.ActivityCancelBinding;

public class CancelActivity extends AppCompatActivity {

    ActivityCancelBinding mActivityCancelBinding;

    private ItemDAO mItemDAO;
    private Button mBack;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        mActivityCancelBinding = ActivityCancelBinding.inflate(getLayoutInflater());
        setContentView(mActivityCancelBinding.getRoot());

        mBack = mActivityCancelBinding.buttonBack;


        getDatabase();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = StoreActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

    }


    private void getDatabase(){
        mItemDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getItemDAO();
    }

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, CancelActivity.class);
        return intent;
    }
}