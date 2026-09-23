package com.placement.admin.service.impl;

import com.placement.admin.dto.student.StudentRequestDTO;
import com.placement.admin.dto.student.StudentResponseDTO;
import com.placement.admin.entity.Student;
import com.placement.admin.entity.enums.PlacementStatus;
import com.placement.admin.exception.DuplicateResourceException;
import com.placement.admin.exception.ResourceNotFoundException;
import com.placement.admin.repository.StudentRepository;
import com.placement.admin.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO requestDTO) {
        if (studentRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException("Student with email " + requestDTO.getEmail() + " already exists");
        }
        if (studentRepository.existsByRollNumber(requestDTO.getRollNumber())) {
            throw new DuplicateResourceException("Student with roll number " + requestDTO.getRollNumber() + " already exists");
        }

        Student student = new Student();
        student.setName(requestDTO.getName());
        student.setEmail(requestDTO.getEmail());
        student.setPhone(requestDTO.getPhone());
        student.setRollNumber(requestDTO.getRollNumber());
        student.setDepartment(requestDTO.getDepartment());
        student.setCourse(requestDTO.getCourse());
        student.setGraduationYear(requestDTO.getGraduationYear());
        student.setCgpa(requestDTO.getCgpa());
        student.setSkills(requestDTO.getSkills());
        student.setPlacementStatus(requestDTO.getPlacementStatus() != null ? requestDTO.getPlacementStatus() : PlacementStatus.NOT_PLACED);

        Student savedStudent = studentRepository.save(student);
        return mapToResponseDTO(savedStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found"));
        return mapToResponseDTO(student);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO requestDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found"));

        if (studentRepository.existsByEmailAndIdNot(requestDTO.getEmail(), id)) {
            throw new DuplicateResourceException("Student with email " + requestDTO.getEmail() + " already exists");
        }
        if (studentRepository.existsByRollNumberAndIdNot(requestDTO.getRollNumber(), id)) {
            throw new DuplicateResourceException("Student with roll number " + requestDTO.getRollNumber() + " already exists");
        }

        student.setName(requestDTO.getName());
        student.setEmail(requestDTO.getEmail());
        student.setPhone(requestDTO.getPhone());
        student.setRollNumber(requestDTO.getRollNumber());
        student.setDepartment(requestDTO.getDepartment());
        student.setCourse(requestDTO.getCourse());
        student.setGraduationYear(requestDTO.getGraduationYear());
        student.setCgpa(requestDTO.getCgpa());
        student.setSkills(requestDTO.getSkills());
        if (requestDTO.getPlacementStatus() != null) {
            student.setPlacementStatus(requestDTO.getPlacementStatus());
        }

        Student updatedStudent = studentRepository.save(student);
        return mapToResponseDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student with ID " + id + " not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getStudentsByDepartment(String department) {
        return studentRepository.findByDepartmentIgnoreCase(department)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getStudentsByStatus(PlacementStatus status) {
        return studentRepository.findByPlacementStatus(status)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private StudentResponseDTO mapToResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getRollNumber(),
                student.getDepartment(),
                student.getCourse(),
                student.getGraduationYear(),
                student.getCgpa(),
                student.getSkills(),
                student.getPlacementStatus(),
                student.getCreatedAt(),
                student.getUpdatedAt()
        );
    }
}
