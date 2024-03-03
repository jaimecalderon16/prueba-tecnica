package com.university.demo.types;

import com.university.demo.model.Student;
import lombok.Data;

import java.util.List;

@Data
public class StudentRequest {
    private Student userData;
    private List<StudentGradeRequest> grades;
}
