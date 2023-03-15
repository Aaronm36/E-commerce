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
import com.example.appproject.DB.ItemDAO;
import com.example.appproject.databinding.ActivityRemoveItemsBinding;

public class RemoveItemsActivity extends AppCompatActivity {

    ActivityRemoveItemsBinding mActivityRemoveItemsBinding;

    private EditText mRemoveItem;
    private Button mRemove;
    private Button mBack;

    private ItemDAO mItemDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_items);

        mActivityRemoveItemsBinding = ActivityRemoveItemsBinding.inflate(getLayoutInflater());
        setContentView(mActivityRemoveItemsBinding.getRoot());

        mRemoveItem = mActivityRemoveItemsBinding.editTextRemoveItemText;
        mRemove = mActivityRemoveItemsBinding.buttonRemove;
        mBack = mActivityRemoveItemsBinding.buttonL2;

        getDatabase();

        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem();
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
        mItemDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getItemDAO();
    }

    private void removeItem(){
        String itemName = mRemoveItem.getText().toString();
        mItemDAO.deleteItem(itemName);
        Toast.makeText(this, "Item has been removed", Toast.LENGTH_SHORT).show();
    }


    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, RemoveItemsActivity.class);
        return intent;
    }
}