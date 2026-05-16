package com.cognizant.ems.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String action; // CREATED, UPDATED, DEACTIVATED, REACTIVATED, DELETED

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "performed_by", nullable = false)
    private String performedBy;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public AuditLog() {}

    public AuditLog(String action, String employeeName, String performedBy, LocalDateTime timestamp) {
        this.action = action;
        this.employeeName = employeeName;
        this.performedBy = performedBy;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getPerformedBy() { return performedBy; }
    public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
