# Placement Management System - Admin Service

[![Java 21](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Database](https://img.shields.io/badge/Database-H2%20In--Memory-blue.svg)](https://www.h2database.com/)
[![Build Tool](https://img.shields.io/badge/Build-Maven%20Wrapper-red.svg)](https://maven.apache.org/)

---

## 1. Project Overview

The **Placement Management System** is an enterprise-grade academic platform designed to streamline campus recruitment workflows. The complete system architecture comprises three microservices:
1. **Login Service** (Authentication & Role-Based Authorization)
2. **Student Service** (Student Profile & Resume Portal)
3. **Admin Service** (Central Placement Office Operations & Drive Orchestration)

> [!IMPORTANT]
> **This repository contains the complete, standalone, and independently runnable backend for the ADMIN SERVICE ONLY.**
> It requires **zero external dependencies** or other running services. You can test all APIs out-of-the-box using Postman or run the comprehensive automated test suite.

---

## 2. Admin Service Responsibilities

The Admin Service empowers college placement officers and administrators to manage the complete end-to-end recruitment lifecycle:
* **Administrator Management**: Manage administrator accounts and authenticate placement officers.
* **Student Management**: Register, update, query, search, and categorize student academic and placement records.
* **Company Management**: Onboard recruitment partners, manage company profiles, industry sectors, and active/inactive statuses.
* **Placement Drive Management**: Schedule campus recruitment drives with specific eligibility criteria, cutoffs (CGPA), packages (LPA), locations, and deadlines.
* **Placement Application Management**: Track and transition student job applications across stages (`APPLIED` $\rightarrow$ `SHORTLISTED` $\rightarrow$ `SELECTED` / `REJECTED`), automatically updating student placement status upon selection.
* **Live Placement Dashboard & Statistics**: Dynamically compute aggregated analytics directly from the database (total counts, placement rate, active drives, application breakdown).

---

## 3. Technology Stack

* **Programming Language**: Java 21 (LTS)
* **Framework**: Spring Boot 3.3.4
* **Web Layer**: Spring Web (Spring MVC REST Controllers)
* **Persistence Layer**: Spring Data JPA & Hibernate
* **Database**: H2 In-Memory Database (`jdbc:h2:mem:placementdb`)
* **Validation**: Jakarta Bean Validation (`jakarta.validation-api` / Hibernate Validator)
* **Build Tool**: Maven with Maven Wrapper (`mvnw` / `mvnw.cmd`)
* **API Testing**: Postman Collection (Pre-configured)
* **Testing Suite**: JUnit 5, Spring Boot Test, MockMvc, AssertJ

---

## 4. Prerequisites

* **Java Development Kit (JDK)**: Java 21 or higher installed.
* **Operating System**: Windows / macOS / Linux.
* **Network**: Internet access during first run to download Maven dependencies.

---

## 5. Project Directory Structure

```
placement-admin-service/
├── .mvn/wrapper/
│   └── maven-wrapper.properties
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── Placement_Admin_Service_Postman_Collection.json
└── src/
    ├── main/
    │   ├── java/com/placement/admin/
    │   │   ├── PlacementAdminServiceApplication.java
    │   │   ├── config/
    │   │   │   ├── CorsConfig.java
    │   │   │   └── DataInitializer.java
    │   │   ├── controller/
    │   │   │   ├── AdminController.java
    │   │   │   ├── StudentController.java
    │   │   │   ├── CompanyController.java
    │   │   │   ├── PlacementDriveController.java
    │   │   │   ├── ApplicationController.java
    │   │   │   └── DashboardController.java
    │   │   ├── dto/
    │   │   │   ├── common/
    │   │   │   │   ├── ApiResponse.java
    │   │   │   │   └── ErrorResponse.java
    │   │   │   ├── admin/
    │   │   │   │   ├── AdminRequestDTO.java
    │   │   │   │   ├── AdminResponseDTO.java
    │   │   │   │   ├── AdminLoginRequestDTO.java
    │   │   │   │   └── AdminLoginResponseDTO.java
    │   │   │   ├── student/
    │   │   │   │   ├── StudentRequestDTO.java
    │   │   │   │   └── StudentResponseDTO.java
    │   │   │   ├── company/
    │   │   │   │   ├── CompanyRequestDTO.java
    │   │   │   │   └── CompanyResponseDTO.java
    │   │   │   ├── drive/
    │   │   │   │   ├── PlacementDriveRequestDTO.java
    │   │   │   │   └── PlacementDriveResponseDTO.java
    │   │   │   ├── application/
    │   │   │   │   ├── ApplicationRequestDTO.java
    │   │   │   │   ├── ApplicationStatusUpdateDTO.java
    │   │   │   │   └── ApplicationResponseDTO.java
    │   │   │   └── dashboard/
    │   │   │       └── DashboardResponseDTO.java
    │   │   ├── entity/
    │   │   │   ├── Admin.java
    │   │   │   ├── Student.java
    │   │   │   ├── Company.java
    │   │   │   ├── PlacementDrive.java
    │   │   │   ├── Application.java
    │   │   │   └── enums/
    │   │   │       ├── AdminRole.java
    │   │   │       ├── PlacementStatus.java
    │   │   │       ├── CompanyStatus.java
    │   │   │       ├── DriveStatus.java
    │   │   │       └── ApplicationStatus.java
    │   │   ├── exception/
    │   │   │   ├── ResourceNotFoundException.java
    │   │   │   ├── DuplicateResourceException.java
    │   │   │   ├── InvalidStatusException.java
    │   │   │   ├── InvalidOperationException.java
    │   │   │   └── GlobalExceptionHandler.java
    │   │   ├── repository/
    │   │   │   ├── AdminRepository.java
    │   │   │   ├── StudentRepository.java
    │   │   │   ├── CompanyRepository.java
    │   │   │   ├── PlacementDriveRepository.java
    │   │   │   └── ApplicationRepository.java
    │   │   └── service/
    │   │       ├── AdminService.java
    │   │       ├── StudentService.java
    │   │       ├── CompanyService.java
    │   │       ├── PlacementDriveService.java
    │   │       ├── ApplicationService.java
    │   │       ├── DashboardService.java
    │   │       └── impl/
    │   │           ├── AdminServiceImpl.java
    │   │           ├── StudentServiceImpl.java
    │   │           ├── CompanyServiceImpl.java
    │   │           ├── PlacementDriveServiceImpl.java
    │   │           ├── ApplicationServiceImpl.java
    │   │           └── DashboardServiceImpl.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/com/placement/admin/
            ├── PlacementAdminServiceApplicationTests.java
            └── controller/
                ├── AdminControllerTest.java
                ├── StudentControllerTest.java
                ├── CompanyControllerTest.java
                ├── PlacementDriveControllerTest.java
                ├── ApplicationControllerTest.java
                └── DashboardControllerTest.java
```

---

## 6. How to Build & Run the Application

### Running with Maven Wrapper (Recommended)

**On Windows (PowerShell / Command Prompt):**
```powershell
# Navigate to the project root
cd placement-admin-service

# Run the Spring Boot application
.\mvnw.cmd spring-boot:run
```

**On Linux / macOS:**
```bash
chmod +x ./mvnw
./mvnw spring-boot:run
```

### Running Automated Tests
```powershell
.\mvnw.cmd clean test
```

When the application starts, it will listen on port **`8080`**.

---

## 7. Database Configuration & H2 Console

The service is pre-configured with an in-memory H2 database that initializes with sample data upon startup.

* **Database Type**: In-Memory H2
* **JDBC URL**: `jdbc:h2:mem:placementdb`
* **Driver Class**: `org.h2.Driver`
* **Username**: `sa`
* **Password**: *(Leave empty)*
* **H2 Web Console URL**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

### Accessing H2 Web Console:
1. Open your browser and navigate to `http://localhost:8080/h2-console`.
2. Ensure **JDBC URL** is set to `jdbc:h2:mem:placementdb`.
3. Set **User Name** to `sa` and leave **Password** blank.
4. Click **Connect** to inspect all tables (`ADMINS`, `STUDENTS`, `COMPANIES`, `PLACEMENT_DRIVES`, `APPLICATIONS`).

---

## 8. Pre-loaded Sample Data

Upon application boot, `DataInitializer` seeds realistic sample data:
* **3 Admins**: `admin@placement.edu` (password: `admin123`), `dean.placements@placement.edu`, `coordinator@placement.edu`
* **10 Students**: Diverse branches (CSE, IT, ECE, ME), CGPAs, skills, and roll numbers (`CSE001` to `CSE005`, `IT001`, `ECE001`, etc.)
* **5 Companies**: TechCorp Global, Innovate Cloud Labs, NextGen AI Dynamics, Apex Fintech Systems, CyberShield Solutions
* **5 Placement Drives**: Diverse roles (Associate Software Engineer, Cloud DevOps, ML Engineer, etc.)
* **15 Applications**: Realistic statuses (`APPLIED`, `SHORTLISTED`, `REJECTED`, `SELECTED`)

---

## 9. Complete REST API Reference Table

| Category | HTTP Method | Endpoint URL | Description |
| :--- | :--- | :--- | :--- |
| **Admin** | `POST` | `/api/admins/login` | Validate admin credentials |
| **Admin** | `POST` | `/api/admins` | Create a new admin |
| **Admin** | `GET` | `/api/admins` | Get all admins (passwords hidden) |
| **Admin** | `GET` | `/api/admins/{id}` | Get admin by ID |
| **Admin** | `PUT` | `/api/admins/{id}` | Update admin details |
| **Admin** | `DELETE` | `/api/admins/{id}` | Delete admin by ID |
| **Student** | `POST` | `/api/students` | Register a new student |
| **Student** | `GET` | `/api/students` | Get all students |
| **Student** | `GET` | `/api/students/{id}` | Get student by ID |
| **Student** | `PUT` | `/api/students/{id}` | Update student profile |
| **Student** | `DELETE` | `/api/students/{id}` | Delete student profile |
| **Student** | `GET` | `/api/students/search?name={name}` | Search students by name |
| **Student** | `GET` | `/api/students/department/{dept}` | Filter students by department |
| **Student** | `GET` | `/api/students/status/{status}` | Filter students by status (`PLACED`/`NOT_PLACED`) |
| **Company** | `POST` | `/api/companies` | Register a recruitment company |
| **Company** | `GET` | `/api/companies` | Get all companies |
| **Company** | `GET` | `/api/companies/{id}` | Get company by ID |
| **Company** | `PUT` | `/api/companies/{id}` | Update company details |
| **Company** | `DELETE` | `/api/companies/{id}` | Delete company |
| **Company** | `GET` | `/api/companies/status/{status}` | Filter companies by status (`ACTIVE`/`INACTIVE`) |
| **Company** | `GET` | `/api/companies/search?name={name}` | Search company by name |
| **Drives** | `POST` | `/api/drives` | Schedule a placement drive |
| **Drives** | `GET` | `/api/drives` | Get all placement drives |
| **Drives** | `GET` | `/api/drives/{id}` | Get drive by ID |
| **Drives** | `PUT` | `/api/drives/{id}` | Update placement drive |
| **Drives** | `DELETE` | `/api/drives/{id}` | Delete placement drive |
| **Drives** | `GET` | `/api/drives/status/{status}` | Filter drives by status (`OPEN`, `UPCOMING`, etc.) |
| **Drives** | `GET` | `/api/drives/company/{companyId}` | Get drives for a specific company |
| **Applications** | `POST` | `/api/applications` | Submit student application for drive |
| **Applications** | `GET` | `/api/applications` | Get all applications |
| **Applications** | `GET` | `/api/applications/{id}` | Get application by ID |
| **Applications** | `PUT` | `/api/applications/{id}` | Update status (`APPLIED`, `SHORTLISTED`, `SELECTED`, `REJECTED`) |
| **Applications** | `DELETE` | `/api/applications/{id}` | Delete application |
| **Applications** | `GET` | `/api/applications/student/{id}` | Get applications by student ID |
| **Applications** | `GET` | `/api/applications/drive/{id}` | Get applications by drive ID |
| **Applications** | `GET` | `/api/applications/status/{status}` | Filter applications by status |
| **Dashboard** | `GET` | `/api/admin/dashboard` | Dynamically calculated real-time placement statistics |

---

## 10. Sample Requests & Responses

### 1. Admin Login
* **`POST /api/admins/login`**
* **Request Body**:
```json
{
  "email": "admin@placement.edu",
  "password": "admin123"
}
```
* **Response Body (200 OK)**:
```json
{
  "message": "Login successful",
  "data": {
    "id": 1,
    "name": "Placement Officer",
    "email": "admin@placement.edu",
    "role": "ADMIN",
    "message": "Login successful"
  }
}
```

### 2. Create Student
* **`POST /api/students`**
* **Request Body**:
```json
{
  "name": "Manish Malhotra",
  "email": "manish.m@example.com",
  "phone": "+91 9811223344",
  "rollNumber": "CSE011",
  "department": "Computer Science",
  "course": "B.Tech",
  "graduationYear": 2026,
  "cgpa": 8.92,
  "skills": "Java, Spring Boot, React, Docker",
  "placementStatus": "NOT_PLACED"
}
```
* **Response Body (201 CREATED)**:
```json
{
  "message": "Student created successfully",
  "data": {
    "id": 11,
    "name": "Manish Malhotra",
    "email": "manish.m@example.com",
    "phone": "+91 9811223344",
    "rollNumber": "CSE011",
    "department": "Computer Science",
    "course": "B.Tech",
    "graduationYear": 2026,
    "cgpa": 8.92,
    "skills": "Java, Spring Boot, React, Docker",
    "placementStatus": "NOT_PLACED",
    "createdAt": "2026-09-13T18:00:00",
    "updatedAt": "2026-09-13T18:00:00"
  }
}
```

### 3. Create Placement Drive
* **`POST /api/drives`**
* **Request Body**:
```json
{
  "companyId": 1,
  "jobRole": "Full Stack Developer",
  "jobDescription": "Build scalable cloud applications using Java, Spring Boot, and React.",
  "eligibilityCriteria": "B.Tech in CSE / IT with minimum 7.5 CGPA and zero active backlogs",
  "minimumCGPA": 7.5,
  "package": 11.5,
  "location": "Bengaluru",
  "driveDate": "2026-10-15",
  "applicationDeadline": "2026-10-05",
  "status": "OPEN"
}
```

### 4. Update Application Status
* **`PUT /api/applications/1`**
* **Request Body**:
```json
{
  "status": "SELECTED",
  "remarks": "Candidate cleared all rounds. Offer letter issued."
}
```
*(Note: Automatically transitions student status to `PLACED`)*

### 5. Get Dashboard Analytics
* **`GET /api/admin/dashboard`**
* **Response Body (200 OK)**:
```json
{
  "totalStudents": 10,
  "totalCompanies": 5,
  "totalPlacementDrives": 5,
  "totalApplications": 15,
  "placedStudents": 4,
  "unplacedStudents": 6,
  "selectedApplications": 4,
  "rejectedApplications": 3,
  "shortlistedApplications": 4,
  "upcomingDrives": 1,
  "openDrives": 3
}
```

---

## 11. Postman Testing Instructions

A complete, pre-configured Postman collection is included in the project root:
**`Placement_Admin_Service_Postman_Collection.json`**

### Steps to Import & Execute:
1. Open **Postman**.
2. Click **Import** in the top-left corner.
3. Select or drag-and-drop `Placement_Admin_Service_Postman_Collection.json`.
4. The collection **Placement Admin Service API Collection** will appear with 6 organized folders:
   * `01 - Admin APIs`
   * `02 - Student APIs`
   * `03 - Company APIs`
   * `04 - Placement Drive APIs`
   * `05 - Application APIs`
   * `06 - Dashboard APIs`
5. The collection variable `baseUrl` is preset to `http://localhost:8080`.
6. Start testing any endpoint right away.

---

## 12. Security & Future Service Integration

### Current Development Architecture
* **Passwords**: Are securely handled via DTO separation (`password` is never included in response DTOs or logs).
* **CORS**: Enabled for frontend origins `http://localhost:3000` and `http://localhost:5173`.
* **Login**: Clean credential validation endpoint (`/api/admins/login`).

### Future Microservices Integration Plan

```
                   +-------------------------+
                   |       API GATEWAY       |
                   |  (Spring Cloud Gateway) |
                   +------------+------------+
                                |
         +----------------------+----------------------+
         |                      |                      |
         v                      v                      v
+-----------------+   +--------------------+   +-------------------+
|  LOGIN SERVICE  |   |   STUDENT SERVICE  |   |   ADMIN SERVICE   |
| (OAuth2 / JWT / |   |  (Student Portal / |   | (Placements /     |
| Spring Security)|   |   Resume Upload)   |   | Drives / Analytics|
+-----------------+   +--------------------+   +-------------------+
```

1. **Login Service Integration**:
   * Replace `/api/admins/login` with centralized OAuth2/JWT issuance in Login Service.
   * Add `spring-boot-starter-oauth2-resource-server` in Admin Service to validate incoming JWT Bearer tokens and enforce `@PreAuthorize("hasRole('ADMIN')")`.
2. **Student Service Integration**:
   * Synchronize student profiles via Kafka event streams or Spring Cloud OpenFeign client.
3. **Database Migration**:
   * Switch `spring.datasource.url` from H2 to PostgreSQL/MySQL by adding the corresponding driver in `pom.xml`.
