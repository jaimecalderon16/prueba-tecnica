package com.university.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "type_identification")
public class TypeIdentification {
    @Id
    private Long id;
    private String name;

}
