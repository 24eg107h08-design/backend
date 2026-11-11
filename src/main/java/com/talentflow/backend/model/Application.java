package com.talentflow.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;

    @NotBlank(message = "Job title cannot be blank")
    private String jobTitle;

    private String company;

    @NotBlank(message = "Applicant name cannot be blank")
    private String applicantName;

    @NotBlank(message = "Applicant email cannot be blank")
    @Email(message = "Invalid email format")
    private String applicantEmail;

    private String appliedDate;

    @Column(nullable = false)
    private String status = "Under Review"; // Under Review, Interview, Offer, Rejected

    private Integer stageNumber = 1;

    private String matchScore = "85%";

    @Column(length = 1000)
    private String notes;

    @Column(length = 2000)
    private String coverNote;

    public Application() {
        this.appliedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }

    public Application(Long jobId, String jobTitle, String company, String applicantName, String applicantEmail, String status, Integer stageNumber, String matchScore, String notes) {
        this();
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.company = company;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.status = status;
        this.stageNumber = stageNumber;
        this.matchScore = matchScore;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(Integer stageNumber) {
        this.stageNumber = stageNumber;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCoverNote() {
        return coverNote;
    }

    public void setCoverNote(String coverNote) {
        this.coverNote = coverNote;
    }
}
