package com.university.demo.repositories;

import com.university.demo.model.TypeIdentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeIdentificationRepository extends JpaRepository<TypeIdentification, Long> {
}
