package com.cognizant.ems.controller;

import com.cognizant.ems.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping("/audit-logs")
    public String viewAuditLogs(Model model, @RequestParam(defaultValue = "1") int page) {
        model.addAttribute("logs", auditLogService.getAuditLogs(page, 10));
        model.addAttribute("currentPage", page);
        return "audit_logs";
    }
}
