package com.aumaid.jobskash.User.PostJobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aumaid.jobskash.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class PostJobsFirstPage extends AppCompatActivity {

    private TextInputEditText mCompanyName, mCompanyAddress, mContact;

    String _companyAddress, _companyName, _contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_jobs_first_page);

        mCompanyName = findViewById(R.id.employers_name_box);
        mCompanyAddress = findViewById(R.id.employers_address_box);
        mContact = findViewById(R.id.pj_email_phone_box);

    }

    public void nextPostJobScreen(View view){

        if(!(validateCompanyName()||validateCompanyAddress()||validateContact())){
            return;
        }

        _companyName = mCompanyName.getText().toString().trim();
        _companyAddress = mCompanyAddress.getText().toString().trim();
        _contact = mContact.getText().toString().trim();
        Intent intent = new Intent(getApplicationContext(), PostJobsSecondScreen.class);

        /*Passing Data*/
        intent.putExtra("COMPANY_NAME",_companyName);
        intent.putExtra("COMPANY_ADDRESS",_companyAddress);
        intent.putExtra("CONTACT",_contact);

        startActivity(intent);

        /*Animating Screen*/
        this.overridePendingTransition(R.anim.slide_right_animation,
                R.anim.slide_left_animation);
    }

    /*Validating functions*/
    public boolean validateCompanyAddress(){
        String value = Objects.requireNonNull(mCompanyAddress.getText()).toString().toLowerCase().trim();
        if(value.isEmpty()){
            mCompanyAddress.setError("Field can't be empty");
            return false;
        }
        _companyAddress = mCompanyAddress.getText().toString();
        return true;
    }

    public boolean validateCompanyName(){
        String value = Objects.requireNonNull(mCompanyName.getText()).toString().toLowerCase().trim();
        if (value.isEmpty()) {
            mCompanyName.setError("Field can't be empty");
            return false;
        } else {
            mCompanyName.setError(null);
            return true;
        }
    }

    public boolean validateContact(){
        String value = Objects.requireNonNull(mContact.getText()).toString().toLowerCase().trim();
        if (value.isEmpty()) {
            mContact.setError("Field can't be empty");
            return false;
        } else {
            mContact.setError(null);
            return true;
        }
    }
}