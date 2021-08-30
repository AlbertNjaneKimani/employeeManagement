package com.example.albert.employeemanagement.service.impl;

import com.example.albert.employeemanagement.datalayer.Employees;
import com.example.albert.employeemanagement.datalayer.LeaveBalance;
import com.example.albert.employeemanagement.datalayer.LeaveBalanceResponse;
import com.example.albert.employeemanagement.exception.LimitExcededException;
import com.example.albert.employeemanagement.exception.ResourceExistsException;
import com.example.albert.employeemanagement.exception.ResourceNotFoundException;
import com.example.albert.employeemanagement.repository.EmployeeRepository;
import com.example.albert.employeemanagement.repository.LeaveBalanceRepository;
import com.example.albert.employeemanagement.service.LeaveBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LeaveBalanceServiceImpl implements LeaveBalanceService {
    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public LeaveBalance createLeaveBalance(String employeeId, LeaveBalance leaveBalance) {
        if (leaveBalanceRepository.leaveBalanceExist(leaveBalance.getLeaveType(), employeeId).isPresent()) {
            throw new ResourceExistsException("Leave Type " + leaveBalance.getLeaveType() + " Already Exits");
        }
        Employees empid = employeeRepository.findEmployeesByEmployeeStatusAndEmployeeId(1, employeeId);
        leaveBalance.setLeaveId(UUID.randomUUID().toString());
        leaveBalance.setLeaveType(leaveBalance.getLeaveType());
        leaveBalance.setBalance(leaveBalance.getBalance());
        leaveBalance.setEmployee(empid);
        leaveBalanceRepository.save(leaveBalance);
        return leaveBalance;
    }

    @Override
    public List<LeaveBalance> getAllLeaveBalances() {
        Optional<List<LeaveBalance>> balances = Optional.of(leaveBalanceRepository.findAll());
        if (!balances.isPresent()) {
            throw new ResourceNotFoundException("Records not found");
        }
        return leaveBalanceRepository.getAllLeaves();
    }

    @Override
    public List<LeaveBalance> getLeaveBalanceByEmployee(String employeeId) {
        if (!employeeRepository.existsEmployeesByEmployeeId(employeeId)) {
            throw new ResourceNotFoundException("employee has no leave balance record");
        }

        return leaveBalanceRepository.getAllLeaveByEmployee(employeeId);
    }

    @Override
    public LeaveBalance updateLeaveBalances(String leaveId, LeaveBalance leaveBalance) {
        if (leaveBalanceRepository.findLeaveBalanceByLeaveId(leaveId) == null) {
            throw new ResourceNotFoundException("No record was found");
        }
        if (leaveBalance.getLeaveType().equalsIgnoreCase("Paternety")) {
            if (leaveBalance.getBalance() > 14) {
                throw new LimitExcededException("Leave days for " + leaveBalance.getLeaveType() + " cannot be greater than 14");
            }
        } else if (leaveBalance.getLeaveType().equalsIgnoreCase("Maternity")) {
            if (leaveBalance.getBalance() > 90) {
                throw new LimitExcededException("Leave days for " + leaveBalance.getLeaveType() + " cannot be greater than 90");
            }
        }
        LeaveBalance leaveBal = leaveBalanceRepository.findLeaveBalanceByLeaveId(leaveId);
        leaveBal.setBalance(leaveBalance.getBalance());
        leaveBal.setLeaveType(leaveBalance.getLeaveType());
        return leaveBalanceRepository.save(leaveBal);
    }
}
