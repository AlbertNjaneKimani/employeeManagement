package com.example.albert.employeemanagement.controller;

import com.example.albert.employeemanagement.datalayer.LeaveBalance;
import com.example.albert.employeemanagement.service.LeaveBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class LeaveBalanceController {
    @Autowired
    private LeaveBalanceService leaveBalanceService;

    @PostMapping("employees/{employeeId}/leave-balance")
    public LeaveBalance createLeaveBalance(@PathVariable String employeeId,
                                           @Valid @RequestBody LeaveBalance leaveBalance) {
        return leaveBalanceService.createLeaveBalance(employeeId, leaveBalance);
    }

    @GetMapping("/leave-balances")

    public List<LeaveBalance> getAllLeaveBalance() {
        return leaveBalanceService.getAllLeaveBalances();
    }

    @GetMapping("employees/leave-balance/{employeeId}")
    public List<LeaveBalance> getLeaveBalance(@PathVariable String employeeId) {
        return leaveBalanceService.getLeaveBalanceByEmployee(employeeId);
    }

    @PutMapping("employees/leave-balance/{leaveId}")
    public LeaveBalance updateLeave(@PathVariable String leaveId, @RequestBody LeaveBalance leaveBalance) {
        return leaveBalanceService.updateLeaveBalances(leaveId, leaveBalance);
    }
}
