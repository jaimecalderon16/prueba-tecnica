package com.university.demo.controller;

import com.university.demo.repositories.StudentGradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentGradeController {

    @Autowired
    StudentGradesRepository studentGradesRepository;


}
