package com.aumaid.jobskash.User.PostJobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aumaid.jobskash.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class PostJobsSecondScreen extends AppCompatActivity {

    private TextInputEditText mJobType, mSalary;

    private String _jobType, _salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_jobs_second_screen);

        mJobType = findViewById(R.id.job_type_box);
        mSalary = findViewById(R.id.salary_box);

    }

    public void nextPostJobScreen(View view){

        if(!(validateJobType()||validateSalary())){
            return;
        }

        _jobType = mJobType.getText().toString().trim();
        _salary = mSalary.getText().toString().trim().concat("/MONTH");

        Intent intent = new Intent(getApplicationContext(), PostJobThirdScreen.class);

        /*Passing data to next activity*/
        intent.putExtra("COMPANY_NAME",getIntent().getStringExtra("COMPANY_NAME"));
        intent.putExtra("COMPANY_ADDRESS",getIntent().getStringExtra("COMPANY_ADDRESS"));
        intent.putExtra("CONTACT",getIntent().getStringExtra("CONTACT"));
        intent.putExtra("JOB_TYPE",_jobType);
        intent.putExtra("SALARY",_salary);

        startActivity(intent);
        /*Animating Screen*/
        this.overridePendingTransition(R.anim.slide_right_animation,
                R.anim.slide_left_animation);


    }

    public boolean validateJobType(){
        String value = Objects.requireNonNull(mJobType.getText()).toString().toLowerCase().trim();
        if(value.isEmpty()){
            mJobType.setError("Field can't be empty");
            return false;
        }
        _jobType = mJobType.getText().toString();
        return true;
    }

    public boolean validateSalary(){
        String value = Objects.requireNonNull(mSalary.getText()).toString().toLowerCase().trim();
        if(value.isEmpty()){
            mSalary.setError("Field can't be empty");
            return false;
        }
        _salary = mSalary.getText().toString();
        return true;
    }
}