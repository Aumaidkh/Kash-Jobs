package com.aumaid.jobskash.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aumaid.jobskash.Interfaces.RecyclerViewItemClickListener;
import com.aumaid.jobskash.Models.JobModel;
import com.aumaid.jobskash.R;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private Context context;
    private ArrayList<JobModel> jobList;

    public RVAdapter(Context context, RecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.jobList = new ArrayList<>();
        this.context = context;
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }


    public void setItems(ArrayList<JobModel> jobs){
        jobList.addAll(jobs);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recycler_view,parent,false);
        return new JobsVH(view, onRecyclerViewItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        JobsVH vh = (JobsVH) holder;
        JobModel model = jobList.get(position);
        vh.jobTitle.setText(model.getJobTitle());
        vh.jobType.setText(model.getJobType());
        vh.companyName.setText(model.getCompanyName());
        vh.companyAddress.setText(model.getCompanyAddress());
        vh.salary.setText(model.getSalary());
        vh.skills.setText(model.getSkills());
        vh.experience.setText(model.getExperience());
        vh.postedOn.setText(model.getPostedOn());
        vh.hires.setText(model.getNumberOfHires());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

}
