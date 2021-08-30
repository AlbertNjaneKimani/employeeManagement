package com.example.albert.employeemanagement.service;

import com.example.albert.employeemanagement.datalayer.Department;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {
    public ResponseEntity<Department> createDepartment(Department department);

    public ResponseEntity<List<Department>> getAllDepartments();

    public ResponseEntity<Department> getDepartmentById(String departmentId);

    public Department searchDepartment(String departmentName, String departmentCode);

    public Department updateDepartment(String departmentId,Department department);
}
