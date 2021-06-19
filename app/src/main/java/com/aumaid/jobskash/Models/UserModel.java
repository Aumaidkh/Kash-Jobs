package com.aumaid.jobskash.Models;

public class UserModel {

    String fullName;
    String userName;
    String email;
    String password;
    String gender;
    String date;
    String phoneNumber;

    //For Firebase
    public UserModel(){}

    public UserModel(String fullName, String userName, String email, String password, String gender, String date, String phoneNumber) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.date = date;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
