package com.example.albert.employeemanagement.service;

import com.example.albert.employeemanagement.datalayer.LeaveBalance;
import com.example.albert.employeemanagement.datalayer.LeaveBalanceResponse;

import javax.swing.text.AbstractDocument;
import java.util.List;

public interface LeaveBalanceService {
    public LeaveBalance createLeaveBalance(String employeeId, LeaveBalance leaveBalance);

    public List<LeaveBalance> getAllLeaveBalances();

    public List<LeaveBalance> getLeaveBalanceByEmployee(String employeeId);

    public LeaveBalance updateLeaveBalances(String leaveId, LeaveBalance leaveBalance);
}
