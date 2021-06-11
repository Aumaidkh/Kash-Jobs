package com.aumaid.jobskash.User.PostJobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aumaid.jobskash.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class PostJobThirdScreen extends AppCompatActivity {

    private TextInputEditText mExperience, mSkills;

    String _experience, _skills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job_third_screen);

        mExperience = findViewById(R.id.experience_box);
        mSkills = findViewById(R.id.skills_box);
    }

    public void nextPostJobScreen(View view) {

        if (!(validateExperience() || validateSkills())) {
            return;
        }
        _experience = mExperience.getText().toString().trim().concat(" Years");
        _skills = mSkills.getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), PostJobsLastScreen.class);

        /*Passing data to next activity*/
        intent.putExtra("COMPANY_NAME", getIntent().getStringExtra("COMPANY_NAME"));
        intent.putExtra("COMPANY_ADDRESS", getIntent().getStringExtra("COMPANY_ADDRESS"));
        intent.putExtra("CONTACT",getIntent().getStringExtra("CONTACT"));
        intent.putExtra("JOB_TYPE", getIntent().getStringExtra("JOB_TYPE"));
        intent.putExtra("SALARY", getIntent().getStringExtra("SALARY"));
        intent.putExtra("EXPERIENCE", _experience);
        intent.putExtra("SKILLS", _skills);

        startActivity(intent);
        /*Animating Screen*/
        this.overridePendingTransition(R.anim.slide_right_animation,
                R.anim.slide_left_animation);

    }

    /*Validating functions*/
    public boolean validateExperience() {
        String value = Objects.requireNonNull(mExperience.getText()).toString().toLowerCase().trim();
        if (value.isEmpty()) {
            mExperience.setError("Field can't be empty");
            return false;
        }
        return true;
    }

    public boolean validateSkills() {
        String value = Objects.requireNonNull(mSkills.getText()).toString().toLowerCase().trim();
        if (value.isEmpty()) {
            mSkills.setError("Field can't be empty");
            return false;
        } else {
            mSkills.setError(null);
            return true;
        }
    }
}