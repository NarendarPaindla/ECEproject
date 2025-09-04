package com.example.service;

import com.example.model.*;
import com.example.repository.LeaveRequestRepository;
import com.example.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service @Transactional
public class LeaveService {
    private final LeaveRequestRepository leaves;
    private final UserRepository users;

    public LeaveService(LeaveRequestRepository leaves, UserRepository users) {
        this.leaves = leaves; this.users = users;
    }

    public LeaveRequest apply(Long employeeId, Long managerId, LocalDate start, LocalDate end, String reason){
        if (end.isBefore(start)) throw new IllegalArgumentException("End date cannot be before start date");
        User emp = users.findById(employeeId).orElseThrow();

        if (managerId != null) {
            User mgr = users.findById(managerId).orElseThrow(() -> new IllegalArgumentException("Manager not found"));
            if (mgr.getRole() != Role.MANAGER) throw new IllegalArgumentException("Selected user is not a MANAGER");
            if (emp.getManagerId() == null || !managerId.equals(emp.getManagerId())) {
                emp.setManagerId(managerId);
                users.save(emp);
            }
        } else if (emp.getManagerId() == null) {
            throw new IllegalArgumentException("Please select a manager.");
        }

        LeaveRequest lr = new LeaveRequest(null, emp, start, end, reason, LeaveStatus.PENDING);
        return leaves.save(lr);
    }

    // list (non-paged) kept for internal use
    public List<LeaveRequest> myLeaves(Long employeeId){ return leaves.findByEmployee_Id(employeeId); }
    public List<LeaveRequest> leavesForManager(Long managerId){ return leaves.findByEmployee_ManagerId(managerId); }
    public List<LeaveRequest> all(){ return leaves.findAll(); }

    // paged
    public Page<LeaveRequest> myLeavesPaged(Long employeeId, int page, int size){
        return leaves.findByEmployee_Id(employeeId, PageRequest.of(page, size));
    }
    public Page<LeaveRequest> leavesForManagerPaged(Long managerId, int page, int size){
        return leaves.findByEmployee_ManagerId(managerId, PageRequest.of(page, size));
    }
    public Page<LeaveRequest> allPaged(int page, int size){
        return leaves.findAll(PageRequest.of(page, size));
    }

    public LeaveRequest setStatus(Long leaveId, LeaveStatus status){
        LeaveRequest lr = leaves.findById(leaveId).orElseThrow();
        lr.setStatus(status);
        return leaves.save(lr);
    }
    public LeaveRequest byId(Long id){ return leaves.findById(id).orElseThrow(); }
    public void delete(Long id){ leaves.deleteById(id); }
}
