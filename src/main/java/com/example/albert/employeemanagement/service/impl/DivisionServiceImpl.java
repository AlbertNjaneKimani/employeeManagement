package com.example.albert.employeemanagement.service.impl;

import com.example.albert.employeemanagement.datalayer.Department;
import com.example.albert.employeemanagement.datalayer.Division;
import com.example.albert.employeemanagement.datalayer.DivisionRequest;
import com.example.albert.employeemanagement.exception.ResourceExistsException;
import com.example.albert.employeemanagement.exception.ResourceNotFoundException;
import com.example.albert.employeemanagement.repository.DepartmentRepository;
import com.example.albert.employeemanagement.repository.DivisionRepository;
import com.example.albert.employeemanagement.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DivisionServiceImpl implements DivisionService {
    @Autowired
    private DivisionRepository divisionRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public ResponseEntity<Division> addDivision(String departmentId, Division division) {
        if (divisionRepository.existsDivisionByDivisionName(division.getDivisionName())) {
            throw new ResourceExistsException("Division with name " + division.getDivisionName() + " already exists");
        }
        if (divisionRepository.existsDivisionByDivisionCode(division.getDivisionCode())) {
            throw new ResourceExistsException("Division with code " + division.getDivisionCode() + " already exists");
        }
        Department department = departmentRepository.findDepartmentByDepartmentId(departmentId);
        division.setDivisionId(UUID.randomUUID().toString());
        division.setInTrash(0);
        division.setDepartment(department);
        Division savedDivision = divisionRepository.save(division);
        return new ResponseEntity<Division>(savedDivision, HttpStatus.OK);
    }

    @Override
    public List<Division> findAllDivision() {
        List<Division> div = divisionRepository.findDivisionByInTrash(0);
        return div;
    }

    @Override
    public Division getDivisionByDivisionId(String divisionId) {
        if (!divisionRepository.existsDivisionByDivisionId(divisionId)) {
            throw new ResourceNotFoundException("Division record not found");
        }
        Division foundDivision = divisionRepository.findDivisionByDivisionIdAndInTrash(divisionId, 0);
        return foundDivision;
    }

    @Override
    public Division searchByDivisionNameAndCode(String divisionName, String divisionCode) {
        if (!divisionRepository.existsDivisionByDivisionName(divisionName)
                && !divisionRepository.existsDivisionByDivisionCode(divisionCode)) {
            throw new ResourceNotFoundException("Record Not found");
        }
        return divisionRepository.getDivisionByDivisionNameOrDivisionCode(divisionName, divisionCode);
    }

    @Override
    public Division updateDivision(String divisionId, Division division) {
        if (!divisionRepository.existsDivisionByDivisionId(divisionId)) {
            throw new ResourceNotFoundException("Division record not found");
        }
        Division div = divisionRepository.findDivisionByDivisionIdAndInTrash(divisionId, 0);
        div.setDivisionCode(division.getDivisionCode());
        div.setDivisionName(division.getDivisionName());
        return divisionRepository.save(div);
    }

    @Override
    public String deleteDivision(String divisionId) {
        if (!divisionRepository.existsDivisionByDivisionId(divisionId)) {
            throw new ResourceNotFoundException("Resource not found");
        }

        else if (divisionRepository.existsDivisionByInTrash(1)) {
            throw new ResourceNotFoundException("Division is already deactivated");
        }
        Division div = divisionRepository.findDivisionByDivisionIdAndInTrash(divisionId, 0);
        div.setInTrash(1);
         divisionRepository.save(div);
        return "Division Deleted succcesfully";

    }
}