package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.enums.LeaveType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "leave_balances")
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "leave_type", nullable = false)
    private LeaveType leaveType;

    @Column(name = "total_leaves", nullable = false)
    private Double totalLeaves;

    @Column(name = "used_leaves", nullable = false)
    private Double bookedLeaves;

    @Column(name = "available_leaves", nullable = false)
    private Double availableLeaves;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public Double getTotalLeaves() {
        return totalLeaves;
    }

    public void setTotalLeaves(Double totalLeaves) {
        this.totalLeaves = totalLeaves;
    }

    public Double getBookedLeaves() {
        return bookedLeaves;
    }

    public void setBookedLeaves(Double bookedLeaves) {
        this.bookedLeaves = bookedLeaves;
    }

    public Double getAvailableLeaves() {
        return availableLeaves;
    }

    public void setAvailableLeaves(Double availableLeaves) {
        this.availableLeaves = availableLeaves;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
