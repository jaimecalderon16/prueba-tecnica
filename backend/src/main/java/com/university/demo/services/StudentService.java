package com.university.demo.services;

import com.university.demo.model.Student;
import com.university.demo.repositories.StudentRepository;
import com.university.demo.repositories.TypeIdentificationRepository;
import com.university.demo.types.StudentSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TypeIdentificationRepository typeIdentificationRepository;

    public Student addStudent(Student student){

        return studentRepository.save(student);
    }


    public List<Student> showAllStudent(){
        return studentRepository.findAll();
    }

    public ResponseEntity<Student> getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.map(student -> ResponseEntity.ok().body(student))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    public Student updateStudent(Long id, Student updatedStudent) {

            Optional<Student> optionalStudent = studentRepository.findById(id);


            Student existingStudent = optionalStudent.get();

            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setPhone(updatedStudent.getPhone());
            existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
            existingStudent.setDateOfAdmission(updatedStudent.getDateOfAdmission());
            existingStudent.setAddress(updatedStudent.getAddress());
            existingStudent.setIdentification(updatedStudent.getIdentification());
            existingStudent.setTypeIdentification(updatedStudent.getTypeIdentification());

            return studentRepository.save(existingStudent);


    }

    public ResponseEntity<String> deleteStudent(Long id) {
        try {
            Optional<Student> optionalStudent = studentRepository.findById(id);

            if (optionalStudent.isPresent()) {
                studentRepository.deleteById(id);
                return new ResponseEntity<>("Registro eliminado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Registro no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar estudiante: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Student> searchStudents(StudentSearchDTO searchDTO) {
        return studentRepository.findByCodeNameDateAdmition(searchDTO);  // Pass the searchDTO argument
    }
}
