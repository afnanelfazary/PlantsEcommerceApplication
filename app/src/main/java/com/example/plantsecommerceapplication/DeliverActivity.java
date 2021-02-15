package com.example.plantsecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.math.BigDecimal;

public class DeliverActivity extends AppCompatActivity {

    //List <Product>  products;
   Product product = new Product();
    CartItem cartItem;
    private static final String TAG = "DeliverActivity";
    public static final String CURRENCY = "$";
    private RecyclerView recyclerView;
     TextView pQuantity,SubTotal;
      Button btn_Next;
      Button btn_AddNewAddress;
      Button btn_bottomAddAddress;
      String addNewAddress;
      ImageView iv_editAddress;
      TextView tv_Address;
    ImageButton Ibtn_backarrow ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //getSupportActionBar().hide();
        pQuantity=findViewById(R.id.TV_NumOfItems);
        SubTotal=findViewById(R.id.TV_TotalPrice);
        tv_Address = findViewById(R.id.tv_Address);
        btn_AddNewAddress=findViewById(R.id.btn_AddNewAddress);
        iv_editAddress = findViewById(R.id.iv_editAddress);
        btn_Next=findViewById(R.id.btn_Next);
        Ibtn_backarrow = (ImageButton) findViewById(R.id.Ibtn_backarrow);

            //Get ITems Quantity and Total Price
        Bundle data = getIntent().getExtras();
        cartItem =  (CartItem) data.getSerializable("product");
         Log.d(TAG, "View product: " + cartItem);
          initializeData();

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if Address TextView is Empty

                           if(!tv_Address.getText().toString().trim().equals("") && tv_Address.getText().length()> 0 && !tv_Address.getText().toString().isEmpty() && tv_Address.getText()!=null)
                           {

                    Intent intent = new Intent(DeliverActivity.this, FinalReviewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("product", product);
                    Log.d(TAG, "Adding product: " + product.getName());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();

                }

                else {
                    Toast.makeText(DeliverActivity.this, "Please Add Address", Toast.LENGTH_SHORT).show();

                }
            }
        });


    // Bottom Sheet
         BottomSheetDialog    bottomSheetDialog = new BottomSheetDialog(DeliverActivity.this);
        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        btn_bottomAddAddress= bottomSheetDialogView.findViewById(R.id.btn_bottomAddAddress);
          btn_AddNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();

                btn_bottomAddAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText et_addNewAddress = (EditText) bottomSheetDialogView.findViewById(R.id.et_addNewAddress);
                        addNewAddress =  et_addNewAddress.getText().toString();

                        if (!addNewAddress.isEmpty()) {
                                    product.setAddress(addNewAddress);
                                   tv_Address.setText(product.getAddress());
                            et_addNewAddress.getText().clear();
                             bottomSheetDialog.hide();
                            bottomSheetDialog.cancel();

                          }

                        else {

                            Toast.makeText(DeliverActivity.this, "Please Add Address", Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.show();

                        }

                    }
                });
             }
    });
        iv_editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
                EditText et_addNewAddress = (EditText) bottomSheetDialogView.findViewById(R.id.et_addNewAddress);
             et_addNewAddress.setText(product.getAddress());


                btn_bottomAddAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         addNewAddress =  et_addNewAddress.getText().toString();

                        if (!addNewAddress.isEmpty() ) {
                            product.setAddress(addNewAddress);
                            tv_Address.setText(product.getAddress());
                            et_addNewAddress.getText().clear();
                            bottomSheetDialog.hide();
                        }
                        else {
                            Toast.makeText(DeliverActivity.this, "Please Add Address", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


        Ibtn_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DeliverActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });




    }


    private void initializeData() {
        final Cart cart = CartHelper.getCart();
           pQuantity.setText(String.valueOf(cart.getTotalQuantity()));
           SubTotal.setText(Constant.CURRENCY+ String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP))+ 5.00);
    }




}

