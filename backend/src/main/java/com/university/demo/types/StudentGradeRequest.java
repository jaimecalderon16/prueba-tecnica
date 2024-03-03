package com.university.demo.types;

import lombok.Data;

@Data
public class StudentGradeRequest {
    private String courseName;
    private int grade;
}
