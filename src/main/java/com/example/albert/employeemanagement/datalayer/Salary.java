package com.example.albert.employeemanagement.datalayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tbl_salary")
public class Salary {
    @Id
    @Column(name = "salary_id")
    private String salaryId;
    private int amount;
    private int bonus;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="employee_id", nullable = false)
    private Employees employee;
}
