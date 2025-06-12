package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "leave_application")
public class LeaveApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String leaveType;

    @Column(name = "from_date")  // Renamed to avoid SQL reserved keyword
    private LocalDate fromDate;

    @Column(name = "to_date")    // Renamed to avoid SQL reserved keyword
    private LocalDate toDate;

    private String reason;
    private String status; // PENDING, APPROVED, REJECTED
    private int hoursTaken;
    private LocalDate requestDate;
    private String operation; // Add/Update/Delete
    private String teamEmailId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // No-args constructor
    public LeaveApplication() {}

    // All-args constructor
    public LeaveApplication(Long id, String leaveType, LocalDate fromDate, LocalDate toDate, String reason,
                            String status, int hoursTaken, LocalDate requestDate, String operation,
                            String teamEmailId, Employee employee) {
        this.id = id;
        this.leaveType = leaveType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.status = status;
        this.hoursTaken = hoursTaken;
        this.requestDate = requestDate;
        this.operation = operation;
        this.teamEmailId = teamEmailId;
        this.employee = employee;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLeaveType() { return leaveType; }
    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }

    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getHoursTaken() { return hoursTaken; }
    public void setHoursTaken(int hoursTaken) { this.hoursTaken = hoursTaken; }

    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public String getTeamEmailId() { return teamEmailId; }
    public void setTeamEmailId(String teamEmailId) { this.teamEmailId = teamEmailId; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Long getEmployeeId() {
        return employee != null ? employee.getId() : null;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String leaveType;
        private LocalDate fromDate;
        private LocalDate toDate;
        private String reason;
        private String status;
        private int hoursTaken;
        private LocalDate requestDate;
        private String operation;
        private String teamEmailId;
        private Employee employee;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder leaveType(String leaveType) {
            this.leaveType = leaveType;
            return this;
        }

        public Builder fromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public Builder toDate(LocalDate toDate) {
            this.toDate = toDate;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder hoursTaken(int hoursTaken) {
            this.hoursTaken = hoursTaken;
            return this;
        }

        public Builder requestDate(LocalDate requestDate) {
            this.requestDate = requestDate;
            return this;
        }

        public Builder operation(String operation) {
            this.operation = operation;
            return this;
        }

        public Builder teamEmailId(String teamEmailId) {
            this.teamEmailId = teamEmailId;
            return this;
        }

        public Builder employee(Employee employee) {
            this.employee = employee;
            return this;
        }

        public LeaveApplication build() {
            return new LeaveApplication(id, leaveType, fromDate, toDate, reason, status, hoursTaken,
                                        requestDate, operation, teamEmailId, employee);
        }
    }
}
