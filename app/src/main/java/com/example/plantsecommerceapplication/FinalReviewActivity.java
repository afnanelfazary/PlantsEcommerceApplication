package com.example.plantsecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FinalReviewActivity extends AppCompatActivity {
    // Add RecyclerView member
 Product product;
    private static final String TAG = "FinalReviewActivity";
    public static final String CURRENCY = "$";
    private ListView listView;
    Button btn_PayNow;
    TextView tv_Address;
    ImageView iv_AddressICon;
  CartItem cartItem;
  int spQuantity =11 ;
    ImageButton Ibtn_backarrow ;
  TextView btn_ChangeList;
  TextView TV_ChangeAddressBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_review);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            getSupportActionBar().hide();
        tv_Address = (TextView) findViewById(R.id.tv_Address);
        Ibtn_backarrow = (ImageButton) findViewById(R.id.Ibtn_backarrow);
        btn_ChangeList = (TextView ) findViewById(R.id.btn_ChangeList) ;
 //       TV_ChangeAddressBtn = (TextView) findViewById( R.id.TV_ChangeAddressBtn);
        listView = findViewById(R.id.rv);
            initializeData();
            initializeAdapter();

        btn_PayNow=findViewById(R.id.btn_PayNow);
        btn_PayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), PaymentActivity.class);
                startActivity(intent);
                finish();


            }
        });


        Ibtn_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( FinalReviewActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_ChangeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( FinalReviewActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        TV_ChangeAddressBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent( FinalReviewActivity.this, DeliverActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }
    ///Get Address
        private void initializeData() {
            Bundle data = getIntent().getExtras();
            product =  (Product) data.getSerializable("product");
            Log.d(TAG, "View product: " + product);
        tv_Address.setText(product.getAddress());



        }

        private void initializeAdapter() {
            final Cart cart = CartHelper.getCart();
       //     cartItem.getProduct().getAddress();
            FinalReviewRecycleViewAdapter adapter = new FinalReviewRecycleViewAdapter(this);
            adapter.updateCartItems(getCartItems(cart));
            listView.setAdapter(adapter);
        }
    private List<CartItem> getCartItems(Cart cart) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Current shopping cart: " + cart);

        Map<Saleable, Integer> itemMap = cart.getItemWithQuantity();

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