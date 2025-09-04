package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity @Table(name = "leave_requests")
public class LeaveRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employee_id")
    private User employee;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @Column(length = 500)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveStatus status = LeaveStatus.PENDING;

    public LeaveRequest() {}
    public LeaveRequest(Long id, User employee, LocalDate startDate, LocalDate endDate, String reason, LeaveStatus status) {
        this.id=id; this.employee=employee; this.startDate=startDate; this.endDate=endDate; this.reason=reason; this.status=status;
    }

    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public User getEmployee(){return employee;} public void setEmployee(User employee){this.employee=employee;}
    public LocalDate getStartDate(){return startDate;} public void setStartDate(LocalDate startDate){this.startDate=startDate;}
    public LocalDate getEndDate(){return endDate;} public void setEndDate(LocalDate endDate){this.endDate=endDate;}
    public String getReason(){return reason;} public void setReason(String reason){this.reason=reason;}
    public LeaveStatus getStatus(){return status;} public void setStatus(LeaveStatus status){this.status=status;}
}
