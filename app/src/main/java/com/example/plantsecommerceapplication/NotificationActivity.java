package com.example.plantsecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.Map;

public class NotificationActivity extends AppCompatActivity {

    private ListView listView;
    private static final String TAG = "NotificationActivity";
    public static final String CURRENCY = "$";
    ImageButton Ibtn_backarrow ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //       getSupportActionBar().hide();
        Ibtn_backarrow = (ImageButton) findViewById(R.id.Ibtn_backarrow);
        listView = (ListView) findViewById(R.id.rv);
        final Notifications notifications = NotificationsHelper.getCart();
        final NotificationRecycleViewAdapter adapter = new NotificationRecycleViewAdapter(this);
        adapter.updateCartItems(getCartItems(notifications));
        listView.setAdapter(adapter);
        Ibtn_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( NotificationActivity.this, DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private List<CartItem> getCartItems(Notifications notifications) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Current shopping cart: " + notifications);

        Map<Saleable, Integer> itemMap = notifications.getItemWithQuantity();

        for (Map.Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct((Product) entry.getKey());
            cartItem.setQuantity(entry.getValue());
            cartItems.add(cartItem);
        }

        Log.d(TAG, "Cart item list: " + cartItems);
        return cartItems;
    }

}
