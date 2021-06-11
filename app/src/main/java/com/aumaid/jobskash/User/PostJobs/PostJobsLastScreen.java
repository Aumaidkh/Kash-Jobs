package com.aumaid.jobskash.User.PostJobs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aumaid.jobskash.Common.SuccessScreen;
import com.aumaid.jobskash.Database.JobHelperClass;
import com.aumaid.jobskash.HelperClasses.InternetChecker;
import com.aumaid.jobskash.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

public class PostJobsLastScreen extends AppCompatActivity {

    private TextInputEditText mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_jobs_last_screen);

        mDescription = findViewById(R.id.post_job_des_box);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void postJob(View view) {

        /*Internet Connectivity Check*/
        InternetChecker internetChecker = new InternetChecker();
        if (!internetChecker.isConnected(this)) {
            Toast.makeText(getApplicationContext(), "No internet connection detected", Toast.LENGTH_SHORT).show();
            return;
        }

        String _companyName = getIntent().getStringExtra("COMPANY_NAME");
        String _companyAddress = getIntent().getStringExtra("COMPANY_ADDRESS");
        String _contact = getIntent().getStringExtra("CONTACT");
        String _jobType = getIntent().getStringExtra("JOB_TYPE");
        String _salary = getIntent().getStringExtra("SALARY");
        String _experience = getIntent().getStringExtra("EXPERIENCE");
        String _skills = getIntent().getStringExtra("SKILLS");
        String _description = mDescription.getText().toString().trim();
        String _posted_on = "Posted on " + LocalDate.now().toString();

        JobHelperClass job = new JobHelperClass(_jobType, _companyName, _companyAddress, _salary, _skills, _experience, _contact, _description, _posted_on);

        /*Connecting to Database*/

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Jobs");
        reference.child(_companyName).setValue(job);

        Toast.makeText(getApplicationContext(), "Post Job Pressed", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), SuccessScreen.class);
        intent.putExtra("Mode", 1);
        startActivity(intent);
        finish();


    }

}