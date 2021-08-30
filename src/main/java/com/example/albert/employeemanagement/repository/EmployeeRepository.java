package com.example.albert.employeemanagement.repository;

import com.example.albert.employeemanagement.datalayer.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employees, String> {
    public Boolean existsEmployeesByEmail(String email);

    public Boolean existsEmployeesByEmployeeId(String employeeId);

    public Boolean existsEmployeesByPhoneNumber(String phoneNumber);

    public Boolean existsEmployeesByNationalId(String nationalId);

    public List<Employees> findAllByEmployeeStatus(int employeeStatus);

    public Optional<Employees> findEmployeesByEmployeeIdAndEmployeeStatus(String employeeId, int employeeStatus);

    public Optional<Employees> findEmployeesByPhoneNumberAndEmployeeStatus(String phoneNumber, int employeeStatus);

    public Optional<Employees> findEmployeesByNationalIdAndEmployeeStatus(String nationalId, int employeeStatus);

    public Employees findEmployeesByEmployeeStatusAndEmployeeId(int employeeStatus, String employeeId);
}
