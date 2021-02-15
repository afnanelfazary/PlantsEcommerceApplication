package com.example.plantsecommerceapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
 import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ProductActivity extends AppCompatActivity {
    private static final String TAG = "ProductActivity";
    TextView tvProductPrice;
    TextView tvProductType;
    TextView tvProductName;
    TextView tvProductDesc;
    ImageView ivProductImage;
    Product product;
    Button bOrder;
     Spinner spQuantity;
  AlertDialog.Builder builder;
 ImageButton Ibtn_backarrow ;
    public static final String CURRENCY = "$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_detail);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        builder = new AlertDialog.Builder(this);
        Ibtn_backarrow = (ImageButton) findViewById(R.id.Ibtn_backarrow);

        Bundle data = getIntent().getExtras();
        product = (Product) data.getSerializable("product");

        Log.d(TAG, "Product hashCode: " + product.hashCode());

        //Set Shopping Cart link
        setShoppingCartLink();

        //Retrieve views
        retrieveViews();

        //Set product properties
        setProductProperties();


        //On ordering of product
        onOrderProduct();


        Ibtn_backarrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ProductActivity.this, DrawerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setShoppingCartLink() {

  //      TextView tvViewShoppingCart = (TextView)findViewById(R.id.tvViewShoppingCart);
        SpannableString content = new SpannableString(getText(R.string.shopping_cart));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
//        tvViewShoppingCart.setText(content);
//        tvViewShoppingCart.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ProductActivity.this, ShoppingCartActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void retrieveViews() {
        tvProductName = (TextView) findViewById(R.id.tvProductName);
        tvProductDesc = (TextView) findViewById(R.id.tvProductDesc);
        ivProductImage = (ImageView) findViewById(R.id.ivProductImage);
         tvProductDesc = (TextView) findViewById(R.id.tvProductDesc);
         tvProductPrice = (TextView) findViewById(R.id.tvProductPrice);
        tvProductType = (TextView) findViewById(R.id.tvProductType);
         bOrder = (Button) findViewById(R.id.btn_AddToCart);

    }

    private void setProductProperties() {
         tvProductName.setText(product.getName());
        tvProductDesc.setText(product.getDescription());
        ivProductImage.setImageResource(product.getImageName());
        tvProductPrice.setText(CURRENCY + String.valueOf(product.getPrice()));
        tvProductType.setText(product.getProductType());
    }



    private void onOrderProduct() {
        bOrder.setOnClickListener(new OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ProductActivity.this);
                                     View mView =  getLayoutInflater().inflate(R.layout.dialog_spinner, null);
                                     mBuilder.setTitle("Add To Cart .. ?");
                                           mBuilder.setIcon(R.drawable.ic_baseline_add_shopping_cart_24);
                                          spQuantity = (Spinner) mView.findViewById(R.id.spQuantity);
                                          initializeQuantity();
                                          mBuilder.setPositiveButton("Add ", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialogInterface, int i) {
                                                  if(!spQuantity.getSelectedItem().toString().equalsIgnoreCase("Choose A Quantity")){
                                                      Cart cart = CartHelper.getCart();
                                                      Log.d(TAG, "Adding product: " + product.getName());
                                                      cart.add(product, Integer.valueOf(spQuantity.getSelectedItem().toString()));
                                                      Intent intent = new Intent(ProductActivity.this, ShoppingCartActivity.class);
                                                      startActivity(intent);
                                                      //Get notifications
                                                      Notifications notifications = NotificationsHelper.getCart();
                                                      Log.d(TAG, "Adding product: " + product.getName());
                                                      notifications.add(product, Integer.valueOf(spQuantity.getSelectedItem().toString()));
                                                      Intent AddToNotifications = new Intent(ProductActivity.this, NotificationActivity.class);
                                                  }

                                              }
                                          });
                                          mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialogInterface, int i) {
                                                  dialogInterface.dismiss();
                                              }
                                          });
                                          mBuilder.setView(mView);
                                          AlertDialog dialog = mBuilder.create();
                                          dialog.show();
                                      }
                                  });
    }
    private void initializeQuantity() {
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, R.layout.dialog_spinner_item, Constant.QUANTITY_LIST);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuantity.setAdapter(dataAdapter);
    }

}
