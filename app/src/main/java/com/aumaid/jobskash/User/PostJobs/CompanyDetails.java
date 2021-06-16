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

import com.aumaid.jobskash.HelperClasses.Validator;
import com.aumaid.jobskash.R;
import com.google.android.material.textfield.TextInputEditText;

import static com.aumaid.jobskash.HelperClasses.AVariables.ABOUT_EMPLOYER;
import static com.aumaid.jobskash.HelperClasses.AVariables.CONTACT;
import static com.aumaid.jobskash.HelperClasses.AVariables.EMPLOYERS_ADDRESS;
import static com.aumaid.jobskash.HelperClasses.AVariables.EMPLOYERS_NAME;
import static com.aumaid.jobskash.HelperClasses.AVariables.INDUSTRY_TYPE;

public class CompanyDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /*Declaring Views*/
    Spinner spinner;
    TextInputEditText mEmployerName, mEmployerLocation, mEmployerContact, mEmployerAbout;


    /*Declaring Other Variables*/
    String industryType;
    String options[] = {"Select an option",
            "Arts & Entertainment",
            "Automotive",
            "Construction & Facilities",
            "Education",
            "Sales",
            "Financial Services",
            "Staffing",
            "Information Technology",
            "Nonprofit & NGO",
            "Personal Consumer Services",
            "Power, Mining & Utilities",
            "Retail & Wholesale",
            "Restaurants & Food Services",
            "Telecommunications",
            "Transportation"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        /*Binding Textviews*/
        mEmployerName = findViewById(R.id.pj_employers_name_box);
        mEmployerLocation = findViewById(R.id.pj_location_box);
        mEmployerContact = findViewById(R.id.pj_email_box);
        mEmployerAbout = findViewById(R.id.pj_about_company_box);



        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spinner = (Spinner) findViewById(R.id.industry_type_spinner);
        spinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter industryType = new ArrayAdapter(this,android.R.layout.simple_spinner_item,options);
        industryType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(industryType);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*Storing option in variable*/
        if(position==0){
            industryType = "-";
            return;
        }
        industryType = options[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void nextPage(View view){
        /*Validating fields*/
        Validator validator = new Validator();
        if(!(validator.validateTextField(mEmployerName) || validator.validateTextField(mEmployerLocation) || validator.validateMultiLineTextField(mEmployerAbout,40) || validator.validateTextField(mEmployerContact))){
            return;
        }
        /*Getting values from the fields*/
        String _EmployerName = mEmployerName.getText().toString().trim();
        String _EmployerAddress = mEmployerLocation.getText().toString().trim();
        String _EmployerContact = mEmployerContact.getText().toString().trim();
        String _EmployerAbout = mEmployerAbout.getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), JobDetails.class);

        /*Passing Data to other activity*/
        intent.putExtra(EMPLOYERS_NAME,_EmployerName);
        intent.putExtra(EMPLOYERS_ADDRESS,_EmployerAddress);
        intent.putExtra(CONTACT,_EmployerContact);
        intent.putExtra(ABOUT_EMPLOYER,_EmployerAbout);
        intent.putExtra(INDUSTRY_TYPE,industryType);

        Pair[] pair = new Pair[1];
        pair[0] = new Pair<View, String>(view, "save_job_details_transition");
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(CompanyDetails.this,pair).toBundle());

    }


}