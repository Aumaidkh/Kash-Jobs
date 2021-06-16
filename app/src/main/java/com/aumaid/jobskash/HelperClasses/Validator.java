package com.aumaid.jobskash.HelperClasses;


import com.google.android.material.textfield.TextInputEditText;

public class Validator {

    public boolean validateTextField(TextInputEditText view){
        String value = view.getText().toString().trim();
        if(value.isEmpty()){
            view.setError("Field can't be empty");
            return false;
        }
        return true;
    }

    public boolean validateMultiLineTextField(TextInputEditText view,int minSize){
        String value = view.getText().toString().trim();
        if(value.isEmpty()){
            view.setError("Field can't be blank");
            return false;
        }else if(value.length()<minSize){
            view.setError("Description shouldn't be less than "+minSize);
            return false;
        }
        return true;
    }
}
