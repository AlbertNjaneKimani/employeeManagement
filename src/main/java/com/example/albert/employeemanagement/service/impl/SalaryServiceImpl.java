package com.example.albert.employeemanagement.service.impl;

import com.example.albert.employeemanagement.datalayer.Employees;
import com.example.albert.employeemanagement.datalayer.Salary;
import com.example.albert.employeemanagement.exception.ResourceExistsException;
import com.example.albert.employeemanagement.exception.ResourceNotFoundException;
import com.example.albert.employeemanagement.repository.EmployeeRepository;
import com.example.albert.employeemanagement.repository.SalaryRepository;
import com.example.albert.employeemanagement.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Salary addSalary(String employeeId, Salary salary) {
        if (salaryRepository.checkSalary(employeeId) != null) {
            throw new ResourceExistsException("Employee Salary already exists");
        }
        Employees empId = employeeRepository.findEmployeesByEmployeeStatusAndEmployeeId(1, employeeId);
        salary.setSalaryId(UUID.randomUUID().toString());
        salary.setEmployee(empId);
        salaryRepository.save(salary);
        return salary;
    }

    @Override
    public List<Salary> getAllSalaries() {
        List<Salary> sal = salaryRepository.findAll();
        return sal;
    }

    @Override
    public Salary getSalaryByEmployee(String employeeId) {
        if (salaryRepository.checkSalary(employeeId)==null) {
            throw new ResourceNotFoundException("Employee Salary not found");
        }
        Salary sal = salaryRepository.checkSalary(employeeId);
        return sal;
    }

    @Override
    public Salary updateSalary(String salaryId, Salary salary) {
        if(!salaryRepository.existsSalaryBySalaryId(salaryId)){
            throw  new ResourceNotFoundException("Employee Salary Record Not found");
        }
        Salary sal =salaryRepository.findSalaryBySalaryId(salaryId);
        sal.setAmount(salary.getAmount());
        sal.setBonus(salary.getBonus());
        salaryRepository.save(sal);
        return sal;
    }
}
