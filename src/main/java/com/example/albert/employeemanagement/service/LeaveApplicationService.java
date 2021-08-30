package com.example.albert.employeemanagement.service;

import com.example.albert.employeemanagement.datalayer.LeaveApplication;

import java.util.List;

public interface LeaveApplicationService {
    public LeaveApplication applyLeave(String employeeId, String leaveId, LeaveApplication leaveApplication);

    public LeaveApplication approveLeave(String leaveId, String applicationId);

    public LeaveApplication rejectLeave(String leaveId, String applicationId, LeaveApplication leaveApplication);

    public List<LeaveApplication> allLeaves();

    public List<LeaveApplication> leavesByEmployee(String employeeId);
}
