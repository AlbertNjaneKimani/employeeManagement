package com.example.albert.employeemanagement.controller;

import com.example.albert.employeemanagement.datalayer.LeaveApplication;
import com.example.albert.employeemanagement.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class LeaveApplicationController {
    @Autowired
    private LeaveApplicationService leaveApplicationService;

    @PostMapping("employee/{employeeId}/{leaveId}/apply-leave")
    public LeaveApplication applyLeave(@PathVariable String employeeId,
                                       @PathVariable String leaveId,
                                       @RequestBody LeaveApplication leaveApplication) {

        return leaveApplicationService.applyLeave(employeeId, leaveId, leaveApplication);
    }

    @PutMapping("employee/{leaveId}/{applicationId}")
    public LeaveApplication approveLeave(@PathVariable String leaveId, @PathVariable String applicationId) {
        return leaveApplicationService.approveLeave(leaveId, applicationId);
    }

    @PutMapping("employee/reject-leave/{leaveId}/{applicationId}")
    public LeaveApplication rejectLeave(@PathVariable String leaveId, @PathVariable String applicationId,
                                        @RequestBody LeaveApplication leaveApplication) {
        return leaveApplicationService.rejectLeave(leaveId, applicationId, leaveApplication);
    }

    @GetMapping("/leaves")
    public List<LeaveApplication> getAllLeaves() {
        return leaveApplicationService.allLeaves();
    }

    @GetMapping("/leaves/{employeeId}")
    public List<LeaveApplication> getLeavesByEmployee(@PathVariable String employeeId) {
        return leaveApplicationService.leavesByEmployee(employeeId);
    }
}
