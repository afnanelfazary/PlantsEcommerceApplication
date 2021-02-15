package com.example.plantsecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoneActivity extends AppCompatActivity {
     Button btn_Done;
    Product product = new Product();
    int  spQuantity =8 ;
    private static final String TAG = "DoneActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
 //       getSupportActionBar().hide();

        btn_Done=findViewById(R.id.btn_Done);
        btn_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Empty Cart when click on  Done
                final Cart cart = CartHelper.getCart();
                Log.d(TAG, "Clearing the shopping cart");
                cart.clear();
                 Intent goToMainActivity=new Intent(getApplicationContext(),DrawerActivity.class);
                startActivity(goToMainActivity);
                 finish();

            }
        });


    }

}