package com.aumaid.jobskash.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aumaid.jobskash.Database.JobHelperClass;
import com.aumaid.jobskash.Interfaces.RecyclerViewItemClickListener;
import com.aumaid.jobskash.R;

import java.util.ArrayList;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.Viewholder>{

    private RecyclerViewItemClickListener onRecyclerViewItemClickListener;
    ArrayList<JobHelperClass> JobArrayList;
    Context context;

    public JobAdapter(ArrayList<JobHelperClass> JobArrayList, Context context, RecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.JobArrayList = JobArrayList;
        this.context = context;
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO: Inflate the view here
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recycler_view, parent, false);
        return new Viewholder(view,onRecyclerViewItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        JobHelperClass model = JobArrayList.get(position);
        holder.jobTitle.setText(model.getJobTitle());
        holder.jobType.setText(model.getJobType());
        holder.companyName.setText(model.getCompanyName());
        holder.companyAddress.setText(model.getCompanyAddress());
        holder.salary.setText(model.getSalary());
        holder.skills.setText(model.getSkills());
        holder.experience.setText(model.getExperience());
        holder.postedOn.setText(model.getPostedOn());
        holder.hires.setText(model.getNumberOfHires());
    }

    @Override
    public int getItemCount() {
        return JobArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Todo: Declaring Variables
        TextView jobTitle, jobType, companyName, companyAddress, salary, postedOn, experience, skills, hires;
        RecyclerViewItemClickListener onRecyclerViewItemClickListener;

        public Viewholder(@NonNull View itemView, RecyclerViewItemClickListener onRecyclerViewItemClickListener) {
            super(itemView);

            //TODO: Creating hooks
            jobTitle = itemView.findViewById(R.id.job_title);
            jobType = itemView.findViewById(R.id.dl_job_type_tv);
            companyName = itemView.findViewById(R.id.company_name);
            companyAddress = itemView.findViewById(R.id.fg_company_address);
            salary = itemView.findViewById(R.id.salary);
            experience = itemView.findViewById(R.id.dl_experience_tv);
            hires = itemView.findViewById(R.id.dl_number_of_hires);
            skills = itemView.findViewById(R.id.dl_skills_tv);
            postedOn = itemView.findViewById(R.id.posted_on);

            this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRecyclerViewItemClickListener.onRecyclerViewItemCLickListener(getAdapterPosition());
        }
    }


}
