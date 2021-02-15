package com.example.plantsecommerceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class SignUpActivity extends AppCompatActivity {
 TextView Tv_signin;
 Button Btn_SignUp,btn_signinwithgoogle;
 LoginButton btn_signinwithfacebook;
 EditText et_FirstName,et_LastName,email,et_MobileNum,etpassword,ConfirmPassword;
 String FirstName,LastName, emailAddress, MobileNum, Password, confirmPassword;
    AwesomeValidation awesomeValidation;

    private FirebaseAuth mAuth;
     private DatabaseReference databaseReference ;
    private FirebaseUser user;


    private FirebaseUser currentUser;
    //Sign in with Google
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN  =12345;
    //Sigin in with facebook
    //  private final static int RC_SIGN_IN  =12345;
    private static final String TAG = "FacebookAuthentication";
    private CallbackManager mCallbackManager;
    private  static  final String EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
     //   getSupportActionBar().hide();
        Btn_SignUp=findViewById(R.id.btn_signup);
        btn_signinwithfacebook = (LoginButton) findViewById(R.id.btn_signinwithfacebook) ;
         Tv_signin= findViewById(R.id.tv_SignIn);
         btn_signinwithgoogle=findViewById(R.id.btn_signinwithgoogle);
         et_FirstName=findViewById(R.id.et_FirstName);
         et_LastName=findViewById(R.id.et_LastName);
         email=findViewById(R.id.email);
         et_MobileNum=findViewById(R.id.et_MobileNum);
         etpassword=findViewById(R.id.password);
         ConfirmPassword=findViewById(R.id.ConfirmPassword);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.valid_email_address);
        awesomeValidation.addValidation(this, R.id.et_MobileNum, "^[+]?[0-9]{10,13}$", R.string.valid_phone_number);
        awesomeValidation.addValidation(this, R.id.password, "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})", R.string.valid_password);
        awesomeValidation.addValidation(this, R.id.ConfirmPassword, R.id.password, R.string.valid_confirm_password);
        awesomeValidation.addValidation(this, R.id.et_FirstName, RegexTemplate.NOT_EMPTY, R.string.valid_first_name);
        awesomeValidation.addValidation(this, R.id.et_LastName, RegexTemplate.NOT_EMPTY, R.string.valid_last_name);



        //FireBase
        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        //underline Text
        Tv_signin.setPaintFlags(Tv_signin.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        //SignUp Button


         Btn_SignUp.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 FirstName = et_FirstName.getText().toString().trim();
                 LastName = et_LastName.getText().toString().trim();
                 emailAddress = email.getText().toString().trim();
                 MobileNum = et_MobileNum.getText().toString().trim();
                 Password = etpassword.getText().toString().trim();
                 confirmPassword = ConfirmPassword.getText().toString().trim();
                 if (awesomeValidation.validate()) {
                     mAuth.createUserWithEmailAndPassword(emailAddress, Password)
                             .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                     if (task.isSuccessful()) {
                                         user = task.getResult().getUser();
                                         UserProfileChangeRequest ProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                                 .setDisplayName(et_FirstName.getText().toString())
                                                 .build();
                                         user.updateProfile(ProfileChangeRequest);
                                         DatabaseReference newUser = databaseReference.child(user.getUid());
                                         newUser.child("First Name").setValue(FirstName);
                                        newUser.child("Last Name").setValue(LastName);
                                         newUser.child("Email Address").setValue(emailAddress);
                                         newUser.child("Phone Number").setValue(MobileNum);
                                         newUser.child("Password").setValue(Password);

                                         Toast.makeText(SignUpActivity.this, "Registration Successfully", Toast.LENGTH_LONG).show();
                                         Intent goToLogin = new Intent(SignUpActivity.this, SigninActivity.class);
                                         startActivity(goToLogin);
                                         finish();

                                     } else {
                                         // If sign in fails, display a message to the user.
                                         Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                     }

                                  }
                             });


                 }


             }
         });
    Tv_signin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent goToSignInActivity=new Intent(getApplicationContext(),SigninActivity.class);
            startActivity(goToSignInActivity);
            finish();
        }
    });


    //Sign in with google

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
                            Toast.makeText(SignUpActivity.this ,"Sorry  Auth Failed",Toast.LENGTH_SHORT).show();
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
                            Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign-in fails, a message will display to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



}
