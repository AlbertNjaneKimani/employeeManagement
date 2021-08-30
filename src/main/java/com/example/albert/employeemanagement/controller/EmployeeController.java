package com.example.albert.employeemanagement.controller;

import com.example.albert.employeemanagement.datalayer.Employees;
import com.example.albert.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employees> createEmployee(@Valid @RequestBody Employees employees) {
        ResponseEntity<Employees> savedEmp = (ResponseEntity<Employees>)
                employeeService.createEmployee(employees);
        return savedEmp;
    }

    @GetMapping("/employee")
    public List<Employees> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/{employeeId}")
    public Optional<Employees> getSingleEmployee(@PathVariable String employeeId) {
        return employeeService.getSingleEmployee(employeeId);
    }

    @GetMapping("/employee/phone/{phoneNumber}")
    public Optional<Employees> getEmployeeByPhoneNumber(@PathVariable String phoneNumber) {
        return employeeService.getEmployeeByPhoneNumber(phoneNumber);
    }

    @GetMapping("/employee/national-id/{nationalId}")
    public Optional<Employees> getEmployeeByNationalId(@PathVariable String nationalId) {
        return employeeService.getEmployeeByNationalId(nationalId);
    }
    @PutMapping("/employee/{employeeId}")
    public Employees updateEmployee(@PathVariable("employeeId") String employeeId,
                                    @RequestBody Employees employees) {
        return employeeService.updateEmployee(employeeId,employees);
    }
    @PutMapping("/employee/disable/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteEmployee(@PathVariable String employeeId, Employees employees){
        employeeService.removeEmployee(employeeId,employees);
        return "Employee deleted successfully";
    }
}
