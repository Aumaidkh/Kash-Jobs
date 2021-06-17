package com.aumaid.jobskash.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aumaid.jobskash.Adapters.JobAdapter;
import com.aumaid.jobskash.Common.LogInSignUp.SignInPage;
import com.aumaid.jobskash.Common.SuccessScreen;
import com.aumaid.jobskash.Database.JobHelperClass;
import com.aumaid.jobskash.Database.SessionManager;
import com.aumaid.jobskash.HelperClasses.InternetChecker;
import com.aumaid.jobskash.Interfaces.RecyclerViewItemClickListener;
import com.aumaid.jobskash.R;
import com.aumaid.jobskash.User.PostJobs.CompanyDetails;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.aumaid.jobskash.HelperClasses.AVariables.ABOUT_EMPLOYER;
import static com.aumaid.jobskash.HelperClasses.AVariables.CONTACT;
import static com.aumaid.jobskash.HelperClasses.AVariables.CYCLE_SELECTOR;
import static com.aumaid.jobskash.HelperClasses.AVariables.EMPLOYERS_ADDRESS;
import static com.aumaid.jobskash.HelperClasses.AVariables.EMPLOYERS_NAME;
import static com.aumaid.jobskash.HelperClasses.AVariables.EXPERIENCE;
import static com.aumaid.jobskash.HelperClasses.AVariables.HIRES_REQUIRED;
import static com.aumaid.jobskash.HelperClasses.AVariables.INDUSTRY_TYPE;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_DESCRIPTION;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_TITLE;
import static com.aumaid.jobskash.HelperClasses.AVariables.JOB_TYPE;
import static com.aumaid.jobskash.HelperClasses.AVariables.QUALIFICATIONS;
import static com.aumaid.jobskash.HelperClasses.AVariables.RANGE_SELECTOR;
import static com.aumaid.jobskash.HelperClasses.AVariables.SALARY;
import static com.aumaid.jobskash.HelperClasses.AVariables.SKILL;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RecyclerViewItemClickListener {


    /*Progress bar*/
    ProgressBar progressBar;

    ImageView mDrawerToggleButton;
    RelativeLayout contentView;

    static final float END_SCALE = 0.7f;

    //Drawer menu
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    /*Recycler View*/
    RecyclerView mRecyclerView;
    ArrayList<JobHelperClass> jobModelArrayList;
    JobAdapter adapter;
    DatabaseReference mbase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        progressBar = findViewById(R.id.progress_bar);

        mRecyclerView = findViewById(R.id.recyclerview1);

        /*adding onclick listener for recyclerview*/

        mbase = FirebaseDatabase.getInstance().getReference("Jobs");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        jobModelArrayList = new ArrayList<>();

        //Drawer menu
        contentView = findViewById(R.id.content);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigation_view);
        mDrawerToggleButton = findViewById(R.id.drawer_menu_btn);
        drawerMenu();


        /*Updating jobs using firebase*/
        progressBar.setVisibility(View.VISIBLE);
        addJobs();


    }


    //Dealing with navigation bar
    private void drawerMenu() {
        //Navigation View
        mNavigationView.bringToFront();
        mNavigationView.setNavigationItemSelectedListener(this);
        mDrawerToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        animateDrawer();
    }

    private void animateDrawer() {

        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                //Scale the view based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //Translate the View, accoutning for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);

                super.onDrawerSlide(drawerView, slideOffset);
            }

        });

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        /*Internet Connectivity Check*/
        InternetChecker internetChecker = new InternetChecker();
        if (!internetChecker.isConnected(this)) {
            Toast.makeText(getApplicationContext(), "No internet connection detected", Toast.LENGTH_SHORT).show();
            return false;
        }

        switch (item.getItemId()) {

            case R.id.log_out_btn:
                FirebaseAuth.getInstance().signOut();
                /*LOGGING OUT SESSION*/
                SessionManager sessionManager = new SessionManager(HomePage.this, SessionManager.USER_LOGIN_SESSION);
                sessionManager.logOut();

                Toast.makeText(getApplicationContext(), "You have been logged out successfully", Toast.LENGTH_SHORT).show();
                Intent mLogInIntent = new Intent(getApplicationContext(), SignInPage.class);
                startActivity(mLogInIntent);
                finish();
                break;

            case R.id.drawer_post_job_btn:
                /*Check if user is logged in and if he is not present him the log in screen*/

                postJobScreen();
                break;

            case R.id.profile_btn:
                /*Test button for job description screen*/
                startActivity(new Intent(getApplicationContext(), JobApplicationPage.class));
                break;

            case R.id.share_btn:
                /*Test button for new post job features*/
                break;

        }
        return true;
    }

    public void addJobs() {

        /*Internet Connectivity Check*/
        InternetChecker internetChecker = new InternetChecker();
        if (!internetChecker.isConnected(this)) {
            Toast.makeText(getApplicationContext(), "No internet connection detected", Toast.LENGTH_SHORT).show();
            return;
        }
        //


        // Create a instance of the database and get
        // its reference
        mbase
                = FirebaseDatabase.getInstance()
                .getReference("NewJobs");

        mRecyclerView = findViewById(R.id.recyclerview1);

        // To display the Recycler view linearly
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        adapter = new JobAdapter(jobModelArrayList, this,this);

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data

        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jobModelArrayList.clear();//Avoid duplicate cards

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    JobHelperClass job = dataSnapshot.getValue(JobHelperClass.class);
                    jobModelArrayList.add(job);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mRecyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    public void postJobScreen() {

        /*Post job without animations*/
        startActivity(new Intent(HomePage.this, CompanyDetails.class));

    }

    @Override
    public void onRecyclerViewItemCLickListener(int position) {
        Intent intent = new Intent(getApplicationContext(),JobApplicationPage.class);
        JobHelperClass job = jobModelArrayList.get(position);
        intent.putExtra("JOB",job);
        startActivity(intent);
    }
}