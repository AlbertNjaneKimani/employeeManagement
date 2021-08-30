package com.example.albert.employeemanagement.datalayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tbl_leave_balance")
public class LeaveBalanceResponse {
    @Column(name = "leave_id")
    private  String leaveId;
    @Column(name = "employee_id")
    private String employeeId;
    @Column(name = "leave_type")
    private String leaveType;
    @Column(name = "leave_balance")
    private int balance;
}
