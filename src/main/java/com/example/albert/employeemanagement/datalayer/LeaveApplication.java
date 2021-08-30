package com.example.albert.employeemanagement.datalayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_leave_application")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeaveApplication {
    @Id
    @Column(name = "application_id")
    private String applicationId;
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;
    @Column(name = "leave_days")
    private int leaveDays;
    private String reason;
    @Column(name = "leave_status")
    private String leaveStatus;
    private String remarks;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_id")
    private LeaveBalance leaveBalance;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employees employees;

}
