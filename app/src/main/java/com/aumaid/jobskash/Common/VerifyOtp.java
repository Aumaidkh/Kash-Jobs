package com.aumaid.jobskash.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aumaid.jobskash.Models.UserModel;
import com.aumaid.jobskash.HelperClasses.InternetChecker;
import com.aumaid.jobskash.R;
import com.aumaid.jobskash.User.HomePage;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifyOtp extends AppCompatActivity {

    /*Tag*/
    final String TAG = "MAIN";

    /*Declaring Views*/
    PinView pinView;
    Button mVerifyOtp;
    TextView mPhoneNumber;

    /*Firebase stuff*/
    private FirebaseAuth mAuth;

    private String mVerificationId;

    /*Declaring database Variables*/
    String _fullname;
    String _username;
    String _email;
    String _password;
    String _gender;
    String _dateOfBirth;
    String _phoneNumber;

    private PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        prepareOtpScreen();
        sendVerificationCodeTo(_phoneNumber);


    }

    /*Preparing Otp Screen*/
    private void prepareOtpScreen() {
        //Retreiving data from previous Intent
        _fullname = getIntent().getStringExtra("FULL_NAME");
        _username = getIntent().getStringExtra("USER_NAME");
        _email = getIntent().getStringExtra("EMAIL");
        _password = getIntent().getStringExtra("PASSWORD");
        _gender = getIntent().getStringExtra("GENDER");
        _dateOfBirth = getIntent().getStringExtra("DATE_OF_BIRTH");
        _phoneNumber = getIntent().getStringExtra("PHONE_NUMBER");

        pinView = findViewById(R.id.pin_view);
        mVerifyOtp = findViewById(R.id.verify_otp_btn);
        mPhoneNumber = findViewById(R.id.vo_phone_number_tv);
        mPhoneNumber.setText(_phoneNumber);

        mAuth = FirebaseAuth.getInstance();

        //TODO: THis is the Code
        // Force reCAPTCHA flow
        FirebaseAuth.getInstance().getFirebaseAuthSettings().forceRecaptchaFlowForTesting(false);
        FirebaseAuth.getInstance().getFirebaseAuthSettings()
                .setAppVerificationDisabledForTesting(false);
    }

    /*Dealing with Phone Authentication*/
    private void sendVerificationCodeTo(String phoneNumber) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential credential) {
                                // This callback will be invoked in two situations:
                                // 1 - Instant verification. In some cases the phone number can be instantly
                                //     verified without needing to send or enter a verification code.
                                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                                //     detect the incoming verification SMS and perform verification without
                                //     user action.
                                Log.d(TAG, "onVerificationCompleted:" + credential);

                                //TODO: Needs modification
                                pinView.setText(mVerificationId);
                                signInWithPhoneAuthCredential(credential);
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                // This callback is invoked in an invalid request for verification is made,
                                // for instance if the the phone number format is not valid.
                                Log.w(TAG, "onVerificationFailed", e);

                                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                    // Invalid request
                                } else if (e instanceof FirebaseTooManyRequestsException) {
                                    // The SMS quota for the project has been exceeded
                                }

                                // Show a message and update the UI
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                                // The SMS verification code has been sent to the provided phone number, we
                                // now need to ask the user to enter the code and then construct a credential
                                // by combining the code with a verification ID.
                                Log.d(TAG, "onCodeSent:" + verificationId);

                                // Save verification ID and resending token so we can use them later
                                mVerificationId = verificationId;
                                mResendToken = token;
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            Toast.makeText(getApplicationContext(), "Logged in as " + user.getPhoneNumber(), Toast.LENGTH_SHORT).show();

                            saveData();
                            startActivity(new Intent(getApplicationContext(), HomePage.class));
                            finish();

                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);

    }

    /*Calling Next Screen When done*/
    public void callNextScreenFromOtp(View view) {
        /*Internet Connectivity Check*/
        InternetChecker internetChecker = new InternetChecker();
        if (!internetChecker.isConnected(this)) {
            Toast.makeText(getApplicationContext(), "No internet connection detected", Toast.LENGTH_SHORT).show();
            return;
        }
        String _otp = pinView.getText().toString();
        verifyCode(_otp);
    }

    public void saveData() {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Users");

        UserModel user = new UserModel(_fullname, _username, _email, _password, _gender, _dateOfBirth, _phoneNumber);
        reference.child(_username).setValue(user);
    }
}