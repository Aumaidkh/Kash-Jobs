package com.aumaid.jobskash.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aumaid.jobskash.R;
import com.aumaid.jobskash.User.HomePage;

public class SuccessScreen extends AppCompatActivity {

    /*Declaring Text Views*/
    TextView mSuccessHeading, mCongratsHeading;
    Button mSignButton;

    /*Declaring mode*/
    int _mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_screen);

        _mode = getIntent().getIntExtra("Mode",0);

        mSuccessHeading = findViewById(R.id.success_sign_in_heading);
        mCongratsHeading = findViewById(R.id.success_congrats_tv);
        mSignButton = findViewById(R.id.success_screen_log_in_btn);


        switch(_mode){
            case 0:
                break;
            case 1:
                jobPostBehaviour();
                break;
        }

    }

    private void jobPostBehaviour(){
        mSuccessHeading.setText("Congrats");
        mCongratsHeading.setText("Job has been posted.");
        mSignButton.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        },3000);
    }
}