package com.aumaid.jobskash.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aumaid.jobskash.Adapters.JobAdapter;
import com.aumaid.jobskash.Common.LogInSignUp.SignInPage;
import com.aumaid.jobskash.Database.JobHelperClass;
import com.aumaid.jobskash.R;
import com.aumaid.jobskash.User.PostJobs.PostJobsFirstPage;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    final String perMonth = " /Month";
    final String postedOn = " Posted on";

    /*Progress bar*/
    ProgressBar progressBar;

    FirebaseAuth mAuth;
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

        mbase = FirebaseDatabase.getInstance("https://kash-jobs-9b913-jobs-rtdb.firebaseio.com/").getReference();

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

    @Override
    protected void onResume() {
        super.onResume();
       // Toast.makeText(getApplicationContext(),"OnResumeCalled", Toast.LENGTH_SHORT).show();
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

        switch(item.getItemId()){

            case R.id.log_out_btn:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(),"You have been logged out successfully",Toast.LENGTH_SHORT).show();
                Intent mLogInIntent = new Intent(getApplicationContext(), SignInPage.class);
                startActivity(mLogInIntent);
                finish();
                break;

            case R.id.drawer_post_job_btn:
                /*Check if user is logged in and if he is not present him the log in screen*/

                postJobScreen();
                break;
        }
        return true;
    }

    public void addJobs(){
        //


        // Create a instance of the database and get
        // its reference
        mbase
                = FirebaseDatabase.getInstance()
                .getReference("Jobs");

        mRecyclerView = findViewById(R.id.recyclerview1);

        // To display the Recycler view linearly
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        adapter = new JobAdapter(jobModelArrayList,this);

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data

        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jobModelArrayList.clear();//Avoid duplicate cards

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

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

    public void postJobScreen(){

        /*Post job without animations*/
        startActivity(new Intent(HomePage.this, PostJobsFirstPage.class));

    }
}