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
import android.widget.TextView;
import android.widget.Toast;

import com.aumaid.jobskash.HelperClasses.ErrorMessages;
import com.aumaid.jobskash.HelperClasses.InternetChecker;
import com.aumaid.jobskash.HelperClasses.Validator;
import com.aumaid.jobskash.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static com.aumaid.jobskash.HelperClasses.AVariables.ABOUT_EMPLOYER;
import static com.aumaid.jobskash.HelperClasses.AVariables.CONTACT;
import static com.aumaid.jobskash.HelperClasses.AVariables.CYCLE_SELECTOR;
import static com.aumaid.jobskash.HelperClasses.AVariables.EMPLOYERS_ADDRESS;
import static com.aumaid.jobskash.HelperClasses.AVariables.EMPLOYERS_NAME;
import static com.aumaid.jobskash.HelperClasses.AVariables.HIRES_REQUIRED;
import static com.aumaid.jobskash.HelperClasses.AVariables.INDUSTRY_TYPE;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_TITLE;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_TYPE;
import static com.aumaid.jobskash.HelperClasses.AVariables.RANGE_SELECTOR;
import static com.aumaid.jobskash.HelperClasses.AVariables.SALARY;

public class SalaryDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /*Declaring Views*/
    Spinner mRangeSpinner, mCycleSpinner;
    TextInputEditText mFromSalary, mToSalary;
    TextInputLayout mToSalaryBox;
    TextView mToTextView;

    /*Declaring Other Variables*/
    String[] rangeOptions = {"Range ","Starting at ","Up to ", "Exact rate "};
    String[] cycleOptions = {"per hour", "per month", "per week", "per year"};

    String range = "";
    String cycle ="";
    String salary = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_details);
        mRangeSpinner = findViewById(R.id.salary_range_spinner);
        mCycleSpinner = findViewById(R.id.cycle_spinner);
        mFromSalary = findViewById(R.id.pj_from_salary_box);
        mToSalary = findViewById(R.id.pj_to_salary_box);
        mToSalaryBox = findViewById(R.id.pj_to_salary_ip_box);
        mToTextView = findViewById(R.id.to_tv);

        /*Setting Spinners*/
        mRangeSpinner.setOnItemSelectedListener(this);
        mCycleSpinner.setOnItemSelectedListener(this);

        ArrayAdapter range = new ArrayAdapter(this,android.R.layout.simple_spinner_item,rangeOptions);
        range.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter cycle = new ArrayAdapter(this,android.R.layout.simple_spinner_item,cycleOptions);
        cycle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mRangeSpinner.setAdapter(range);
        mCycleSpinner.setAdapter(cycle);
        /*----------------------------------------*/




    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int _id = parent.getId();
        switch (_id){
            case R.id.salary_range_spinner:
                if(position!=0){
                    mToSalaryBox.setVisibility(View.GONE);
                    mToTextView.setVisibility(View.GONE);

                }
                range = rangeOptions[position];
                break;

            case R.id.cycle_spinner:
                cycle = cycleOptions[position];
                break;
        }


    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void nextPage(View view){
        /*Internet Connectivity Check*/
        InternetChecker internetChecker = new InternetChecker();
        if(!internetChecker.isConnected(this)){
            Toast.makeText(this, ErrorMessages.NO_INTERNET_CONNECTION, Toast.LENGTH_SHORT).show();
            return;
        }

        /*Validating Fields*/
        Validator validator = new Validator();
        if(!(validator.validateTextField(mFromSalary)||validator.validateTextField(mToSalary))){
            return;
        }

        String _fromSalary = mFromSalary.getText().toString().trim();
        String _toSalary = mToSalary.getText().toString().trim();
        if(!_toSalary.isEmpty()){
            salary = range+"Rs. "+_fromSalary +" - "+"Rs. "+ _toSalary+" "+cycle;
        }else{
            salary = range + "Rs. "+_fromSalary+" "+cycle;
        }

        /*Starting next activity and passing data to it*/
        Intent intent = new Intent(getApplicationContext(),JobDescription.class);

        intent.putExtra(EMPLOYERS_NAME,getIntent().getStringExtra(EMPLOYERS_NAME));
        intent.putExtra(EMPLOYERS_ADDRESS,getIntent().getStringExtra(EMPLOYERS_ADDRESS));
        intent.putExtra(CONTACT,getIntent().getStringExtra(CONTACT));
        intent.putExtra(INDUSTRY_TYPE,getIntent().getStringExtra(INDUSTRY_TYPE));
        intent.putExtra(ABOUT_EMPLOYER,getIntent().getStringExtra(ABOUT_EMPLOYER));
        intent.putExtra(JOB_TYPE,getIntent().getStringExtra(JOB_TYPE));
        intent.putExtra(JOB_TITLE,getIntent().getStringExtra(JOB_TITLE));
        intent.putExtra(HIRES_REQUIRED,getIntent().getStringExtra(HIRES_REQUIRED));
        intent.putExtra(RANGE_SELECTOR,range);
        intent.putExtra(SALARY,salary);
        intent.putExtra(CYCLE_SELECTOR,cycle);

        Pair[] pair = new Pair[1];
        pair[0] = new Pair<View, String>(view, "save_job_description_transition");
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SalaryDetails.this,pair).toBundle());

    }
}