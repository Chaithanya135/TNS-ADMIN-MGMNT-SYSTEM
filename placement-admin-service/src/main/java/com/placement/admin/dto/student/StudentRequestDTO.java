package com.placement.admin.dto.student;

import com.placement.admin.entity.enums.PlacementStatus;
import jakarta.validation.constraints.*;

public class StudentRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;

    @NotBlank(message = "Phone cannot be blank")
    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @NotBlank(message = "Roll number cannot be blank")
    @Size(max = 50, message = "Roll number must not exceed 50 characters")
    private String rollNumber;

    @NotBlank(message = "Department cannot be blank")
    @Size(max = 100, message = "Department must not exceed 100 characters")
    private String department;

    @NotBlank(message = "Course cannot be blank")
    @Size(max = 100, message = "Course must not exceed 100 characters")
    private String course;

    @NotNull(message = "Graduation year cannot be null")
    @Min(value = 2000, message = "Graduation year must be valid (2000 or later)")
    @Max(value = 2100, message = "Graduation year must be valid (2100 or earlier)")
    private Integer graduationYear;

    @NotNull(message = "CGPA cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "CGPA must be between 0.0 and 10.0")
    @DecimalMax(value = "10.0", inclusive = true, message = "CGPA must be between 0.0 and 10.0")
    private Double cgpa;

    @Size(max = 500, message = "Skills must not exceed 500 characters")
    private String skills;

    private PlacementStatus placementStatus;

    public StudentRequestDTO() {
    }

    public StudentRequestDTO(String name, String email, String phone, String rollNumber,
                             String department, String course, Integer graduationYear,
                             Double cgpa, String skills, PlacementStatus placementStatus) {
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
}
