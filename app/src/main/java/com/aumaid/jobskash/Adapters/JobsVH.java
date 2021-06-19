package com.aumaid.jobskash.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aumaid.jobskash.Interfaces.RecyclerViewItemClickListener;
import com.aumaid.jobskash.R;

public class JobsVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView jobTitle, jobType, companyName, companyAddress, salary, postedOn, experience, skills, hires;
    RecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public JobsVH(@NonNull View itemView, RecyclerViewItemClickListener onRecyclerViewItemClickListener) {
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
