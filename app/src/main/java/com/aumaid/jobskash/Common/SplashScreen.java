package com.aumaid.jobskash.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.aumaid.jobskash.R;

public class SplashScreen extends AppCompatActivity {

    final int duration = 5000;

    ImageView mSplashScreenLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        mSplashScreenLogo = findViewById(R.id.splash_screen_logo);

        animateSplashScreen();

        /*If check Here*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mHomeIntent;

                mHomeIntent = new Intent(getApplicationContext(), WelcomeScreen.class);

                startActivity(mHomeIntent);
                finish();
            }
        },duration);


    }

    public void animateSplashScreen(){
        mSplashScreenLogo.setAlpha(0.0F);

        mSplashScreenLogo.animate().alphaBy(1.0F).setDuration(duration);
    }
}