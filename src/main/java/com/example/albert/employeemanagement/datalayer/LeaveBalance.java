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
@Table(name = "tbl_leave_balance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeaveBalance {
    @Id
    @Column(name = "leave_id")
    private String leaveId;
    @NotEmpty(message = "Leave type cannot be empty")
    @Column(name = "leave_type")
    private String leaveType;
    @Column(name = "leave_balance")
    private int balance;
    //@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employees employee;
    @OneToMany(mappedBy = "leaveBalance",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<LeaveApplication> leaveApplications = new ArrayList<>();
}
