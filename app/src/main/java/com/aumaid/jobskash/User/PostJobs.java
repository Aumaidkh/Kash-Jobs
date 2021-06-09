package com.aumaid.jobskash.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aumaid.jobskash.Common.SuccessScreen;
import com.aumaid.jobskash.Database.JobHelperClass;
import com.aumaid.jobskash.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.Objects;

public class PostJobs extends AppCompatActivity {

    /*Declaring Constants*/
    final String perMonth = " /Month";
    final String postedOn = "Posted on ";

    /*Declaring Variables*/
    String _jobType;
    String _companyname;
    String _companyaddress;
    String _salary;
    String _posted_on;
    String _jobdescription;
    String _email;

    /*Creating hooks for text boxes*/
    TextInputEditText mJobType, mCompanyname, mCompanyAddress, mSalary, mJobDescription, mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_jobs);

        /*Creating hooks*/
        mJobType = findViewById(R.id.job_type_box);
        mCompanyname = findViewById(R.id.employers_name_box);
        mCompanyAddress = findViewById(R.id.employers_address_box);
        mSalary = findViewById(R.id.salary_box);
        mEmail = findViewById(R.id.post_job_email_box);
        mJobDescription = findViewById(R.id.post_job_des_box);

    }

    public void postJob(View view){

        if(!(validateJobType()||validateCompanyName()||validateCompanyAddress()||validateSalary()||validateJobDescription()||validateEmail())){
            Toast.makeText(getApplicationContext(),"Invalid Data",Toast.LENGTH_SHORT);
            return;
        }

        _jobType = mJobType.getText().toString();
        _companyname = mCompanyname.getText().toString();
        _companyaddress = mCompanyAddress.getText().toString();
        _salary = mSalary.getText().toString()+perMonth;
        _email = mEmail.getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            _posted_on = postedOn+ LocalDate.now().toString();
        }
        _jobdescription = mJobDescription.getText().toString();




        JobHelperClass job = new JobHelperClass(_jobType, _companyname, _companyaddress, _salary, _email, _jobdescription,_posted_on);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kash-jobs-a8dc9-jobs-rtdb.firebaseio.com/");
        DatabaseReference reference = database.getReference("Jobs");

        reference.child(_companyname).setValue(job);
        Toast.makeText(getApplicationContext(),"Post Job Pressed", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), SuccessScreen.class);
        intent.putExtra("Mode",1);
        startActivity(intent);
        finish();



    }

    /*Extracting day of the week*/
    private String findday(String day){
        String newDay = "";
        for(int i=0; i<day.length(); i++){
            if(day.charAt(i)!=0&&day.charAt(i)!=1){
                newDay += day.charAt(i);
            }else{
                break;
            }
        }
        return newDay;
    }


    /*Data Validation Functions*/

    public boolean validateJobType(){
        String value = Objects.requireNonNull(mCompanyname.getText()).toString().toLowerCase().trim();
        if(value.isEmpty()){
            mJobType.setError("Field can't be empty");
            return false;
        }
        _jobType = mJobType.getText().toString();
        return true;
    }

    public boolean validateCompanyAddress(){
        String value = Objects.requireNonNull(mCompanyAddress.getText()).toString().toLowerCase().trim();
        if(value.isEmpty()){
            mCompanyAddress.setError("Field can't be empty");
            return false;
        }
        _companyaddress = mCompanyAddress.getText().toString();
        return true;
    }

    public boolean validateCompanyName(){
        String value = Objects.requireNonNull(mCompanyname.getText()).toString().toLowerCase().trim();
        if (value.isEmpty()) {
            mCompanyname.setError("Field can't be empty");
            return false;
        } else {
            mCompanyname.setError(null);
            return true;
        }
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

    public boolean validateJobDescription(){
        String value = Objects.requireNonNull(mJobDescription.getText()).toString().toLowerCase().trim();
        if(value.isEmpty()){
            mJobDescription.setError("Field can't be empty");
            return false;
        }
        _jobdescription = mJobDescription.getText().toString();
        return true;
    }

    public boolean validateEmail(){
        String value = Objects.requireNonNull(mEmail.getText()).toString().toLowerCase().trim();
        if(value.isEmpty()){
            mEmail.setError("Field can't be empty");
            return false;
        }
        _email= mEmail.getText().toString();
        return true;
    }

}