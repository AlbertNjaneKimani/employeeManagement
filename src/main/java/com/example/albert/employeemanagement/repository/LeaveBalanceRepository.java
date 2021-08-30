package com.example.albert.employeemanagement.repository;

import com.example.albert.employeemanagement.datalayer.LeaveBalance;
import com.example.albert.employeemanagement.datalayer.LeaveBalanceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, String> {
    @Query(nativeQuery = true, value = "select * from tbl_leave_balance" +
            " where leave_type=:leaveType and employee_id=:employeeId")
    public Optional<LeaveBalance> leaveBalanceExist(String leaveType, String employeeId);

    @Query(nativeQuery = true, value = "select leave_id,leave_type,employee_id,leave_balance from tbl_leave_balance")
    public List<LeaveBalance> getAllLeaves();

    @Query(nativeQuery = true, value = "select leave_id,leave_type,employee_id,leave_balance " +
            "from tbl_leave_balance" +
            " where employee_id=:employeeId")
    public List<LeaveBalance> getAllLeaveByEmployee(String employeeId);

    public LeaveBalance findLeaveBalanceByLeaveId(String leaveId);

    public Boolean existsLeaveBalanceByLeaveId(String leaveId);


}
