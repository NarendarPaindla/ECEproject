package com.example.controller;

import com.example.dto.ApplyReq;
import com.example.dto.StatusReq;
import com.example.model.LeaveRequest;
import com.example.service.LeaveService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/leaves")
public class LeaveController {
    private final LeaveService service;
    public LeaveController(LeaveService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<LeaveRequest> apply(@Valid @RequestBody ApplyReq req) {
        return ResponseEntity.ok(
            service.apply(req.getEmployeeId(), req.getManagerId(), req.getStartDate(), req.getEndDate(), req.getReason())
        );
    }

    @GetMapping("/employee/{employeeId}")
    public Page<LeaveRequest> myLeaves(
            @PathVariable Long employeeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.myLeavesPaged(employeeId, page, size);
    }

    @GetMapping("/manager/{managerId}")
    public Page<LeaveRequest> teamLeaves(
            @PathVariable Long managerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.leavesForManagerPaged(managerId, page, size);
    }

    @PatchMapping("/{leaveId}/status")
    public ResponseEntity<LeaveRequest> setStatus(@PathVariable Long leaveId, @Valid @RequestBody StatusReq req){
        return ResponseEntity.ok(service.setStatus(leaveId, req.getStatus()));
    }

    @GetMapping
    public Page<LeaveRequest> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return service.allPaged(page, size);
    }

    @GetMapping("/{id}") public LeaveRequest one(@PathVariable Long id){ return service.byId(id); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id); return ResponseEntity.noContent().build();
    }
}
