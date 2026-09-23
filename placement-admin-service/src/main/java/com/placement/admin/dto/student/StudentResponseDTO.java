package com.placement.admin.dto.student;

import com.placement.admin.entity.enums.PlacementStatus;
import java.time.LocalDateTime;

public class StudentResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String rollNumber;
    private String department;
    private String course;
    private Integer graduationYear;
    private Double cgpa;
    private String skills;
    private PlacementStatus placementStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StudentResponseDTO() {
    }

    public StudentResponseDTO(Long id, String name, String email, String phone, String rollNumber,
                              String department, String course, Integer graduationYear, Double cgpa,
                              String skills, PlacementStatus placementStatus,
                              LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.rollNumber = rollNumber;
        this.department = department;
        this.course = course;
        this.graduationYear = graduationYear;
        this.cgpa = cgpa;
        this.skills = skills;
        this.placementStatus = placementStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public PlacementStatus getPlacementStatus() {
        return placementStatus;
    }

    public void setPlacementStatus(PlacementStatus placementStatus) {
        this.placementStatus = placementStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
