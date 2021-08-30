package com.example.albert.employeemanagement.controller;

import com.example.albert.employeemanagement.datalayer.Salary;
import com.example.albert.employeemanagement.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @PostMapping("/employee/{employeeId}/salary")
    public Salary addSalary(@PathVariable String employeeId,
                            @Valid @RequestBody Salary salary) {
        return salaryService.addSalary(employeeId, salary);
    }

    @GetMapping("/salaries")
    public List<Salary> getSalaries() {
        return salaryService.getAllSalaries();
    }

    @GetMapping("/salaries/{employeeId}")
    public Salary getSalaryByEmployee(@PathVariable String employeeId) {
        return salaryService.getSalaryByEmployee(employeeId);
    }

    @PutMapping("/salaries/{salaryId}")
    public Salary updateSalary(@PathVariable String salaryId,
                               @Valid @RequestBody Salary salary) {
        return salaryService.updateSalary(salaryId, salary);
    }
}
