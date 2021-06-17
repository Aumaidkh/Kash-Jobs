package com.aumaid.jobskash.User.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aumaid.jobskash.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JobDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobDescriptionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JobDescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JobDescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JobDescriptionFragment newInstance(String param1, String param2) {
        JobDescriptionFragment fragment = new JobDescriptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //            mSkills.setText(job.getSkills());
//            mSalary.setText(job.getSalary());
//            mAboutCompany.setText(job.getAboutCompany());
//            mEmploymentType.setText(job.getJobType());
//            mIndustryType.setText(job.getIndustryType());
//            mQualifications.setText(job.getQualifications());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_details, container, false);
    }
}