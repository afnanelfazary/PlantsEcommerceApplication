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

public class OrdersActivity extends AppCompatActivity {
    ImageButton Ibtn_backarrow ;
    private ListView listView;
    private static final String TAG = "OrdersActivity";
    public static final String CURRENCY = "$";
   Product product;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //       getSupportActionBar().hide();
        listView = (ListView) findViewById(R.id.rv);
        final Orders orders = OrderHelper.getCart();
        final OrderAdapter adapter = new OrderAdapter(this);
        adapter.updateCartItems(getCartItems(orders));
        listView.setAdapter(adapter);
        Ibtn_backarrow =findViewById(R.id.Ibtn_backarrow );
        Ibtn_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( OrdersActivity.this, DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    private List<CartItem> getCartItems(Orders orders) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Current shopping cart: " + orders);

        Map<Saleable, Integer> itemMap = orders.getItemWithQuantity();

        for (Map.Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct((Product) entry.getKey());
            cartItem.setQuantity(entry.getValue());
            cartItems.add(cartItem);
            Bundle data = getIntent().getExtras();
            product =  (Product) data.getSerializable("product");
            Log.d(TAG, "View product: " + product);
        }

        Log.d(TAG, "Cart item list: " + cartItems);
        return cartItems;
    }


}
