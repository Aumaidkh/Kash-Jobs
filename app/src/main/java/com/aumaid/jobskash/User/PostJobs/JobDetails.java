package com.aumaid.jobskash.User.PostJobs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.aumaid.jobskash.HelperClasses.InternetChecker;
import com.aumaid.jobskash.HelperClasses.Validator;
import com.aumaid.jobskash.R;
import com.google.android.material.textfield.TextInputEditText;

import static com.aumaid.jobskash.HelperClasses.AVariables.ABOUT_EMPLOYER;
import static com.aumaid.jobskash.HelperClasses.AVariables.CONTACT;
import static com.aumaid.jobskash.HelperClasses.AVariables.EMPLOYERS_ADDRESS;
import static com.aumaid.jobskash.HelperClasses.AVariables.EMPLOYERS_NAME;
import static com.aumaid.jobskash.HelperClasses.AVariables.HIRES_REQUIRED;
import static com.aumaid.jobskash.HelperClasses.AVariables.INDUSTRY_TYPE;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_TITLE;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_TYPE;

public class JobDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /*Declaring Views*/
    Spinner spinner;
    TextInputEditText mJobTitle;
    CheckBox mFullTime, mPartTime,mTemporary,mContract,mInternship;

    /*Declaring other variables*/
    String options[] = {"1 Hire", "2-4 Hires","5-10 Hires","More than 10"};
    String jobType = "";
    String numberOfHirees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        /*Binding View*/
        mJobTitle = findViewById(R.id.pj_job_title_box);
        mPartTime = findViewById(R.id.full_time_check_box);
        mTemporary = findViewById(R.id.part_time_check_box);
        mContract = findViewById(R.id.contract_check_box);
        mInternship = findViewById(R.id.internship_check_box);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spinner =  findViewById(R.id.number_of_hirees_spinner);
        spinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter industryType = new ArrayAdapter(this,android.R.layout.simple_spinner_item,options);
        industryType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(industryType);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        numberOfHirees = options[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void nextPage(View view){

        /*Internet Check Here*/
        InternetChecker internetChecker = new InternetChecker();
        if(!internetChecker.isConnected(this)){
            Toast.makeText(this, "No internet connection detected", Toast.LENGTH_SHORT).show();
            return;
        }

        /*Validating fields*/
        Validator validator = new Validator();
        if(!(validator.validateTextField(mJobTitle))){
            return;
        }

        String _JobTitle = mJobTitle.getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), SalaryDetails.class);
        /*Passing Data to other activity*/
        intent.putExtra(EMPLOYERS_NAME,getIntent().getStringExtra(EMPLOYERS_NAME));
        intent.putExtra(EMPLOYERS_ADDRESS,getIntent().getStringExtra(EMPLOYERS_ADDRESS));
        intent.putExtra(CONTACT,getIntent().getStringExtra(CONTACT));
        intent.putExtra(INDUSTRY_TYPE,getIntent().getStringExtra(INDUSTRY_TYPE));
        intent.putExtra(ABOUT_EMPLOYER,getIntent().getStringExtra(ABOUT_EMPLOYER));
        intent.putExtra(JOB_TYPE,removeDuplicates(jobType));
        intent.putExtra(JOB_TITLE,_JobTitle);
        intent.putExtra(HIRES_REQUIRED,numberOfHirees);



        Pair[] pair = new Pair[1];
        pair[0] = new Pair<View, String>(view, "save_salary_details_transition");
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(JobDetails.this,pair).toBundle());
        startActivity(intent);

        /*Animate Here*/
     //   Animator animator = new Animator();
    //    animator.animate();
    }

    public void onCheckBoxClicked(View view){

        CheckBox checkBox = (CheckBox) view;

        if (checkBox.isChecked()){
            jobType+=checkBox.getText()+",";
            Toast.makeText(this, jobType, Toast.LENGTH_SHORT).show();
        }

    }

    /*Removing duplicate words from Job Type*/
    private String removeDuplicates(String string){

        String[] words = string.split(",");
        StringBuilder result = new StringBuilder();
        for(int i=0; i<words.length; i++){

            if(words[i]!=null){

                for(int j = i+1; j<words.length; j++){

                    if(words[i].equals(words[j])){
                        words[j] = null;
                    }
                }

            }


        }
        for (String word : words) {
            if (word != null) {
                result.append(word).append(",");
            }
        }

        return String.valueOf(result);

    }


}