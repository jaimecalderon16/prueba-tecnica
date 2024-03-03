package com.university.demo.types;

import com.university.demo.model.Student;
import com.university.demo.model.StudentGrades;

import java.util.List;

public class StudentUpdateRequest {
    Student student;
    List<StudentGrades> grades;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<StudentGrades> getGrades() {
        return grades;
    }

    public void setGrades(List<StudentGrades> grades) {
        this.grades = grades;
    }
}
