package com.example.albert.employeemanagement.repository;

import com.example.albert.employeemanagement.datalayer.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    public Boolean existsDepartmentByDepartmentId(String departmentId);
    public Boolean existsDepartmentByDepartmentName(String departmentName);
    public Boolean existsDepartmentByDepartmentCode(String departmentCode);
    public Department getDepartmentByDepartmentId(String departmentId);
    public Department findDepartmentByDepartmentId(String departmentId);
    public Department getDepartmentByDepartmentNameOrDepartmentCode(String departmentName, String departmentCode);
}
