package com.aumaid.jobskash.Database;

public class JobHelperClass {

    String jobType;
    String companyName;
    String companyAddress;
    String salary;
    String email;
    String description;
    String postedOn;


    public JobHelperClass(){}

    public JobHelperClass(String jobType, String companyName, String companyAddress, String salary,String email, String description, String postedOn) {
        this.jobType = jobType;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.salary = salary;
        this.email = email;
        this.description = description;
        this.postedOn = postedOn;
    }

    public String getJobType() {
        return jobType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(String postedOn) {
        this.postedOn = postedOn;
    }

}
