package com.example.plantsecommerceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;
//pw2KV90SJzrrMwfgUQIbWtX2WpA=

public class SigninActivity extends AppCompatActivity {
 TextView tv_SignUp,tv_forgetpassword ;
    Button Btn_Login,btn_signinwithgoogle;
    LoginButton btn_signinwithfacebook ;
    EditText email,password;
    String   emailAddress,Password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
     private FirebaseUser currentUser;
    boolean clickTwiceToExit = false;
    //Sign in with Google
    private GoogleSignInClient mGoogleSignInClient;
   private final static int RC_SIGN_IN  =12345;
   //Sigin in with facebook
 //  private final static int RC_SIGN_IN  =12345;
    private static final String TAG = "FacebookAuthentication";
    private CallbackManager mCallbackManager;
 private  static  final String EMAIL = "email";

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
          openDrawer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signin);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        getSupportActionBar().hide();
        tv_SignUp=findViewById(R.id.tv_SignUp);
        tv_forgetpassword=findViewById(R.id.forgetPassword);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btn_signinwithgoogle = findViewById(R.id.btn_signinwithgoogle);

        //FireBase
        mAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
                    startActivity(intent);
                    finish();
                }
            }


        };
        //underline text
        tv_SignUp.setPaintFlags(tv_SignUp.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv_forgetpassword.setPaintFlags(tv_SignUp.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        tv_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToForgetPasswordActivity=new Intent(getApplicationContext(),ForgetPasswordActivity.class);
                startActivity(goToForgetPasswordActivity);
                finish();

            }
        });


        //Sign Up Activity
        tv_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignUpActivity=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(goToSignUpActivity);
                finish();

            }
        });

        //SignIn Button
        Btn_Login=findViewById(R.id.btn_signin);
        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAddress = email.getText().toString().trim();
                Password = password.getText().toString().trim();
                if (emailAddress.isEmpty() && !Password.isEmpty()) {
                    Toast.makeText(SigninActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                }
                if (!emailAddress.isEmpty() && Password.isEmpty()) {
                    Toast.makeText(SigninActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                }
                if (emailAddress.isEmpty() && Password.isEmpty()) {
                    Toast.makeText(SigninActivity.this, "Please Enter  Your Email and Password", Toast.LENGTH_SHORT).show();
                }
                if (!emailAddress.isEmpty() && !Password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(emailAddress, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SigninActivity.this, "Welcome To Plants Shop ", Toast.LENGTH_SHORT).show();
                                Intent goToMainActivity = new Intent(getApplicationContext(), DrawerActivity.class);
                                startActivity(goToMainActivity);
                                finish();
                            } else {
                                Toast.makeText(SigninActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

});
      //Sig in with Google
        CreateRequest ();
        btn_signinwithgoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   signIn();
            }
        });


        // Initialize Facebook Login button
        FacebookSdk.sdkInitialize(getApplicationContext());
  //      AppEventsLogger.activateApp(this);
         mCallbackManager = CallbackManager.Factory.create();
        btn_signinwithfacebook=findViewById(R.id.btn_signinwithfacebook);
        btn_signinwithfacebook.setBackgroundResource(R.drawable.gray_roundbtn);
        Drawable img =this.getResources().getDrawable(R.drawable.f_logo);
        img.setBounds(0, 0, 60, 60);
        btn_signinwithfacebook.setCompoundDrawables(img, null, null, null);
         btn_signinwithfacebook.setText("Facebook");
         btn_signinwithfacebook.setLoginText("FaceBook");
          btn_signinwithfacebook.setTextColor(this.getResources().getColor(R.color.grey));
          btn_signinwithfacebook.setTextSize(16);
        btn_signinwithfacebook.setPermissions(Arrays.asList(EMAIL));
        btn_signinwithfacebook.setReadPermissions("public_profile", "email");
        btn_signinwithfacebook.setPermissions("public_profile", "email");

        btn_signinwithfacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
             }
        });
    }
    //Sign in with Google
    private void CreateRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }
    //Sig in with Google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                //Log.w(TAG, "Google sign in failed", e);
                // ...
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
// Sign in with Google
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                     //       Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                                 Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
                                startActivity(intent);
                                finish();
                            }
                 //           updateUI(user);
                        else {
                        Toast.makeText(SigninActivity.this ,"Sorry  Auth Failed",Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user.
                        //    Log.w(TAG, "signInWithCredential:failure", task.getException());
                  //          Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                          //  updateUI(null);
                        }

                        // ...
                    }
                });
    }

// Sign in with Facebook
private void handleFacebookAccessToken(AccessToken token) {
    Log.d(TAG, "handleFacebookAccessToken:" + token);

    AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

    mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, UI will update with the signed-in user's information
                       Log.d(TAG, "signInWithCredential:success");
//                        FirebaseUser user = mAuth.getCurrentUser();
                   //     Toast.makeText(SigninActivity.this, "Sign in  Succeeded.", Toast.LENGTH_SHORT).show();
                            openDrawer();
                    } else {
                        // If sign-in fails, a message will display to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(SigninActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                    }
                }
            });
}


private  void openDrawer()
{
    Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
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