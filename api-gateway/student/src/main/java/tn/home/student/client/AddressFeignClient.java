package tn.home.student.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import tn.home.student.dto.response.StudentResponse;
import tn.home.student.dtto.request.StudentRequest;

@FeignClient(value = "api-gateway", path = "/address-service/api/address")
public interface AddressFeignClient {

    
    @GetMapping("/{id}")
    Object getAddressById(@PathVariable Long id);

    @GetMapping("/student-service/api/students/{id}")
    StudentResponse getStudentById(@PathVariable Long id);

    @GetMapping("/student-service/api/students")
    List<StudentResponse> getAllStudents();

    @PostMapping(value = "/student-service/api/students", consumes = "application/json")
    StudentResponse createStudent(@RequestBody StudentRequest studentRequest);

    @PutMapping(value = "/student-service/api/students/{id}", consumes = "application/json")
    StudentResponse updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest);

    @DeleteMapping("/student-service/api/students/{id}")
    void deleteStudent(@PathVariable Long id);
    
}
