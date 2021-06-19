package com.aumaid.jobskash.Database;

import com.aumaid.jobskash.Models.JobModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOJob {

    private DatabaseReference databaseReference;

    public DAOJob() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference(JobModel.class.getSimpleName());
    }

    public Task<Void> add(JobModel job){
        return databaseReference.push().setValue(job);
    }

    public Task<Void> update(String key, JobModel job){
        return databaseReference.child(key).setValue(job);
    }

    public Query get(String key){

        if(key == null){
            return databaseReference.orderByKey().limitToFirst(4);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(4);

    }
}
