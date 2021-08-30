package com.example.albert.employeemanagement.repository;

import com.example.albert.employeemanagement.datalayer.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, String> {
    @Query(nativeQuery = true, value = "select * from tbl_leave_application " +
            "where employee_id =:employeeId and leave_id=:leaveId")
    public Optional<LeaveApplication> findLeaveApplication(String employeeId, String leaveId);

    public LeaveApplication findLeaveApplicationByApplicationId(String applicationId);

    @Query(nativeQuery = true, value = "select * from tbl_leave_application " +
            "where employee_id =:employeeId")
    public Optional<LeaveApplication> findLeaveApplicationByEmployees(String employeeId);

    @Query(nativeQuery = true, value = "select * from tbl_leave_application " +
            "where employee_id =:employeeId")
    public List<LeaveApplication> employeeLeaveApplications(String employeeId);
}