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

import com.example.appproject.DB.AppDatabase;
import com.example.appproject.DB.ItemDAO;
import com.example.appproject.databinding.ActivityItemsBinding;
import com.example.appproject.databinding.ActivityUsersBinding;

import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    ActivityItemsBinding mActivityItemsBinding;

    private TextView mItems;
    private Button mBack;

    private ItemDAO mItemDAO;

    private List<Item> mItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        mActivityItemsBinding = ActivityItemsBinding.inflate(getLayoutInflater());
        setContentView(mActivityItemsBinding.getRoot());

        mItems = mActivityItemsBinding.textViewItemList;
        mBack = mActivityItemsBinding.buttonReturn;

        mItems.setMovementMethod(new ScrollingMovementMethod());

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
        mItemDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getItemDAO();
    }


    private void refreshDisplay(){
        mItemList = mItemDAO.getAllItems();
        if(!mItemList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(Item log : mItemList){
                sb.append(log.toString());
            }
            mItems.setText(sb.toString());
        }else{
            mItems.setText("No Items have been added yet");
        }
    }

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, ItemsActivity.class);
        return intent;
    }
}