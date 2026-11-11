package com.talentflow.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Candidate name cannot be blank")
    @Column(nullable = false)
    private String candidateName;

    @NotBlank(message = "Role cannot be blank")
    @Column(nullable = false)
    private String role;

    private String interviewer;

    private String date;

    private String time;

    private String type; // Technical Deep-Dive, Culture & Leadership, Screening

    private String link;

    @Column(nullable = false)
    private String status = "Scheduled"; // Scheduled, Confirmed, Completed, Cancelled

    public Interview() {
    }

    public Interview(String candidateName, String role, String interviewer, String date, String time, String type, String link, String status) {
        this.candidateName = candidateName;
        this.role = role;
        this.interviewer = interviewer;
        this.date = date;
        this.time = time;
        this.type = type;
        this.link = link;
        this.status = status != null ? status : "Scheduled";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
