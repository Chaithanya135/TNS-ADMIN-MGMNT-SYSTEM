package com.placement.admin.service;

import com.placement.admin.dto.student.StudentRequestDTO;
import com.placement.admin.dto.student.StudentResponseDTO;
import com.placement.admin.entity.enums.PlacementStatus;

import java.util.List;

public interface StudentService {

    StudentResponseDTO createStudent(StudentRequestDTO requestDTO);

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudentById(Long id);

    StudentResponseDTO updateStudent(Long id, StudentRequestDTO requestDTO);

    void deleteStudent(Long id);

    List<StudentResponseDTO> searchStudentsByName(String name);

    List<StudentResponseDTO> getStudentsByDepartment(String department);

    List<StudentResponseDTO> getStudentsByStatus(PlacementStatus status);
}
