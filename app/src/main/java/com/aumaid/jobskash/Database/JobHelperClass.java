package com.aumaid.jobskash.Database;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class JobHelperClass {

    private String companyName;
    private String companyAddress;
    private String industryType;
    private String email;
    private String aboutCompany;
    private String jobTitle;
    private String jobType;
    private String numberOfHires;
    private String salary;
    private String skills;
    private String responsibilities;
    private String experience;
    private String qualifications;
    private String postedOn;

    public JobHelperClass() {
    }

    public JobHelperClass(String companyName, String companyAddress, String industryType, String email, String aboutCompany, String jobTitle, String jobType, String numberOfHires, String salary, String skills, String responsibilities, String experience, String qualifications, String postedOn) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.industryType = industryType;
        this.email = email;
        this.aboutCompany = aboutCompany;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.numberOfHires = numberOfHires;
        this.salary = salary;
        this.skills = skills;
        this.responsibilities = responsibilities;
        this.experience = experience;
        this.qualifications = qualifications;
        this.postedOn = postedOn;


    }

    public String getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(String postedOn) {
        this.postedOn = postedOn;
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

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutCompany() {
        return aboutCompany;
    }

    public void setAboutCompany(String aboutCompany) {
        this.aboutCompany = aboutCompany;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getNumberOfHires() {
        return numberOfHires;
    }

    public void setNumberOfHires(String numberOfHires) {
        this.numberOfHires = numberOfHires;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }
}
