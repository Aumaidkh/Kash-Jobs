package com.aumaid.jobskash.Common.LogInSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.aumaid.jobskash.Common.VerifyOtp;
import com.aumaid.jobskash.R;
import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;

import java.util.Objects;

public class SignUpThirdPage extends AppCompatActivity {

    static final String TAG = "MAIN_TAG";

    ImageView mBackBtn;
    Button mNextBtn, mSignInBtn;
    TextInputEditText mPhoneNumberBox;
    CountryCodePicker mCountryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_third_page);

        mBackBtn = findViewById(R.id.sign_up_back_btn);
        mNextBtn = findViewById(R.id.sign_up_next_btn);
        mSignInBtn = findViewById(R.id.log_in_btn_sign_up);
        mPhoneNumberBox = findViewById(R.id.sign_up_phone_box);
        mCountryCodePicker = findViewById(R.id.countryCodeHolder);


    }

    public void callVerifyOtpScreen(View view) {


        //Validating Phone Number
        if (!validatePhoneNumber()) {

            //Toast.makeText(getApplicationContext(),"Invalid Phone Number",Toast.LENGTH_SHORT).show();
            return;
        }



        //Retreiving data from previous Intent
        String _fullName = getIntent().getStringExtra("FULL_NAME");
        String _userName = getIntent().getStringExtra("USER_NAME");
        String _email = getIntent().getStringExtra("EMAIL");
        String _password = getIntent().getStringExtra("PASSWORD");
        String _gender = getIntent().getStringExtra("GENDER");
        String _dateOfBirth = getIntent().getStringExtra("DATE_OF_BIRTH");




        String _userEnteredPhoneNumber = mPhoneNumberBox.getText().toString().trim();
        String _fullPhoneNumber = "+" + mCountryCodePicker.getFullNumber() + _userEnteredPhoneNumber;




        Intent intent = new Intent(getApplicationContext(), VerifyOtp.class);


        //Passing data to the next activity
        intent.putExtra("FULL_NAME", _fullName);
        intent.putExtra("USER_NAME", _userName);
        intent.putExtra("EMAIL", _email);
        intent.putExtra("PASSWORD", _password);
        intent.putExtra("GENDER", _gender);
        intent.putExtra("DATE_OF_BIRTH", _dateOfBirth);
        intent.putExtra("PHONE_NUMBER", _fullPhoneNumber);



        //Making transitions
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(mNextBtn, "transition_next");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpThirdPage.this,pairs);

        startActivity(intent, options.toBundle());

    }

    //validate phone number
    private boolean validatePhoneNumber() {
        String mPhoneNumberCheck = "\\d{10}";
        if (Objects.requireNonNull(mPhoneNumberBox.getText()).toString().isEmpty()) {
            mPhoneNumberBox.setError("Field can't be empty");
            return false;
        } else if (mPhoneNumberBox.getText().toString().matches(mPhoneNumberCheck)) {
            return true;
        } else if (mPhoneNumberBox.getText().toString().matches("\\d{4}[-\\.\\s]\\d{3}[-\\.\\s]\\d{3}")) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}