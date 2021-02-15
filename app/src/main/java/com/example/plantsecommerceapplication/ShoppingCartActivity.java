package com.example.plantsecommerceapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
 import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ShoppingCartActivity extends AppCompatActivity {
    private static final String TAG = "ShoppingCartActivity";
    ListView lvCartItems;
     ImageView bClear;
    ImageButton bShop;
      Button btn_PayNow;
CartItem cartItem;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_cart);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        lvCartItems = (ListView) findViewById(R.id.rv);

        final Cart cart = CartHelper.getCart();
        final TextView tvTotalPrice = (TextView) findViewById(R.id.TV_TotalPrice);
        tvTotalPrice.setText(Constant.CURRENCY+ String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
        final TextView tvSubTotalPrice = (TextView) findViewById(R.id.TV_SubTotalPrice);
        tvSubTotalPrice.setText(Constant.CURRENCY+ String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP))+ 5.00);

        final CartItemAdapter cartItemAdapter = new CartItemAdapter(this);
        cartItemAdapter.updateCartItems(getCartItems(cart));
        lvCartItems.setAdapter(cartItemAdapter);
         bShop = (ImageButton) findViewById(R.id.Ibtn_backarrow);
          btn_PayNow=(Button) findViewById(R.id.btn_PayNow) ;
            bClear =(ImageView) findViewById(R.id.iv_deleteItem);
        bShop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ShoppingCartActivity.this, DrawerActivity.class);
                startActivity(intent);
            }
        });
        btn_PayNow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checking if Cart i not Empty
                  if (!cartItemAdapter.isEmpty() ){
                      Intent intent = new Intent(ShoppingCartActivity.this, DeliverActivity.class);
                      Bundle bundle = new Bundle();
                      bundle.putSerializable("product", (Serializable) cartItem);
                      Log.d(TAG, "View product: " + cartItem);
                      intent.putExtras(bundle);
                      startActivity(intent);
                  }
               else {
                      Toast.makeText(ShoppingCartActivity.this, "Please Add Product To Cart First !", Toast.LENGTH_SHORT).show();

                  }

            }
        });



//        lvCartItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                new AlertDialog.Builder(ShoppingCartActivity.this)
//                        .setTitle(getResources().getString(R.string.delete_item))
//                        .setMessage(getResources().getString(R.string.delete_item_message))
//                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                List<CartItem> cartItems = getCartItems(cart);
//                                cart.remove(cartItems.get(position-1).getProduct());
//                                cartItems.remove(position-1);
//                                cartItemAdapter.updateCartItems(cartItems);
//                                cartItemAdapter.notifyDataSetChanged();
//                                tvTotalPrice.setText(Constant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
//                            }
//                        })
//                        .setNegativeButton(getResources().getString(R.string.no), null)
//                        .show();
//                return false;
//            }
//        });

           bClear.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.d(TAG, "Clearing the shopping cart");
                 cart.clear();
                 cartItemAdapter.updateCartItems(getCartItems(cart));
                 cartItemAdapter.notifyDataSetChanged();
                 tvTotalPrice.setText(Constant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
                 tvSubTotalPrice.setText(Constant.CURRENCY+ String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP))+ 5.00);

             }
         });

    }

    private List<CartItem> getCartItems(Cart cart) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Current shopping cart: " + cart);

        Map<Saleable, Integer> itemMap = cart.getItemWithQuantity();

        for (Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct((Product) entry.getKey());
            cartItem.setQuantity(entry.getValue());
             cartItems.add(cartItem);
        }

        Log.d(TAG, "Cart item list: " + cartItems);
        return cartItems;
    }
}
