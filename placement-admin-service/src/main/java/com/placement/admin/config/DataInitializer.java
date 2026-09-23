package com.placement.admin.config;

import com.placement.admin.entity.*;
import com.placement.admin.entity.enums.*;
import com.placement.admin.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final PlacementDriveRepository placementDriveRepository;
    private final ApplicationRepository applicationRepository;

    public DataInitializer(AdminRepository adminRepository,
                           StudentRepository studentRepository,
                           CompanyRepository companyRepository,
                           PlacementDriveRepository placementDriveRepository,
                           ApplicationRepository applicationRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.placementDriveRepository = placementDriveRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (adminRepository.count() > 0) {
            logger.info("Database already seeded. Skipping initialization.");
            return;
        }

        logger.info("Initializing sample data for Placement Admin Service...");

        // 1. Seed 3 Admins
        Admin admin1 = new Admin(null, "Placement Officer", "admin@placement.edu", "admin123", "+91 9876543210", AdminRole.ADMIN, LocalDateTime.now());
        Admin admin2 = new Admin(null, "Dean Academic Placements", "dean.placements@placement.edu", "deanPass456", "+91 9876543211", AdminRole.ADMIN, LocalDateTime.now());
        Admin admin3 = new Admin(null, "Placement Coordinator", "coordinator@placement.edu", "coordPass789", "+91 9876543212", AdminRole.ADMIN, LocalDateTime.now());
        adminRepository.saveAll(Arrays.asList(admin1, admin2, admin3));
        logger.info("Seeded 3 Admins");

        // 2. Seed 10 Students
        Student s1 = new Student(null, "Rahul Kumar", "rahul.kumar@example.com", "+91 9123456780", "CSE001", "Computer Science", "B.Tech", 2026, 8.75, "Java, Spring Boot, React, MySQL", PlacementStatus.NOT_PLACED, LocalDateTime.now(), LocalDateTime.now());
        Student s2 = new Student(null, "Priya Sharma", "priya.sharma@example.com", "+91 9123456781", "CSE002", "Computer Science", "B.Tech", 2026, 9.20, "Python, Machine Learning, SQL, C++", PlacementStatus.NOT_PLACED, LocalDateTime.now(), LocalDateTime.now());
        Student s3 = new Student(null, "Amit Patel", "amit.patel@example.com", "+91 9123456782", "IT001", "Information Technology", "B.Tech", 2026, 7.80, "JavaScript, Node.js, MongoDB, Docker", PlacementStatus.NOT_PLACED, LocalDateTime.now(), LocalDateTime.now());
        Student s4 = new Student(null, "Sneha Reddy", "sneha.reddy@example.com", "+91 9123456783", "ECE001", "Electronics & Communication", "B.Tech", 2026, 8.45, "Embedded Systems, C, MATLAB, IoT", PlacementStatus.NOT_PLACED, LocalDateTime.now(), LocalDateTime.now());
        Student s5 = new Student(null, "Rohan Gupta", "rohan.gupta@example.com", "+91 9123456784", "CSE003", "Computer Science", "B.Tech", 2026, 6.90, "HTML, CSS, JavaScript, PHP", PlacementStatus.NOT_PLACED, LocalDateTime.now(), LocalDateTime.now());
        Student s6 = new Student(null, "Ananya Verma", "ananya.verma@example.com", "+91 9123456785", "IT002", "Information Technology", "B.Tech", 2026, 8.95, "Java, Kotlin, Android, AWS", PlacementStatus.NOT_PLACED, LocalDateTime.now(), LocalDateTime.now());
        Student s7 = new Student(null, "Vikram Singh", "vikram.singh@example.com", "+91 9123456786", "ME001", "Mechanical Engineering", "B.Tech", 2026, 7.50, "AutoCAD, SolidWorks, ANSYS, Python", PlacementStatus.NOT_PLACED, LocalDateTime.now(), LocalDateTime.now());
        Student s8 = new Student(null, "Pooja Nair", "pooja.nair@example.com", "+91 9123456787", "CSE004", "Computer Science", "B.Tech", 2026, 9.10, "Data Structures, Go, Kubernetes, Cloud", PlacementStatus.NOT_PLACED, LocalDateTime.now(), LocalDateTime.now());
        Student s9 = new Student(null, "Karthik Iyer", "karthik.iyer@example.com", "+91 9123456788", "ECE002", "Electronics & Communication", "B.Tech", 2026, 8.15, "VLSI, Verilog, Python, C++", PlacementStatus.NOT_PLACED, LocalDateTime.now(), LocalDateTime.now());
        Student s10 = new Student(null, "Divya Joshi", "divya.joshi@example.com", "+91 9123456789", "CSE005", "Computer Science", "B.Tech", 2026, 8.60, "Java, Microservices, Spring Cloud, PostgreSQL", PlacementStatus.NOT_PLACED, LocalDateTime.now(), LocalDateTime.now());

        List<Student> students = studentRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10));
        logger.info("Seeded 10 Students");

        // 3. Seed 5 Companies
        Company c1 = new Company(null, "TechCorp Global", "careers@techcorp.com", "+91 8011223344", "https://techcorp.com", "Information Technology", "Bengaluru, Karnataka", "Leading provider of digital transformation and enterprise software solutions.", CompanyStatus.ACTIVE, LocalDateTime.now());
        Company c2 = new Company(null, "Innovate Cloud Labs", "jobs@innovatecloud.com", "+91 8022334455", "https://innovatecloud.com", "Cloud & SaaS", "Hyderabad, Telangana", "Premier cloud migration, SaaS product development, and multi-cloud solutions provider.", CompanyStatus.ACTIVE, LocalDateTime.now());
        Company c3 = new Company(null, "NextGen AI Dynamics", "talent@nextgenai.com", "+91 8033445566", "https://nextgenai.com", "Artificial Intelligence", "Pune, Maharashtra", "Frontier AI and generative modeling engineering laboratory and products.", CompanyStatus.ACTIVE, LocalDateTime.now());
        Company c4 = new Company(null, "Apex Fintech Systems", "recruiting@apexfintech.com", "+91 8044556677", "https://apexfintech.com", "Financial Technology", "Mumbai, Maharashtra", "High-frequency trading architectures and secure decentralized payment gateways.", CompanyStatus.ACTIVE, LocalDateTime.now());
        Company c5 = new Company(null, "CyberShield Solutions", "hr@cybershield.com", "+91 8055667788", "https://cybershield.com", "Cybersecurity", "Noida, Uttar Pradesh", "Enterprise security compliance, threat intelligence, and zero-trust engineering.", CompanyStatus.INACTIVE, LocalDateTime.now());

        List<Company> companies = companyRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
        logger.info("Seeded 5 Companies");

        // 4. Seed 5 Placement Drives
        PlacementDrive d1 = new PlacementDrive(null, c1, "Associate Software Engineer", "Develop scalable backend microservices using Java and Spring Boot.", "B.Tech (CSE/IT) with no active backlogs", 7.0, 8.5, "Bengaluru", LocalDate.now().plusDays(20), LocalDate.now().plusDays(10), DriveStatus.OPEN, LocalDateTime.now(), LocalDateTime.now());
        PlacementDrive d2 = new PlacementDrive(null, c2, "Cloud DevOps Trainee", "Design and maintain CI/CD pipelines, Docker containers, and Kubernetes clusters.", "B.Tech (All Branches) with Linux fundamentals", 7.5, 10.0, "Hyderabad", LocalDate.now().plusDays(30), LocalDate.now().plusDays(15), DriveStatus.OPEN, LocalDateTime.now(), LocalDateTime.now());
        PlacementDrive d3 = new PlacementDrive(null, c3, "Machine Learning Engineer", "Build and optimize computer vision and NLP deep learning models.", "B.Tech (CSE/IT/ECE) with strong Python background", 8.5, 16.0, "Pune", LocalDate.now().plusDays(45), LocalDate.now().plusDays(25), DriveStatus.UPCOMING, LocalDateTime.now(), LocalDateTime.now());
        PlacementDrive d4 = new PlacementDrive(null, c4, "Backend Systems Engineer", "Implement high throughput financial ledger systems with low latency.", "B.Tech (CSE/IT) with strong Data Structures knowledge", 8.0, 14.0, "Mumbai", LocalDate.now().plusDays(10), LocalDate.now().plusDays(2), DriveStatus.OPEN, LocalDateTime.now(), LocalDateTime.now());
        PlacementDrive d5 = new PlacementDrive(null, c1, "QA Automation Engineer", "Develop test automation frameworks using Selenium and RestAssured.", "B.Tech (All Branches)", 6.5, 6.0, "Bengaluru", LocalDate.now().minusDays(15), LocalDate.now().minusDays(25), DriveStatus.COMPLETED, LocalDateTime.now(), LocalDateTime.now());

        List<PlacementDrive> drives = placementDriveRepository.saveAll(Arrays.asList(d1, d2, d3, d4, d5));
        logger.info("Seeded 5 Placement Drives");

        // 5. Seed 15 Applications
        // Student 1 (Rahul)
        Application a1 = new Application(null, students.get(0), drives.get(0), LocalDate.now().minusDays(5), ApplicationStatus.SHORTLISTED, "Cleared round 1 technical assessment", LocalDateTime.now(), LocalDateTime.now());
        Application a2 = new Application(null, students.get(0), drives.get(1), LocalDate.now().minusDays(4), ApplicationStatus.APPLIED, "Application submitted", LocalDateTime.now(), LocalDateTime.now());

        // Student 2 (Priya)
        Application a3 = new Application(null, students.get(1), drives.get(2), LocalDate.now().minusDays(3), ApplicationStatus.SELECTED, "Offered role of ML Engineer with 16 LPA", LocalDateTime.now(), LocalDateTime.now());
        Application a4 = new Application(null, students.get(1), drives.get(0), LocalDate.now().minusDays(5), ApplicationStatus.SHORTLISTED, "Shortlisted for HR round", LocalDateTime.now(), LocalDateTime.now());

        // Student 3 (Amit)
        Application a5 = new Application(null, students.get(2), drives.get(0), LocalDate.now().minusDays(5), ApplicationStatus.REJECTED, "Did not meet coding cutoff in Round 1", LocalDateTime.now(), LocalDateTime.now());
        Application a6 = new Application(null, students.get(2), drives.get(1), LocalDate.now().minusDays(4), ApplicationStatus.SHORTLISTED, "Cleared technical interview", LocalDateTime.now(), LocalDateTime.now());

        // Student 4 (Sneha)
        Application a7 = new Application(null, students.get(3), drives.get(1), LocalDate.now().minusDays(3), ApplicationStatus.APPLIED, "Application under review", LocalDateTime.now(), LocalDateTime.now());

        // Student 5 (Rohan)
        Application a8 = new Application(null, students.get(4), drives.get(4), LocalDate.now().minusDays(20), ApplicationStatus.REJECTED, "Aptitude test score below minimum threshold", LocalDateTime.now(), LocalDateTime.now());

        // Student 6 (Ananya)
        Application a9 = new Application(null, students.get(5), drives.get(0), LocalDate.now().minusDays(5), ApplicationStatus.SELECTED, "Selected as Associate Software Engineer", LocalDateTime.now(), LocalDateTime.now());
        Application a10 = new Application(null, students.get(5), drives.get(3), LocalDate.now().minusDays(2), ApplicationStatus.SHORTLISTED, "Cleared online coding test", LocalDateTime.now(), LocalDateTime.now());

        // Student 7 (Vikram)
        Application a11 = new Application(null, students.get(6), drives.get(4), LocalDate.now().minusDays(20), ApplicationStatus.REJECTED, "Interview performance unsatisfactory", LocalDateTime.now(), LocalDateTime.now());

        // Student 8 (Pooja)
        Application a12 = new Application(null, students.get(7), drives.get(2), LocalDate.now().minusDays(3), ApplicationStatus.SELECTED, "Selected with exemplary technical score", LocalDateTime.now(), LocalDateTime.now());
        Application a13 = new Application(null, students.get(7), drives.get(3), LocalDate.now().minusDays(2), ApplicationStatus.APPLIED, "Application received", LocalDateTime.now(), LocalDateTime.now());

        // Student 9 (Karthik)
        Application a14 = new Application(null, students.get(8), drives.get(1), LocalDate.now().minusDays(3), ApplicationStatus.APPLIED, "Profile under review", LocalDateTime.now(), LocalDateTime.now());

        // Student 10 (Divya)
        Application a15 = new Application(null, students.get(9), drives.get(0), LocalDate.now().minusDays(5), ApplicationStatus.SELECTED, "Offer letter released", LocalDateTime.now(), LocalDateTime.now());

        applicationRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15));
        logger.info("Seeded 15 Applications");

        // Sync placement status for selected students (Priya, Ananya, Pooja, Divya)
        students.get(1).setPlacementStatus(PlacementStatus.PLACED);
        students.get(5).setPlacementStatus(PlacementStatus.PLACED);
        students.get(7).setPlacementStatus(PlacementStatus.PLACED);
        students.get(9).setPlacementStatus(PlacementStatus.PLACED);
        studentRepository.saveAll(Arrays.asList(students.get(1), students.get(5), students.get(7), students.get(9)));

        logger.info("Sample data initialization completed successfully!");
    }
}
