package com.aumaid.jobskash.Common.LogInSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aumaid.jobskash.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SignUpFirstPage extends AppCompatActivity {

    TextInputEditText mFullName, mUserName, mPasswordBox, mEmail;
    TextView mTitleText;
    ImageView mBackBtn;
    Button mNextBtn, mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_first_page);

        mFullName = findViewById(R.id.full_name_box);
        mUserName = findViewById(R.id.username_box);
        mEmail = findViewById(R.id.sign_up_email_box);
        mPasswordBox = findViewById(R.id.sign_up_password_box);
        mBackBtn = findViewById(R.id.sign_up_back_btn);
        mNextBtn = findViewById(R.id.sign_up_next_btn);
        mLoginBtn = findViewById(R.id.log_in_btn_sign_up);
        mTitleText = findViewById(R.id.create_account_title);
    }


    /*Validation Functions*/
    private boolean validateFullName() {
        String value = Objects.requireNonNull(mFullName.getText()).toString().toLowerCase().trim();
        if (value.isEmpty()) {
            mFullName.setError("Field can't be empty");
            return false;
        } else {
            mFullName.setError(null);
            Log.i("Kash", "FUll name validated");
            return true;
        }

    }

    private boolean validateUserName() {
        String value = mUserName.getText().toString().trim();
        String checkSpaces = "\\A\\w{1,20}\\z";

        if (value.isEmpty()) {
            mUserName.setError("Field can't be empty");
            return false;
        } else if (value.length() > 20) {
            mUserName.setError("Username too big");
            return false;
        } else if (!value.matches(checkSpaces)) {
            mUserName.setError("White spaces not allowed");
            return false;
        } else {
            mUserName.setError(null);
            Log.i("Kash", "User name validated");
            return true;
        }
    }

    private boolean validateEmail() {
        String value = mEmail.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+";

        if (value.isEmpty()) {
            mEmail.setError("Field can't be empty");
            return false;
        } else if (!value.matches(checkEmail)) {
            mEmail.setError("Invalid Email");
            return false;
        } else {
            mEmail.setError(null);
            Log.i("Kash", "Email validated");
            return true;
        }
    }

    private boolean validatePassword() {
        String value = mPasswordBox.getText().toString().trim();
        String checkPassword = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{6,20}";
        //(?=.*[@#$%^&-+=()]) special character must occur at least once

        if (value.isEmpty()) {
            mPasswordBox.setError("Field can't be empty");
            return false;
        } else {
            mPasswordBox.setError(null);
            Log.i("Kash", "Password validated");
            return true;
        }
    }

    /*Calling next Screen*/

    public void callNextSignUpScreen(View view) {

        //Validate First
        if (validateFullName() && validateEmail() && validateUserName() && validatePassword()) {

            Log.i("Kash", "We are in");

            Intent intent = new Intent(getApplicationContext(), SignUpSecondPage.class);


            //Passing data to other activity
            intent.putExtra("FULL_NAME", mFullName.getText().toString().toLowerCase().trim());
            intent.putExtra("USER_NAME", mUserName.getText().toString().trim());
            intent.putExtra("EMAIL", mEmail.getText().toString().toLowerCase().trim());
            intent.putExtra("PASSWORD", mPasswordBox.getText().toString().trim());

            //Adding Transitions
            Pair[] pairs = new Pair[4];
            pairs[0] = new Pair<View, String>(mBackBtn, "transition_back_arrow_btn");
            pairs[1] = new Pair<View, String>(mNextBtn, "transition_next");
            pairs[2] = new Pair<View, String>(mLoginBtn, "transition_loginbtn");
            pairs[3] = new Pair<View, String>(mTitleText, "transition_title_text");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpFirstPage.this, pairs);
            startActivity(intent, options.toBundle());

        }


    }

    //TODO: Incomplete Function
    public void back(View view) {

    }
}