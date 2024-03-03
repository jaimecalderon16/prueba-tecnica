package com.university.demo.types;

import java.time.LocalDate;

public class StudentSearchDTO {

    private String code;
    private String identification;
    private String name;
    private LocalDate dateAdmissionStart;
    private LocalDate dateAdmissionEnd;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateAdmissionStart() {
        return dateAdmissionStart;
    }

    public void setDateAdmissionStart(LocalDate dateAdmissionStart) {
        this.dateAdmissionStart = dateAdmissionStart;
    }

    public LocalDate getDateAdmissionEnd() {
        return dateAdmissionEnd;
    }

    public void setDateAdmissionEnd(LocalDate dateAdmissionEnd) {
        this.dateAdmissionEnd = dateAdmissionEnd;
    }
}
