package com.aumaid.jobskash.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aumaid.jobskash.Database.JobHelperClass;
import com.aumaid.jobskash.R;

import java.util.ArrayList;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.Viewholder>{

    ArrayList<JobHelperClass> JobArrayList;
    Context context;

    public JobAdapter(ArrayList<JobHelperClass> JobArrayList, Context context) {
        this.JobArrayList = JobArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO: Inflate the view here
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recycler_view, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        JobHelperClass model = JobArrayList.get(position);
        holder.jobType.setText(model.getJobType());
        holder.companyName.setText(model.getCompanyName());
        holder.companyAddress.setText(model.getCompanyAddress());
        holder.salary.setText(model.getSalary());
        holder.skills.setText(model.getSkills());
        holder.experience.setText(model.getExperience());
        holder.postedOn.setText(model.getPostedOn());
    }

    @Override
    public int getItemCount() {
        return JobArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        //Todo: Declaring Variables
        TextView jobType, companyName, companyAddress, salary, postedOn, experience, skills;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            //TODO: Creating hooks
            jobType = itemView.findViewById(R.id.job_type);
            companyName = itemView.findViewById(R.id.company_name);
            companyAddress = itemView.findViewById(R.id.company_address);
            salary = itemView.findViewById(R.id.salary);
            experience = itemView.findViewById(R.id.dl_experience_tv);
            skills = itemView.findViewById(R.id.dl_skills_tv);
            postedOn = itemView.findViewById(R.id.posted_on);
        }
    }

}
