package com.aumaid.jobskash.Common.LogInSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aumaid.jobskash.R;

import java.util.Calendar;

public class SignUpSecondPage extends AppCompatActivity {

    TextView mTitleText;
    ImageView mBackBtn;
    Button mNextBtn, mLoginBtn;
    RadioGroup mRadioGroup;
    RadioButton mGenderPicker;
    DatePicker mAgePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_second_page);
        mBackBtn = findViewById(R.id.sign_up_back_btn);
        mNextBtn = findViewById(R.id.sign_up_next_btn);
        mLoginBtn = findViewById(R.id.log_in_btn_sign_up);
        mTitleText = findViewById(R.id.create_account_title);
        mAgePicker = findViewById(R.id.age);
        mRadioGroup = findViewById(R.id.radio_group);


    }


    public void callNextSignUpScreen(View view) {

        //Validate First
        if (!validateAge() || !validateGender()) {
            return;
        }
        Log.d("Kash", "we are here");

        //Retreiving data from last intent
        String fullName = getIntent().getStringExtra("FULL_NAME");
        String userName = getIntent().getStringExtra("USER_NAME");
        String email = getIntent().getStringExtra("EMAIL");
        String password = getIntent().getStringExtra("PASSWORD");

        mGenderPicker = findViewById(mRadioGroup.getCheckedRadioButtonId());
        String gender = mGenderPicker.getText().toString();
        int day = mAgePicker.getDayOfMonth();
        int month = mAgePicker.getMonth();
        int year = mAgePicker.getYear();

        String dateOfBirth = day + "/" + month + "/" + year;

        Intent intent = new Intent(getApplicationContext(), SignUpThirdPage.class);

        intent.putExtra("FULL_NAME", fullName);
        intent.putExtra("USER_NAME", userName);
        intent.putExtra("EMAIL", email);
        intent.putExtra("PASSWORD", password);
        intent.putExtra("GENDER", gender);
        intent.putExtra("DATE_OF_BIRTH", dateOfBirth);

        //Adding Transitions
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(mBackBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(mNextBtn, "transition_next");
        pairs[2] = new Pair<View, String>(mLoginBtn, "transition_loginbtn");
        pairs[3] = new Pair<View, String>(mTitleText, "transition_title_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpSecondPage.this, pairs);
        startActivity(intent, options.toBundle());

    }

    //Validate FUnctions
    private boolean validateGender() {
        Log.i("Kash", "We are in");
        if (mRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Log.i("Kash", "Gender Validated");
            return true;
        }
    }

    private boolean validateAge() {
        Log.i("Kash", "We are in");
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = mAgePicker.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 10) {
            Toast.makeText(getApplicationContext(), "You are not eligible", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Log.i("Kash", "Gender Validated");
            return true;
        }
    }
}