package com.aumaid.jobskash.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.aumaid.jobskash.R;
import com.aumaid.jobskash.User.Fragments.JobDescriptionFragment;

public class JobApplicationPage extends AppCompatActivity {

    /*Scroll View*/
    ScrollView scrollView;
    TextView mAboutCompanySection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_application_page);

        scrollView = findViewById(R.id.scroll_view);
        mAboutCompanySection = findViewById(R.id.jd_about_company_section);

        getSupportFragmentManager().beginTransaction().add(R.id.layout, new JobDescriptionFragment()).commit();

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jd_toobar_job_details_btn:
                Toast.makeText(getApplicationContext(), "About company", Toast.LENGTH_SHORT).show();
                break;
            case R.id.jd_toobar_about_company_btn:
                scrollView.scrollTo(0,1400);
                Toast.makeText(getApplicationContext(), "About company", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}