package com.placement.admin.controller;

import com.placement.admin.dto.common.ApiResponse;
import com.placement.admin.dto.student.StudentRequestDTO;
import com.placement.admin.dto.student.StudentResponseDTO;
import com.placement.admin.entity.enums.PlacementStatus;
import com.placement.admin.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponseDTO>> createStudent(@Valid @RequestBody StudentRequestDTO requestDTO) {
        StudentResponseDTO createdStudent = studentService.createStudent(requestDTO);
        return new ResponseEntity<>(
                ApiResponse.of("Student created successfully", createdStudent),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponseDTO>>> getAllStudents() {
        List<StudentResponseDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(ApiResponse.of("Students retrieved successfully", students));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> getStudentById(@PathVariable Long id) {
        StudentResponseDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(ApiResponse.of("Student retrieved successfully", student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO requestDTO) {
        StudentResponseDTO updatedStudent = studentService.updateStudent(id, requestDTO);
        return ResponseEntity.ok(ApiResponse.of("Student updated successfully", updatedStudent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StudentResponseDTO>>> searchStudentsByName(
            @RequestParam(required = false, defaultValue = "") String name) {
        List<StudentResponseDTO> students = studentService.searchStudentsByName(name);
        return ResponseEntity.ok(ApiResponse.of("Students found successfully", students));
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<ApiResponse<List<StudentResponseDTO>>> getStudentsByDepartment(
            @PathVariable String department) {
        List<StudentResponseDTO> students = studentService.getStudentsByDepartment(department);
        return ResponseEntity.ok(ApiResponse.of("Students retrieved for department: " + department, students));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<StudentResponseDTO>>> getStudentsByStatus(
            @PathVariable PlacementStatus status) {
        List<StudentResponseDTO> students = studentService.getStudentsByStatus(status);
        return ResponseEntity.ok(ApiResponse.of("Students retrieved for status: " + status, students));
    }
}
