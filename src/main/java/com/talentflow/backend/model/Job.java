package com.talentflow.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Job title cannot be blank")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Department cannot be blank")
    @Column(nullable = false)
    private String department;

    private String location;

    private String type; // Full-time, Contract, Part-time

    private String salary;

    @Column(nullable = false)
    private String status = "Open"; // Open, Paused, Closed

    private Integer applicantsCount = 0;

    private String postedDate;

    @Column(length = 2500)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "job_requirements", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "requirement")
    private List<String> requirements = new ArrayList<>();

    public Job() {
        this.postedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }

    public Job(String title, String department, String location, String type, String salary, String description, List<String> requirements) {
        this();
        this.title = title;
        this.department = department;
        this.location = location;
        this.type = type;
        this.salary = salary;
        this.description = description;
        this.requirements = requirements != null ? requirements : new ArrayList<>();
        this.status = "Open";
        this.applicantsCount = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getApplicantsCount() {
        return applicantsCount;
    }

    public void setApplicantsCount(Integer applicantsCount) {
        this.applicantsCount = applicantsCount;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }
}
