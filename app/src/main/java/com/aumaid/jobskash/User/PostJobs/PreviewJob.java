package com.aumaid.jobskash.User.PostJobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aumaid.jobskash.Common.SuccessScreen;
import com.aumaid.jobskash.Database.DAOJob;
import com.aumaid.jobskash.Models.JobModel;
import com.aumaid.jobskash.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Calendar;
import java.util.Date;

import static com.aumaid.jobskash.HelperClasses.AVariables.ABOUT_EMPLOYER;
import static com.aumaid.jobskash.HelperClasses.AVariables.CONTACT;
import static com.aumaid.jobskash.HelperClasses.AVariables.EMPLOYERS_ADDRESS;
import static com.aumaid.jobskash.HelperClasses.AVariables.EMPLOYERS_NAME;
import static com.aumaid.jobskash.HelperClasses.AVariables.EXPERIENCE;
import static com.aumaid.jobskash.HelperClasses.AVariables.HIRES_REQUIRED;
import static com.aumaid.jobskash.HelperClasses.AVariables.INDUSTRY_TYPE;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_DESCRIPTION;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_TITLE;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_TYPE;
import static com.aumaid.jobskash.HelperClasses.AVariables.QUALIFICATIONS;
import static com.aumaid.jobskash.HelperClasses.AVariables.SALARY;
import static com.aumaid.jobskash.HelperClasses.AVariables.SKILL;

public class PreviewJob extends AppCompatActivity {

    /*Declaring views*/
    TextView mCompanyName,
            mCompanyAddress,
            mIndustryType,
            mContact,
            mAboutCompany,
            mJobTitle,
            mJobType,
            mNumberOfHires,
            mSalary,
            mSkills,
            mResponsibilities,
            mExperience,
            mQualifications;



    /*Declaring Global Variables*/
    private String _CompanyName,
            _CompanyAddress,
            _IndustryType,
            _Contact,
            _AboutCompany,
            _JobTitle,
            _JobType,
            _NumberOfHires,
            _Salary,
            _Skills,
            _Responsibilities,
            _Experience,
            _Qualifications;
    private DAOJob daoJob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_job);

        /*Creating Hooks*/
        mCompanyName = findViewById(R.id.pr_company_name_tv);
        mCompanyAddress = findViewById(R.id.pr_company_address_tv);
        mIndustryType = findViewById(R.id.pr_industry_type_tv);
        mContact = findViewById(R.id.pr_contact_tv);
        mAboutCompany = findViewById(R.id.pr_company_about_tv);

        mJobTitle = findViewById(R.id.pr_job_title_tv);
        mJobType = findViewById(R.id.pr_job_type_tv);
        mNumberOfHires = findViewById(R.id.pr_number_of_hires_tv);

        mSalary = findViewById(R.id.pr_salary_tv);

        mSkills = findViewById(R.id.pr_skills_tv);
        mResponsibilities = findViewById(R.id.pr_responsibilities_tv);

        mExperience = findViewById(R.id.experience_tv);
        mQualifications = findViewById(R.id.pr_minimum_qualification_tv);

        receiveDataFromPreviousIntent();

        showDataOnTextViews();
        daoJob = new DAOJob();
    }

    private void receiveDataFromPreviousIntent() {
        /*Receiving data from previous intent*/

        _CompanyName = getIntent().getStringExtra(EMPLOYERS_NAME);
        _CompanyAddress = getIntent().getStringExtra(EMPLOYERS_ADDRESS);
        _IndustryType = getIntent().getStringExtra(INDUSTRY_TYPE);
        _Contact = getIntent().getStringExtra(CONTACT);
        _AboutCompany = getIntent().getStringExtra(ABOUT_EMPLOYER);

        _JobTitle = getIntent().getStringExtra(JOB_TITLE);
        _JobType = getIntent().getStringExtra(JOB_TYPE);
        _NumberOfHires = getIntent().getStringExtra(HIRES_REQUIRED);
        _Salary = getIntent().getStringExtra(SALARY);
        _Skills = getIntent().getStringExtra(SKILL);
        _Responsibilities = getIntent().getStringExtra(JOB_DESCRIPTION);

        _Experience = getIntent().getStringExtra(EXPERIENCE).concat(" Years");
        _Qualifications = getIntent().getStringExtra(QUALIFICATIONS);

    }

    private void showDataOnTextViews() {
        mCompanyName.setText(_CompanyName);
        mCompanyAddress.setText(_CompanyAddress);
        mIndustryType.setText(_IndustryType);
        mContact.setText(_Contact);
        mAboutCompany.setText(_AboutCompany);

        mJobTitle.setText(_JobTitle);
        mJobType.setText(_JobType);
        mNumberOfHires.setText(_NumberOfHires);

        mSalary.setText(_Salary);
        mSkills.setText(_Skills);
        mResponsibilities.setText(_Responsibilities);
        mExperience.setText(_Experience);
        mQualifications.setText(_Qualifications);
    }

    /*
    Button Functions
    */

    public void postJob(View view) {
        /*Creating Job Object*/
        Date currentTime = Calendar.getInstance().getTime();

        JobModel job = new JobModel(_CompanyName,
                _CompanyAddress,
                _IndustryType,
                _Contact,
                _AboutCompany,
                _JobTitle,
                _JobType,
                _NumberOfHires,
                _Salary,
                _Skills,
                _Responsibilities,
                _Experience,
                _Qualifications,
                currentTime.toString());


        /*Connecting to firebase and saving Job in it*/
        daoJob.add(job).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        /*Presenting success Screen*/
        Intent intent = new Intent(getApplicationContext(), SuccessScreen.class);
        intent.putExtra("Mode", 1);
        startActivity(intent);
        startActivity(intent);
        finish();


    }

    public void edit(View view) {
        //Under development
//        Button button = (Button) view;
//
//        int tag = Integer.parseInt(button.getTag().toString());
//
//        Pair[] pairs = new Pair[1];
//
//        switch (tag){
//            case 1:
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                Intent intent = new Intent(getApplicationContext(), JobDescription.class);
//                pairs[0] = new Pair<View, String>(button,"edit_job_description_transitions");
//                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,pairs).toBundle());
//                break;
//            case 5:
//                Intent editQualifications = new Intent(getApplicationContext(),Qualifications.class);
//                pairs[0] = new Pair<View, String>(button,"edit_qualifications_transitions");
//                startActivity(editQualifications, ActivityOptions.makeSceneTransitionAnimation(this,pairs).toBundle());
//                break;
//
//
//        }
    }
}