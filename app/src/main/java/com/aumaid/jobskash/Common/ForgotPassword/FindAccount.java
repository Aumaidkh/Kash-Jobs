package com.aumaid.jobskash.Common.ForgotPassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aumaid.jobskash.Common.VerifyOtp;
import com.aumaid.jobskash.HelperClasses.ConnectionChecker;
import com.aumaid.jobskash.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FindAccount extends AppCompatActivity {

    /*Declaring Views*/
    RelativeLayout mProgressBar;
    TextInputEditText mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_account);

        mProgressBar = findViewById(R.id.progress_bar);
        mUserName = findViewById(R.id.forget_password_username_box);

    }

    public void findAccount(View view){

        /*Internet Connectivity Check*/
        ConnectionChecker connectionChecker = new ConnectionChecker();
        if(!connectionChecker.isConnected(this)){
            Toast.makeText(getApplicationContext(),"No internet connection", Toast.LENGTH_SHORT).show();
            return;
        }

        /*Showing progress bar*/
        mProgressBar.setVisibility(View.VISIBLE);

        /*Getting data from the input*/
        String _username = mUserName.getText().toString().trim();

        /*Checking if user exists*/

        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("userName").equalTo(_username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    String _phoneNumber = snapshot.child(_username).child("phoneNumber").getValue(String.class);
                    Intent intent = new Intent(getApplicationContext(), VerifyOtp.class);
                    intent.putExtra("PHONE_NUMBER", _phoneNumber);
                    intent.putExtra("OPTION","FORGET_PASSWORD");
                    mProgressBar.setVisibility(View.GONE);
                    startActivity(intent);
                    finish();

                }else{
                    mProgressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Invalid Username",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}