package com.example.albert.employeemanagement.service;

import com.example.albert.employeemanagement.datalayer.Division;
import com.example.albert.employeemanagement.datalayer.DivisionRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DivisionService {
    public ResponseEntity<Division> addDivision(String departmentId, Division division);

    public List<Division> findAllDivision();

    public Division getDivisionByDivisionId(String divisionId);

    public Division searchByDivisionNameAndCode(String divisionName, String divisionCode);

    public Division updateDivision(String divisionId, Division division);

    public String deleteDivision(String divisionId);
}
