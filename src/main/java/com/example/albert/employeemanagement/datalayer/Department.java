package com.example.albert.employeemanagement.datalayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tbl_department")
public class Department {
    @Id
    @Column(name = "department_id")
    private String departmentId;
    @NotEmpty(message = "Department name cannot be empty")
    @Column(name = "department_name")
    private String departmentName;
    @NotEmpty(message = "Department code cannot be empty")
    @Column(name = "department_code")
    private String departmentCode;
    @Column(name = "in_trash")
    private int inTrash;
    @OneToMany(mappedBy = "department",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Division> divisions = new ArrayList<>();

    public void addDivision(Division division) {
        divisions.add(division);
        division.setDepartment(this);
    }

    public void removeComment(Division division) {
        divisions.remove(division);
        division.setDepartment(null);
    }
}
