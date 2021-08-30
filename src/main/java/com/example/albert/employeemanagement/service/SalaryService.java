package com.example.albert.employeemanagement.service;

import com.example.albert.employeemanagement.datalayer.Salary;

import java.util.List;

public interface SalaryService {
    public Salary addSalary(String employeeId, Salary salary);

    public List<Salary> getAllSalaries();

    public Salary getSalaryByEmployee(String employeeId);

    public Salary updateSalary(String salaryId, Salary salary);
}
