package com.university.demo.repositories;

import com.university.demo.model.Student;
import com.university.demo.types.StudentSearchDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query("""
        SELECT s FROM Student s
        WHERE ((:#{#studentSearchDTO.dateAdmissionStart} is NUll OR :#{#studentSearchDTO.dateAdmissionEnd} is NULl ) OR  s.dateOfAdmission BETWEEN :#{#studentSearchDTO.dateAdmissionStart} AND :#{#studentSearchDTO.dateAdmissionEnd})
        AND (:#{#studentSearchDTO.code} is NULL OR s.code = :#{#studentSearchDTO.code})
        AND (:#{#studentSearchDTO.identification} is NULL OR s.identification =  :#{#studentSearchDTO.identification})
        AND (:#{#studentSearchDTO.name} is  NULL OR s.firstName LIKE %:#{#studentSearchDTO.name}%)
        ORDER BY s.Id ASC""")
    List<Student> findByCodeNameDateAdmition(StudentSearchDTO studentSearchDTO);

}
