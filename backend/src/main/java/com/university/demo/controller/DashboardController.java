package com.university.demo.controller;

import com.university.demo.model.Student;
import com.university.demo.model.TypeIdentification;
import com.university.demo.repositories.TypeIdentificationRepository;
import com.university.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class DashboardController {

    @Autowired
    StudentService studentService;


    @Autowired
    TypeIdentificationRepository TypeIdentificationRepository;



    @GetMapping("/home")
    public ResponseEntity<Map<String, Object>>  addStudent(){
        List<Student> listStudents= studentService.showAllStudent();
        List<TypeIdentification> listTypeIdentification = TypeIdentificationRepository.findAll();

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("list_students", listStudents);
        responseData.put("list_type_identification", listTypeIdentification);

        return ResponseEntity.ok(responseData);
    }

}
