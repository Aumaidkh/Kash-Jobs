package com.aumaid.jobskash.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class JobModel implements Parcelable {

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

    public JobModel() {
    }

    public JobModel(String companyName, String companyAddress, String industryType, String email, String aboutCompany, String jobTitle, String jobType, String numberOfHires, String salary, String skills, String responsibilities, String experience, String qualifications, String postedOn) {
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

    protected JobModel(Parcel in) {
        companyName = in.readString();
        companyAddress = in.readString();
        industryType = in.readString();
        email = in.readString();
        aboutCompany = in.readString();
        jobTitle = in.readString();
        jobType = in.readString();
        numberOfHires = in.readString();
        salary = in.readString();
        skills = in.readString();
        responsibilities = in.readString();
        experience = in.readString();
        qualifications = in.readString();
        postedOn = in.readString();
    }

    public static final Creator<JobModel> CREATOR = new Creator<JobModel>() {
        @Override
        public JobModel createFromParcel(Parcel in) {
            return new JobModel(in);
        }

        @Override
        public JobModel[] newArray(int size) {
            return new JobModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companyName);
        dest.writeString(companyAddress);
        dest.writeString(industryType);
        dest.writeString(email);
        dest.writeString(aboutCompany);
        dest.writeString(jobTitle);
        dest.writeString(jobType);
        dest.writeString(numberOfHires);
        dest.writeString(salary);
        dest.writeString(skills);
        dest.writeString(responsibilities);
        dest.writeString(experience);
        dest.writeString(qualifications);
        dest.writeString(postedOn);
    }
}
