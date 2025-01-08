package tn.home.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.home.student.dtto.request.StudentRequest;
import tn.home.student.client.AddressFeignClient;
import tn.home.student.dto.response.StudentResponse;
import tn.home.student.entities.Student;
import tn.home.student.repositories.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressFeignClient addressFeignClient; 


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setAddressId(studentRequest.getAddressId());

        Student savedStudent = studentRepository.save(student);
        return new StudentResponse(
                savedStudent.getId(),
                savedStudent.getFirstName(),
                savedStudent.getLastName(),
                savedStudent.getEmail(),
                savedStudent.getAddressId()
        );
    }

    @Override
    public StudentResponse getStudentResponseById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getAddressId()
        );
    }
    @Override
    public Map<String, Object> getStudentDetails(Long id) {
        // Récupérer les informations de l'étudiant
        StudentResponse student = getStudentResponseById(id);

        // Récupérer les informations de l'adresse via Feign
        Object address = null;
        try {
            address = addressFeignClient.getAddressById(student.getAddressId());
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération de l'adresse : " + e.getMessage());
        }

        // Construire une réponse sous forme de Map
        Map<String, Object> response = new HashMap<>();
        response.put("studentDetails", student);
        response.put("addressDetails", address);

        return response;
    }


    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setAddressId(studentRequest.getAddressId());

        Student updatedStudent = studentRepository.save(student);
        return new StudentResponse(
                updatedStudent.getId(),
                updatedStudent.getFirstName(),
                updatedStudent.getLastName(),
                updatedStudent.getEmail(),
                updatedStudent.getAddressId()
        );
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
