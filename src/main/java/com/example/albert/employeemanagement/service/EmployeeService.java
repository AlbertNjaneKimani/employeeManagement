package com.example.albert.employeemanagement.service;

import com.example.albert.employeemanagement.datalayer.Employees;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public Object createEmployee(Employees employees);

    public List<Employees> getAllEmployees();

    public Optional<Employees> getSingleEmployee(String employeeId);

    public Optional<Employees> getEmployeeByPhoneNumber(String phoneNumber);

    public Optional<Employees> getEmployeeByNationalId(String nationalId);

    public Employees updateEmployee(String employeeId, Employees employees);

    public String removeEmployee(String employeeId, Employees employees);
}
