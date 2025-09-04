package com.example.repository;

import com.example.model.LeaveRequest;
import com.example.model.LeaveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long> {
    // existing
    List<LeaveRequest> findByEmployee_Id(Long employeeId);
    List<LeaveRequest> findByEmployee_ManagerId(Long managerId);
    List<LeaveRequest> findByStatus(LeaveStatus status);

    // new pageable
    Page<LeaveRequest> findByEmployee_Id(Long employeeId, Pageable pageable);
    Page<LeaveRequest> findByEmployee_ManagerId(Long managerId, Pageable pageable);
}
