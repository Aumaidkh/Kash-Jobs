package com.aumaid.jobskash.User.PostJobs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.aumaid.jobskash.HelperClasses.InternetChecker;
import com.aumaid.jobskash.HelperClasses.Validator;
import com.aumaid.jobskash.R;
import com.google.android.material.textfield.TextInputEditText;

import static com.aumaid.jobskash.HelperClasses.AVariables.ABOUT_EMPLOYER;
import static com.aumaid.jobskash.HelperClasses.AVariables.CONTACT;
import static com.aumaid.jobskash.HelperClasses.AVariables.CYCLE_SELECTOR;
import static com.aumaid.jobskash.HelperClasses.AVariables.EMPLOYERS_ADDRESS;
import static com.aumaid.jobskash.HelperClasses.AVariables.EMPLOYERS_NAME;
import static com.aumaid.jobskash.HelperClasses.AVariables.EXPERIENCE;
import static com.aumaid.jobskash.HelperClasses.AVariables.HIRES_REQUIRED;
import static com.aumaid.jobskash.HelperClasses.AVariables.INDUSTRY_TYPE;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_DESCRIPTION;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_TITLE;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_TYPE;
import static com.aumaid.jobskash.HelperClasses.AVariables.QUALIFICATIONS;
import static com.aumaid.jobskash.HelperClasses.AVariables.RANGE_SELECTOR;
import static com.aumaid.jobskash.HelperClasses.AVariables.SALARY;
import static com.aumaid.jobskash.HelperClasses.AVariables.SKILL;
import static com.aumaid.jobskash.HelperClasses.ErrorMessages.NO_INTERNET_CONNECTION;

public class Qualifications extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /*Declaring Views*/
    TextInputEditText mExperienceInYears;

    /*Declaring Views*/
    Spinner mEducationSpinner;

    /*Declaring Variables*/
    String[] qualifications = {"Secondary(10th Pass)", "Higher Secondary(12th Pass", "Diploma", "Bachelors","Masters"};
    String _Qualification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualifications);
        mExperienceInYears = findViewById(R.id.pj_experience_text_box);

        /*Configuring Spinner*/
        mEducationSpinner = findViewById(R.id.pj_education_spinner);
        mEducationSpinner.setOnItemSelectedListener(this);

        ArrayAdapter mQualifications = new ArrayAdapter(this,android.R.layout.simple_spinner_item,qualifications);
        mQualifications.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mEducationSpinner.setAdapter(mQualifications);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _Qualification = qualifications[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void nextPage(View view){
        /*Checking Internet*/
        InternetChecker internetChecker = new InternetChecker();
        if(!internetChecker.isConnected(this)){

            Toast.makeText(this, NO_INTERNET_CONNECTION, Toast.LENGTH_SHORT).show();
            return;
        }

        /*Validating fields*/
        Validator validator = new Validator();
        if(!(validator.validateTextField(mExperienceInYears))){
            return;
        }

        String _Experience = mExperienceInYears.getText().toString().trim();


        /*Passing data to next activity*/
        Intent intent = new Intent(getApplicationContext(), PreviewJob.class);
        /*Passing Data to other activity*/
        intent.putExtra(EMPLOYERS_NAME,getIntent().getStringExtra(EMPLOYERS_NAME));
        intent.putExtra(EMPLOYERS_ADDRESS,getIntent().getStringExtra(EMPLOYERS_ADDRESS));
        intent.putExtra(CONTACT,getIntent().getStringExtra(CONTACT));
        intent.putExtra(INDUSTRY_TYPE,getIntent().getStringExtra(INDUSTRY_TYPE));
        intent.putExtra(ABOUT_EMPLOYER,getIntent().getStringExtra(ABOUT_EMPLOYER));
        intent.putExtra(JOB_TYPE,getIntent().getStringExtra(JOB_TYPE));
        intent.putExtra(JOB_TITLE,getIntent().getStringExtra(JOB_TITLE));
        intent.putExtra(HIRES_REQUIRED,getIntent().getStringExtra(HIRES_REQUIRED));
        intent.putExtra(RANGE_SELECTOR,getIntent().getStringExtra(RANGE_SELECTOR));
        intent.putExtra(SALARY,getIntent().getStringExtra(SALARY));
        intent.putExtra(CYCLE_SELECTOR,getIntent().getStringExtra(CYCLE_SELECTOR));
        intent.putExtra(JOB_DESCRIPTION,getIntent().getStringExtra(JOB_DESCRIPTION));
        intent.putExtra(SKILL,getIntent().getStringExtra(SKILL));
        intent.putExtra(EXPERIENCE,_Experience+"");
        intent.putExtra(QUALIFICATIONS,_Qualification);



        Pair[] pair = new Pair[1];
        pair[0] = new Pair<View, String>(view, "save_qualifications_transition");
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Qualifications.this,pair).toBundle());
    }
}