package com.aumaid.jobskash.Common.LogInSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aumaid.jobskash.Database.SessionManager;
import com.aumaid.jobskash.HelperClasses.InternetChecker;
import com.aumaid.jobskash.R;
import com.aumaid.jobskash.User.HomePage;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.SignatureSpi;
import java.util.HashMap;

public class SignInPage extends AppCompatActivity {

    /*Declaring Views*/
    TextInputEditText mUsername, mPassword;
    ProgressBar mProgressBar;
    CheckBox mRememberMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        /*Creating Hooks*/
        mUsername = findViewById(R.id.sign_in_email_box);
        mPassword = findViewById(R.id.sign_in_password_box);
        mProgressBar = findViewById(R.id.progress_bar);
        mRememberMe = findViewById(R.id.remember_me);

        /*Checking Sessions*/
        checkRememberMeSession();
        checkLogInSession();

    }


    public void checkRememberMeSession() {
        //TODO: Unimplemented
        SessionManager sessionManager = new SessionManager(SignInPage.this, SessionManager.REMEMBER_ME_SESSION);
        if (sessionManager.checkIsRememberMe()) {
            HashMap<String, String> rememberMeDetails = sessionManager.getUserDataFromRememberMeSession();
            mUsername.setText(rememberMeDetails.get(SessionManager.REMEMBER_ME_SESSION_KEY_USER_NAME));
            mPassword.setText(rememberMeDetails.get(SessionManager.REMEMBER_ME_SESSION_KEY_PASSWORD));
            //  Log.d("MAIN",rememberMeDetails.get(SessionManager.REMEMBER_ME_SESSION_KEY_USER_NAME));
            //  Log.d("MAIN",rememberMeDetails.get(SessionManager.REMEMBER_ME_SESSION_KEY_PASSWORD));
        }
    }

    public void checkLogInSession() {
        SessionManager sessionManager = new SessionManager(SignInPage.this, SessionManager.USER_LOGIN_SESSION);
        if (sessionManager.checkLogIn()) {
            presetHomeScreen();
        }
    }

    //TODO: Un Implemented function
    public void signIn(View view) {
        /*Internet Connectivity Check*/
        InternetChecker internetChecker = new InternetChecker();
        if (!internetChecker.isConnected(this)) {
            Toast.makeText(getApplicationContext(), "No internet connection detected", Toast.LENGTH_SHORT).show();
            return;
        }
        checkUser();


    }

    public void checkUser() {
        /*Getting username and password*/
        String _Username = mUsername.getText().toString();
        String _Password = mPassword.getText().toString();

        mProgressBar.setVisibility(View.VISIBLE);

        if (mRememberMe.isChecked()) {
            //Toast.makeText(getApplicationContext(),mRememberMe.isChecked()+"",Toast.LENGTH_SHORT).show();
            SessionManager sessionManager = new SessionManager(SignInPage.this, SessionManager.REMEMBER_ME_SESSION);
            sessionManager.createRememberMeSession(_Username, _Password);
        }

        /*Checking database for the username entered*/
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("userName").equalTo(_Username);


        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    /*Checking database for password for the entered username*/
                    String realPassword = snapshot.child(_Username).child("password").getValue(String.class);
                    if (realPassword.equals(_Password)) {
                        /*Log in the user*/

                        mProgressBar.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(), "Logged in as " + _Username, Toast.LENGTH_SHORT).show();


                        /*Fetch user data*/
                        String _FullName = snapshot.child("fullName").getValue(String.class);
                        String _Username = snapshot.child("userName").getValue(String.class);
                        String _Email = snapshot.child("email").getValue(String.class);
                        String _Password = snapshot.child("password").getValue(String.class);
                        String _Gender = snapshot.child("gender").getValue(String.class);
                        String _DateOfBirth = snapshot.child("date").getValue(String.class);
                        String _PhoneNumber = snapshot.child("phoneNumber").getValue(String.class);

                        /*Creating session*/
                        SessionManager sessionManager = new SessionManager(SignInPage.this, SessionManager.USER_LOGIN_SESSION);
                        sessionManager.createLogInSession(_FullName, _Username, _Email, _Password, _Gender, _DateOfBirth, _PhoneNumber);
                        /*Present home activity*/
                        presetHomeScreen();

                    } else {
                        /*Invalid password*/
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    //Invalid username
                    mProgressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Invalid Username", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean signUpPage(View view) {

        Intent mSignUpIntent = new Intent(getApplicationContext(), SignUpFirstPage.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.welcome_sign_up_btn), "transition_signup");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignInPage.this, pairs);
        startActivity(mSignUpIntent, options.toBundle());
        return true;
    }


    private void presetHomeScreen() {
        startActivity(new Intent(getApplicationContext(), HomePage.class));
        finish();
    }
}