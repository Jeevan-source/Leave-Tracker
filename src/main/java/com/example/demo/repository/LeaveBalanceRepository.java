package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LeaveBalance;
import com.example.demo.enums.LeaveType;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {

    
    List<LeaveBalance> findByEmployeeId(Long employeeId);

    
    List<LeaveBalance> findByEmployeeIdAndLeaveType(Long employeeId, LeaveType leaveType);
}