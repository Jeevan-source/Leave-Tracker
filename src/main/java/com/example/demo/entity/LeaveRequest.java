package com.example.demo.entity;

import java.time.LocalDate;

import com.example.demo.enums.LeaveStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private String teamEmailId;

    @Column(length = 500)
    private String reason;

    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }
}
