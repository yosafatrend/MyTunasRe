package com.spect.mytunas.models;

public class Job {
    private String jobName;
    private String jobSalary;
    private String jobDate;
    private String jobLink;
    private String jobCompany;
    private String jobLocation;
    private String jobDesc;

    public String getJobName() {
        return jobName;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public void setJobLink(String jobLink) {
        this.jobLink = jobLink;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getJobDate() {
        return jobDate;
    }

    public String getJobLink() {
        return jobLink;
    }

    public String getJobCompany() {
        return jobCompany;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public String getJobDesc() {
        return jobDesc;
    }
}
