package com.example.albert.employeemanagement.service.impl;

import com.example.albert.employeemanagement.datalayer.Employees;
import com.example.albert.employeemanagement.exception.ResourceExistsException;
import com.example.albert.employeemanagement.exception.ResourceNotFoundException;
import com.example.albert.employeemanagement.repository.EmployeeRepository;
import com.example.albert.employeemanagement.service.EmployeeService;
import com.example.albert.employeemanagement.utils.EmployeesVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<Employees> createEmployee(Employees employees) {
        if (employeeRepository.existsEmployeesByEmail
                (employees.getEmail())) {
            throw new ResourceExistsException("Emails  " + employees.getEmail() + " already exists");
        } else if (employeeRepository.existsEmployeesByPhoneNumber
                (employees.getPhoneNumber())) {
            throw new ResourceExistsException("Phone number " + employees.getPhoneNumber() + " already exists");
        } else if (employeeRepository.existsEmployeesByNationalId
                (employees.getNationalId())) {
            throw new ResourceExistsException("National Id " + employees.getNationalId() + " already exists");
        }
        employees.setEmployeeId(UUID.randomUUID().toString());
        employees.setEmployeeStatus(1);
        Employees emp = employeeRepository.save(employees);

        return new ResponseEntity<Employees>(emp, HttpStatus.OK);
    }

    @Override
    public List<Employees> getAllEmployees() {
        List<Employees> fetchedEmployees = employeeRepository.findAllByEmployeeStatus(EmployeesVariables.EMPLOYEE_STATUS);
        return fetchedEmployees;
    }

    @Override
    public Optional<Employees> getSingleEmployee(String employeeId) {
        Optional<Employees> emp = employeeRepository.findEmployeesByEmployeeIdAndEmployeeStatus(employeeId,
                EmployeesVariables.EMPLOYEE_STATUS);
        if (!emp.isPresent()) {
            throw new ResourceNotFoundException("Employee Not Found");
        }
        return emp;
    }

    @Override
    public Optional<Employees> getEmployeeByPhoneNumber(String phoneNumber) {
        Optional<Employees> emp = employeeRepository.findEmployeesByPhoneNumberAndEmployeeStatus(phoneNumber,
                EmployeesVariables.EMPLOYEE_STATUS);
        if (!emp.isPresent()) {
            throw new ResourceNotFoundException("Employee with phone number " + phoneNumber + " Not Found");
        }
        return emp;
    }

    @Override
    public Optional<Employees> getEmployeeByNationalId(String nationalId) {
        Optional<Employees> empId = employeeRepository.findEmployeesByNationalIdAndEmployeeStatus(nationalId,
                EmployeesVariables.EMPLOYEE_STATUS);
        if (!empId.isPresent()) {
            throw new ResourceNotFoundException("Employee with National Id " + nationalId + " Not Found");
        }
        return empId;
    }

    @Override
    public Employees updateEmployee(String employeeId, Employees employees) {
        if (!employeeRepository.existsEmployeesByEmployeeId(employeeId)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        Employees employee = employeeRepository.findEmployeesByEmployeeStatusAndEmployeeId
                (EmployeesVariables.EMPLOYEE_STATUS, employeeId);
        employee.setFirstName(employees.getFirstName());
        employee.setLastName(employees.getLastName());
        employee.setMiddleName(employees.getMiddleName());
        employee.setAddress(employees.getAddress());
        employee.setCity(employees.getCity());
        employee.setCountry(employees.getCountry());
        employee.setState(employees.getState());
        employee.setDateOfBirth(employees.getDateOfBirth());
        employee.setDepartment(employees.getDepartment());
        employee.setDivision(employees.getDivision());
        employee.setJoiningDate(employees.getJoiningDate());
        employee.setPhoneNumber(employees.getPhoneNumber());
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public String removeEmployee(String employeeId, Employees employees) {
        if (!employeeRepository.existsEmployeesByEmployeeId(employeeId)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        Employees employee = employeeRepository.findEmployeesByEmployeeStatusAndEmployeeId
                (EmployeesVariables.EMPLOYEE_STATUS, employeeId);
        employee.setEmployeeStatus(0);
        employeeRepository.save(employee);
        return "Employee Deleted Successfully";
    }
}
