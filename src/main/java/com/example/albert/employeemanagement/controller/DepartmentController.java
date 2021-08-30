package com.example.albert.employeemanagement.controller;

import com.example.albert.employeemanagement.datalayer.Department;
import com.example.albert.employeemanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/department")
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
        ResponseEntity<Department> dept = departmentService.createDepartment(department);
        return dept;
    }

    @GetMapping("/department")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<Department> getDepartmentByDepartmentId(@PathVariable String departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @GetMapping("/department/search")
    public Department searchDepartment(@RequestParam("departmentName") String departmentName,
                                       @RequestParam("departmentCode") String departmentCode) {
        return departmentService.searchDepartment(departmentName, departmentCode);
    }

    @PutMapping("/department/{departmentId}")
    public Department updateDepart(@PathVariable String departmentId,
                                    @Valid @RequestBody Department department) {
        return departmentService.updateDepartment(departmentId, department);

    }
}
