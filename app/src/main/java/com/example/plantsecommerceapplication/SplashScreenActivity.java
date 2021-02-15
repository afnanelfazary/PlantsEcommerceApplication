package com.example.plantsecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
     TextView AppName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
     //   getSupportActionBar().hide();
        AppName=findViewById(R.id.TV_appName);
        Typeface fontType = Typeface.createFromAsset(getAssets(), "fonts/BunchBlossomsPersonalUse-0nA4.ttf");
        AppName.setTypeface(fontType);

        //thread for progress bar

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                Intent goToIntroScreenActivity=new Intent(getApplicationContext(),IntroActivity.class);
                startActivity(goToIntroScreenActivity);
                finish();
            }
        }).start();

    }
}