package com.aumaid.jobskash.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aumaid.jobskash.Models.JobModel;
import com.aumaid.jobskash.R;

public class JobApplicationPage extends AppCompatActivity {

    /*Scroll View*/
    ScrollView scrollView;
    TextView
            mJobName,
            mRole,
            mExperience,
            mCompanyName,
            mCompanyAddress,
            mCompanyLocation,
            mVacancies,
            mSkills,
            mSalary,
            mJobDescription,
            mQualifications,
            mIndustryType,
            mEmploymentType,
            mAboutCompany,
            mCompanyLocation_,
            mEmployerName,
            mPostedOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_application_page);

        //      mAboutCompanySection = findViewById(R.id.jd_about_company_section);
        scrollView = findViewById(R.id.jap_scroll_view);
        mJobName = findViewById(R.id.jap_job_title);
        mExperience = findViewById(R.id.jap_experience);
        mCompanyName = findViewById(R.id.jap_company_name);
        mCompanyAddress = findViewById(R.id.jap_company_address);
        mPostedOn = findViewById(R.id.jap_posted_on);
        mVacancies = findViewById(R.id.jap_vacancies);
        mCompanyLocation = findViewById(R.id.jap_address);
        mSalary = findViewById(R.id.jap_salary);
        mJobDescription = findViewById(R.id.jap_job_description);
        mQualifications = findViewById(R.id.jap_education);
        mIndustryType = findViewById(R.id.jap_industry_type);
        mEmploymentType = findViewById(R.id.jap_employment_type);
        mAboutCompany = findViewById(R.id.jap_about_company);
        mCompanyLocation_ = findViewById(R.id.jap_company_location);
        mEmployerName = findViewById(R.id.jap_company);
        mSkills = findViewById(R.id.jap_skills);
        mRole = findViewById(R.id.jap_role_type);


        populateTextViews();


    }

    public void apply(View view){
        /*Apply for the job
        * First send email to the employee
        * Second add this job to applied jobs for current user*/
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jap_description_tv:
                scrollView.scrollTo(0,0);
            //    Toast.makeText(getApplicationContext(), "About company", Toast.LENGTH_SHORT).show();
                break;
            case R.id.jap_about_company_tv:
                scrollView.scrollTo(0, 1400);
                //Toast.makeText(getApplicationContext(), "About company", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void populateTextViews() {
        /*Receive data from previous*/
        if (getIntent().hasExtra("JOB")) {
            JobModel job = getIntent().getParcelableExtra("JOB");
            mJobName.setText(job.getJobTitle());
            mCompanyName.setText(job.getCompanyName());
            mCompanyAddress.setText(job.getCompanyAddress());
            mPostedOn.setText(job.getPostedOn().substring(0, 9));
            mExperience.setText(job.getExperience());
            mVacancies.setText(job.getNumberOfHires());
            mCompanyLocation.setText(job.getCompanyAddress());
            mSalary.setText(job.getSalary());
            mSkills.setText(job.getSkills());
            mJobDescription.setText(job.getResponsibilities());
            mIndustryType.setText(job.getIndustryType());
            mRole.setText(job.getJobTitle());
            mEmploymentType.setText(job.getJobType());
            mQualifications.setText(job.getQualifications());
            mAboutCompany.setText(job.getAboutCompany());
            mEmployerName.setText(job.getCompanyName());
            mCompanyLocation_.setText(job.getCompanyAddress());

        }


    }
}