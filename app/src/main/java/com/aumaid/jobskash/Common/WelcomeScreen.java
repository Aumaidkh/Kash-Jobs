package com.aumaid.jobskash.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.aumaid.jobskash.Common.LogInSignUp.SignInPage;
import com.aumaid.jobskash.Common.LogInSignUp.SignUpFirstPage;
import com.aumaid.jobskash.R;

public class WelcomeScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }



    public boolean signInPage(View view) {
        Intent mSignUpIntent = new Intent(getApplicationContext(), SignInPage.class);

        /*Making Transition Animation*/
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.welcome_log_in_btn),"transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(WelcomeScreen.this,pairs);
        startActivity(mSignUpIntent,options.toBundle());
        return true;
    }

    public boolean signUpPage(View view){

        Intent mSignUpIntent = new Intent(getApplicationContext(), SignUpFirstPage.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.welcome_sign_up_btn),"transition_signup");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(WelcomeScreen.this,pairs);
        startActivity(mSignUpIntent,options.toBundle());
        return true;
    }
}