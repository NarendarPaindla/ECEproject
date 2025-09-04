package com.example.dto;

import com.example.model.LeaveStatus;
import jakarta.validation.constraints.NotNull;

public class StatusReq {
    @NotNull
    private LeaveStatus status;

    public StatusReq() {}

    public LeaveStatus getStatus() { return status; }
    public void setStatus(LeaveStatus status) { this.status = status; }
}
