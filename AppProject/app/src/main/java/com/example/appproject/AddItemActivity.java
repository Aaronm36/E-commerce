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
import android.widget.Toast;

import com.example.appproject.DB.AppDatabase;
import com.example.appproject.DB.ItemDAO;
import com.example.appproject.databinding.ActivityAddItemBinding;

public class AddItemActivity extends AppCompatActivity {

    ActivityAddItemBinding mActivityAddItemBinding;

    private EditText mName;
    private EditText mPrice;
    private EditText mQuantity;
    private Button mSubmit;
    private Button mBack;

    private ItemDAO mItemDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        getDatabase();

        mActivityAddItemBinding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(mActivityAddItemBinding.getRoot());

        mName = mActivityAddItemBinding.editTextItemName2;
        mPrice = mActivityAddItemBinding.editTextItemPrice;
        mQuantity = mActivityAddItemBinding.editTextItemQuantity;
        mSubmit = mActivityAddItemBinding.buttonAdd;
        mBack = mActivityAddItemBinding.buttonBack;



        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitItem();
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

    private void submitItem(){
        String itemName = mName.getText().toString();
        String itemPrice = mPrice.getText().toString();
        int itemQuantity = Integer.parseInt(mQuantity.getText().toString());

        Item log  = new Item(itemName, itemPrice, itemQuantity);
        mItemDAO.insert(log);

        Toast.makeText(this, "Item has been added", Toast.LENGTH_SHORT).show();
    }

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, AddItemActivity.class);
        return intent;
    }
}