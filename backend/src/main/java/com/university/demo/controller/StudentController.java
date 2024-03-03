package com.university.demo.controller;

import com.university.demo.model.Student;
import com.university.demo.model.StudentGrades;
import com.university.demo.repositories.StudentGradesRepository;
import com.university.demo.services.StudentService;
import com.university.demo.types.StudentGradeRequest;
import com.university.demo.types.StudentRequest;
import com.university.demo.types.StudentSearchDTO;
import com.university.demo.types.StudentUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    StudentGradesRepository studentGradesRepository;


    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody StudentRequest studentRequest) {
        // Obtén los datos del estudiante desde el objeto StudentRequest
        Student studentData = studentRequest.getUserData();

        // Guarda el estudiante
        Student createdStudent = studentService.addStudent(studentData);

        // Obtén las calificaciones desde el objeto StudentRequest
        List<StudentGradeRequest> gradeRequests = studentRequest.getGrades();

        // Guarda las calificaciones asociadas al estudiante creado
        for (StudentGradeRequest gradeRequest : gradeRequests) {
            StudentGrades studentGrade = new StudentGrades();
            studentGrade.setCourseName(gradeRequest.getCourseName());
            studentGrade.setGrade(gradeRequest.getGrade());
            studentGrade.setStudent(createdStudent);

            studentGradesRepository.save(studentGrade);
        }

        return ResponseEntity.ok("Estudiante y calificaciones creados exitosamente.");
    }


    @GetMapping("/getStudents")
    public List<Student> showAllStudent() {
        return studentService.showAllStudent();
    }

    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }


    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentUpdateRequest studentUpdateRequest) {
        try {

            var student = studentService.updateStudent(id, studentUpdateRequest.getStudent());

            var gradeRequests = studentUpdateRequest.getGrades();

            for (StudentGrades gradeRequest : gradeRequests) {

                gradeRequest.setStudent(student);

                studentGradesRepository.save(gradeRequest);
            }


            return new ResponseEntity<>("Registro Actualizado", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error en la actualización: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }


    @PostMapping("/searchStudents")
    public ResponseEntity<?> searchStudents(@RequestBody StudentSearchDTO studentSearchDTO) {
        try {
            List<Student> students = studentService.searchStudents(studentSearchDTO);
            return ResponseEntity.status(HttpStatus.OK).body(students);
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
