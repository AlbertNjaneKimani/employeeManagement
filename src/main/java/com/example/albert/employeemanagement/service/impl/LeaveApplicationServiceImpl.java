package com.example.albert.employeemanagement.service.impl;

import com.example.albert.employeemanagement.datalayer.Employees;
import com.example.albert.employeemanagement.datalayer.LeaveApplication;
import com.example.albert.employeemanagement.datalayer.LeaveBalance;
import com.example.albert.employeemanagement.exception.LimitExcededException;
import com.example.albert.employeemanagement.exception.ResourceNotFoundException;
import com.example.albert.employeemanagement.repository.EmployeeRepository;
import com.example.albert.employeemanagement.repository.LeaveApplicationRepository;
import com.example.albert.employeemanagement.repository.LeaveBalanceRepository;
import com.example.albert.employeemanagement.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.UUID;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {
    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;

    @Transactional
    public LeaveApplication applyLeave(String employeeId, String leaveId, LeaveApplication leaveApplication) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fDate = leaveApplication.getFromDate().toString();
        String tDate = leaveApplication.getToDate().toString();
        LocalDate startDate = LocalDate.parse(tDate, dtf);
        LocalDate endDate = LocalDate.parse(fDate, dtf);
        int daysDif = (int) Duration.between(endDate.atStartOfDay(), startDate.atStartOfDay()).toDays();
        int remainingLeave = leaveBalanceRepository.findLeaveBalanceByLeaveId(leaveId).getBalance();
        if ((employeeRepository.findEmployeesByEmployeeStatusAndEmployeeId(1, employeeId) != null) &&
                leaveBalanceRepository.findLeaveBalanceByLeaveId(leaveId) != null) {

            if (remainingLeave < 0) {
                throw new LimitExcededException("You have insufficient leave balance. your leave balance is :" + remainingLeave);
            }
            if (leaveBalanceRepository.findLeaveBalanceByLeaveId(leaveId).getBalance() < daysDif) {
                throw new LimitExcededException("You have insufficient leave balance. your leave balance is :" + remainingLeave);
            }
        }
        Employees empid = employeeRepository.findEmployeesByEmployeeStatusAndEmployeeId(1, employeeId);
        LeaveBalance leaveBal = leaveBalanceRepository.findLeaveBalanceByLeaveId(leaveId);
        leaveApplication.setApplicationId(UUID.randomUUID().toString());
        leaveApplication.setLeaveStatus("PENDING");
        leaveApplication.setLeaveDays(daysDif);
        leaveApplication.setEmployees(empid);
        leaveApplication.setLeaveBalance(leaveBal);
        leaveApplicationRepository.save(leaveApplication);
        return leaveApplication;
    }

    @Override
    public LeaveApplication approveLeave(String leaveId, String applicationId) {
        LeaveApplication leaveApp = leaveApplicationRepository.findLeaveApplicationByApplicationId(applicationId);
        leaveApp.setLeaveStatus("APPROVED");
        leaveApplicationRepository.save(leaveApp);
        //update leave balances
        LeaveBalance leaveBala = leaveBalanceRepository.findLeaveBalanceByLeaveId(leaveId);
        leaveBala.setBalance(leaveBala.getBalance() - leaveApp.getLeaveDays());
        leaveBalanceRepository.save(leaveBala);
        return leaveApp;
    }

    @Override
    public LeaveApplication rejectLeave(String leaveId, String applicationId, LeaveApplication leaveApplication) {
        LeaveApplication leaveApp = leaveApplicationRepository.findLeaveApplicationByApplicationId(applicationId);
        leaveApp.setLeaveStatus("REJECTED");
        leaveApp.setRemarks(leaveApplication.getRemarks());
        leaveApplicationRepository.save(leaveApp);
        return leaveApp;
    }

    @Override
    public List<LeaveApplication> allLeaves() {
        return leaveApplicationRepository.findAll();
    }

    //in progress
    @Override
    public List<LeaveApplication> leavesByEmployee(String employeeId) {
        if (!leaveApplicationRepository.findLeaveApplicationByEmployees(employeeId).isPresent()) {
            throw new ResourceNotFoundException("Record not found");
        }
        return leaveApplicationRepository.employeeLeaveApplications(employeeId);
    }
}
