package tn.home.student.service;

import tn.home.student.dtto.request.StudentRequest;
import tn.home.student.dto.response.StudentResponse;
import tn.home.student.entities.Student;

import java.util.List;

public interface StudentService {
    // Retrieve all students
    List<Student> getAllStudents();

    StudentResponse createStudent(StudentRequest studentRequest);
    StudentResponse getStudentResponseById(Long id);
    Object getStudentDetails(Long id);
    StudentResponse updateStudent(Long id, StudentRequest studentRequest);
    void deleteStudent(Long id);
}
