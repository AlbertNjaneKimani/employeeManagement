package com.example.albert.employeemanagement.repository;

import com.example.albert.employeemanagement.datalayer.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Native;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, String> {
    @Query(nativeQuery = true, value = "select * from tbl_salary where employee_id =:employeeId")
    public Salary checkSalary(String employeeId);

    public Boolean existsSalaryBySalaryId(String employeeId);

    public Salary findSalaryBySalaryId(String salaryId);
}
