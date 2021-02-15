package com.example.plantsecommerceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class PaymentActivity extends AppCompatActivity {
        Button Next  ;
       ImageButton Ibtn_backarrow ;
        EditText et_CardNumber , et_Cvv,et_ExpMonth,et_ExpYear;
     String   CardNumber,Cvv,ExpMonth,ExpYear;
        RadioButton rb_Cash , rb_CreditCard;
   RadioGroup myRadioGroup;
    Product product;
    int  spQuantity =8 ;

    private static final String TAG = "PaymentActivity";
    public static final String CURRENCY = "$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Ibtn_backarrow = (ImageButton) findViewById(R.id.Ibtn_backarrow);
         Next = findViewById(R.id.btn_Next);
        et_CardNumber  = findViewById(R.id.et_CardNumber);
                et_Cvv = findViewById(R.id.et_Cvv);
        et_ExpMonth = findViewById(R.id.et_ExpMonth);
                et_ExpYear = findViewById(R.id.et_ExpYear);
        rb_Cash = (RadioButton) findViewById(R.id.rb_Cash);
        rb_CreditCard = (RadioButton) findViewById(R.id.rb_CreditCard);
        myRadioGroup=findViewById(R.id.myRadioGroup);



        Ibtn_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( PaymentActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
           Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myRadioGroup.getCheckedRadioButtonId() == R.id.rb_CreditCard) {

                    CardNumber = et_CardNumber.getText().toString().trim();
                    Cvv = et_Cvv.getText().toString().trim();
                    ExpMonth = et_ExpMonth.getText().toString().trim();
                    ExpYear = et_ExpYear.getText().toString().trim();

                    if (CardNumber.isEmpty() ||  Cvv.isEmpty()  ||  ExpMonth.isEmpty() || ExpYear.isEmpty() ) {
                        Toast.makeText(PaymentActivity.this, "Please fill the form", Toast.LENGTH_SHORT).show();
                    }
else  {

                        Intent goToDoneActivity=new Intent(getApplicationContext(),DoneActivity.class);
                        startActivity(goToDoneActivity);
                        finish();
                    }

                }

                if (myRadioGroup.getCheckedRadioButtonId() == R.id.rb_Cash)
                {
                    Intent goToDoneActivity=new Intent(getApplicationContext(),DoneActivity.class);
                    startActivity(goToDoneActivity);
                    finish();

                }

            }
        });
    }}