package com.aumaid.jobskash.User.PostJobs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
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
import static com.aumaid.jobskash.HelperClasses.AVariables.HIRES_REQUIRED;
import static com.aumaid.jobskash.HelperClasses.AVariables.INDUSTRY_TYPE;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_DESCRIPTION;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_TITLE;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_TYPE;
import static com.aumaid.jobskash.HelperClasses.AVariables.RANGE_SELECTOR;
import static com.aumaid.jobskash.HelperClasses.AVariables.SALARY;
import static com.aumaid.jobskash.HelperClasses.AVariables.SKILL;

public class JobDescription extends AppCompatActivity {

    /*Declaring Views*/
    TextView mJobTitle, mCompanyNameAndLocation, mSalary;
    TextInputEditText mResponsibilities, mSkills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);

        /*Binding Views*/
        mJobTitle = findViewById(R.id.pj_job_title_tv);
        mCompanyNameAndLocation = findViewById(R.id.pj_company_name_address);
        mSalary = findViewById(R.id.pj_salary_range_tv);

        mResponsibilities = findViewById(R.id.pj_job_description_text_box);
        mSkills = findViewById(R.id.pj_skills_text_box);
        /*POPULATING TEXT VIEW ON THIS PAGE*/
        showTextOnTextViews();


    }

    public void nextPage(View view) {

        /*Internet Check Here*/
        InternetChecker internetChecker = new InternetChecker();
        if (!internetChecker.isConnected(this)) {
            Toast.makeText(this, "No internet connection detected", Toast.LENGTH_SHORT).show();
            return;
        }

        /*Validating fields*/
        Validator validator = new Validator();
        if (!((validator.validateMultiLineTextField(mResponsibilities,30) || (validator.validateMultiLineTextField(mSkills,20))))) {
            return;
        }

        String _Responsibilities = mResponsibilities.getText().toString().trim();
        String _Skills = mSkills.getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), Qualifications.class);
        /*Passing Data to next activity*/
        intent.putExtra(EMPLOYERS_NAME, getIntent().getStringExtra(EMPLOYERS_NAME));
        intent.putExtra(EMPLOYERS_ADDRESS, getIntent().getStringExtra(EMPLOYERS_ADDRESS));
        intent.putExtra(CONTACT, getIntent().getStringExtra(CONTACT));
        intent.putExtra(INDUSTRY_TYPE, getIntent().getStringExtra(INDUSTRY_TYPE));
        intent.putExtra(ABOUT_EMPLOYER, getIntent().getStringExtra(ABOUT_EMPLOYER));
        intent.putExtra(JOB_TYPE, getIntent().getStringExtra(JOB_TYPE));
        intent.putExtra(JOB_TITLE, getIntent().getStringExtra(JOB_TITLE));
        intent.putExtra(HIRES_REQUIRED, getIntent().getStringExtra(HIRES_REQUIRED));
        intent.putExtra(RANGE_SELECTOR, getIntent().getStringExtra(RANGE_SELECTOR));
        intent.putExtra(SALARY, getIntent().getStringExtra(SALARY));
        intent.putExtra(CYCLE_SELECTOR, getIntent().getStringExtra(CYCLE_SELECTOR));
        intent.putExtra(JOB_DESCRIPTION, _Responsibilities);
        intent.putExtra(SKILL, _Skills);

        Pair[] pair = new Pair[1];
        pair[0] = new Pair<View, String>(view, "save_job_description_transition");
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(JobDescription.this,pair).toBundle());

    }


    @SuppressLint("SetTextI18n")
    private void showTextOnTextViews() {
        Toast.makeText(getApplicationContext(),getIntent().getStringExtra(RANGE_SELECTOR),Toast.LENGTH_SHORT).show();
        mJobTitle.setText(getIntent().getStringExtra(JOB_TITLE));
        mCompanyNameAndLocation.setText(getIntent().getStringExtra(EMPLOYERS_NAME).concat(" - ").concat(getIntent().getStringExtra(EMPLOYERS_ADDRESS)));
        mSalary.setText(getIntent().getStringExtra(SALARY));
    }

}