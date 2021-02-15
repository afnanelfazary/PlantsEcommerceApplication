package com.example.plantsecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyWishlistActivity extends AppCompatActivity {

     private ListView listView;
    private static final String TAG = "MyWishlistActivity";
    public static final String CURRENCY = "$";
    ImageButton Ibtn_backarrow ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wishlist);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //       getSupportActionBar().hide();
        Ibtn_backarrow = (ImageButton) findViewById(R.id.Ibtn_backarrow);
        listView = (ListView) findViewById(R.id.rv);
        final WishList wishList = WishListHelper.getCart();
        final MyWishlistRecycleViewAdapter adapter = new MyWishlistRecycleViewAdapter(this);
        adapter.updateCartItems(getCartItems(wishList));
        listView.setAdapter(adapter);
        Ibtn_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MyWishlistActivity.this, DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private List<CartItem> getCartItems(WishList wishList) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Current shopping cart: " + wishList);

        Map<Saleable, Integer> itemMap = wishList.getItemWithQuantity();

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
