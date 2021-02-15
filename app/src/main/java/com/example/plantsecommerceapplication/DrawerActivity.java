package com.example.plantsecommerceapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;


public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView ivProfileImage;
    TextView txUserName, txEmailAddress;
     private List<CartItem> cartItems;
    boolean clickTwiceToExit = false;

    private static final String TAG = "DrawerActivity";
     //private GoogleSignInClient mSignInClient;
    private FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseReference;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    String googleUserName, userEmail , userPhoto;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //make Home Fragment The main
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        View header = navigationView.getHeaderView(0);
        navigationView.setCheckedItem(R.id.nav_home);
        ivProfileImage = header.findViewById(R.id.ivProfileImage);
        txUserName = header.findViewById(R.id.txUserName);
        txEmailAddress = header.findViewById(R.id.txEmailAddress);

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

        if (currentUser != null) {
            googleUserName = currentUser.getDisplayName();
            txUserName.setText(googleUserName);
            userEmail = currentUser.getEmail();
            txEmailAddress.setText(userEmail);

            if (currentUser.getPhotoUrl() == null) {
                ivProfileImage.setImageResource(R.drawable.ic_baseline_account_circle_24);

            } else {
                userPhoto = String.valueOf(currentUser.getPhotoUrl());
                Picasso.with(this).load(userPhoto).into(ivProfileImage);


            }
        }
        //   Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//         final View view = inflater.inflate(R.layout.fragment_home, null);
//              ImageButton  SideNav= (ImageButton) view.findViewById(R.id.ib_SideNav)
//
//         Toolbar toolbar = findViewById(R.id.ib_SideNav);
//      toggle = new ActionBarDrawerToggle(this, drawerLayout, SideNav,
//          R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//         drawerLayout.addDrawerListener(toggle);
//         toggle.syncState();


        //        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
        //  mSignInClient = GoogleSignIn.getClient(this, gso);

// Sign in with Google
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            googleUserName = signInAccount.getDisplayName();
            txUserName.setText(googleUserName);
            userEmail = currentUser.getEmail();
            txEmailAddress.setText(userEmail);
            if (currentUser.getPhotoUrl() == null) {
                ivProfileImage.setImageResource(R.drawable.ic_baseline_account_circle_24);

            }
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
      case R.id.nav_cart:

                    Intent intent = new Intent(DrawerActivity.this, ShoppingCartActivity.class);
                    startActivity(intent);

                         break;
            case R.id.nav_wishlist:
                Intent intent1 = new Intent(DrawerActivity.this, MyWishlistActivity.class);
                startActivity(intent1);
                break;
//            case R.id.nav_orders:
//                Intent intent2 = new Intent(getApplicationContext(), OrdersActivity.class);
//                startActivity(intent2);
//                break;
            case R.id.nav_notifications:
                Intent intent12 = new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(intent12);
                break;
            case R.id.nav_home:
                Intent intent3 = new Intent(getApplicationContext(), DrawerActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.nav_log_out:

                FirebaseAuth mAuth;
                mAuth=FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent inten = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(inten);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

@Override
protected void onStart() {
    super.onStart();
    if (currentUser==null)
    {
        openLogin ();
}
    }
            private  void openLogin ()
{
        Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
        startActivity(intent);
        finish();

    }

    //    @Override
    public void onBackPressed() {
        if (clickTwiceToExit) {  //check if the value is true
            super.onBackPressed(); // close the app
        } else {
            this.clickTwiceToExit = true;  // set the value for true

            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

            // if the user waited more than 3 seconds , set the value false
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clickTwiceToExit = false;
                }
            }, 3000);
        }
        //show toast to let the user click again quickly
    }


}