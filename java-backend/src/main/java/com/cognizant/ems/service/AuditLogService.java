package com.cognizant.ems.service;

import com.cognizant.ems.model.AuditLog;
import com.cognizant.ems.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public void logAction(String action, String employeeName, String performedBy) {
        AuditLog log = new AuditLog(action, employeeName, performedBy, LocalDateTime.now());
        auditLogRepository.save(log);
    }

    public Page<AuditLog> getAuditLogs(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return auditLogRepository.findAllByOrderByTimestampDesc(pageable);
    }

    public List<AuditLog> getLogsForEmployee(String employeeName) {
        return auditLogRepository.findTop5ByEmployeeNameOrderByTimestampDesc(employeeName);
    }
}
