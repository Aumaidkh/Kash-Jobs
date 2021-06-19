package com.aumaid.jobskash.Database;

import com.aumaid.jobskash.Models.UserModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOUser {

    private DatabaseReference reference;

    public DAOUser(DatabaseReference reference) {
        this.reference = reference;
    }

    public Task<Void> add(UserModel user){
        return reference.push().setValue(user);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return reference.child(key).updateChildren(hashMap);
    }

    public Query get(){
        return reference.orderByKey();
    }
}
