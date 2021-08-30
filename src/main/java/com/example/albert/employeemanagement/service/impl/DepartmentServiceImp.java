package com.example.albert.employeemanagement.service.impl;

import com.example.albert.employeemanagement.datalayer.Department;
import com.example.albert.employeemanagement.exception.ResourceExistsException;
import com.example.albert.employeemanagement.exception.ResourceNotFoundException;
import com.example.albert.employeemanagement.repository.DepartmentRepository;
import com.example.albert.employeemanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public ResponseEntity<Department> createDepartment(Department department) {
        if (departmentRepository.existsDepartmentByDepartmentId(department.getDepartmentId())) {
            throw new ResourceExistsException("Department already exist");
        } else if (departmentRepository.existsDepartmentByDepartmentName(department.getDepartmentName())) {
            throw new ResourceExistsException("Department name already exist");
        } else if (departmentRepository.existsDepartmentByDepartmentCode(department.getDepartmentCode())) {
            throw new ResourceExistsException("Department code already exist");
        }
        department.setDepartmentId(UUID.randomUUID().toString());
        department.setInTrash(0);
        Department newDepartment = departmentRepository.save(department);
        return new ResponseEntity<Department>(newDepartment, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> dept = departmentRepository.findAll();
        return new ResponseEntity<List<Department>>(dept, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Department> getDepartmentById(String departmentId) {
        if (!departmentRepository.existsDepartmentByDepartmentId(departmentId)) {
            throw new ResourceNotFoundException("Department not found");

        }
        Department department = departmentRepository.getDepartmentByDepartmentId(departmentId);
        return new ResponseEntity<Department>(department, HttpStatus.OK);
    }

    @Override
    public Department searchDepartment(String departmentName, String departmentCode) {
        if (!departmentRepository.existsDepartmentByDepartmentName(departmentName)
                && !departmentRepository.existsDepartmentByDepartmentCode(departmentCode)) {
            throw new ResourceNotFoundException("Record Not found");
        }
        return departmentRepository.getDepartmentByDepartmentNameOrDepartmentCode
                (departmentName, departmentCode);
    }

    @Override
    public Department updateDepartment(String departmentId, Department department) {
        if (!departmentRepository.existsDepartmentByDepartmentId(departmentId)) {
            throw new ResourceNotFoundException("Department not found");
        }
        Department dep = departmentRepository.getDepartmentByDepartmentId(departmentId);
        dep.setDepartmentCode(department.getDepartmentCode());
        dep.setDepartmentName(department.getDepartmentName());
        Department savedDep = departmentRepository.save(dep);
        return savedDep;
    }

}
