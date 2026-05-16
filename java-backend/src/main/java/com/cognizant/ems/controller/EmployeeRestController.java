package com.cognizant.ems.controller;

import com.cognizant.ems.model.Employee;
import com.cognizant.ems.model.AuditLog;
import com.cognizant.ems.service.EmployeeService;
import com.cognizant.ems.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clean REST API exposing Data as JSON.
 * Ideal for FSE interview requirements to show full-stack skills.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuditLogService auditLogService;

    // GET /api/employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // GET /api/employees/{id} - enhanced for Quick View Modal
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeDetails(@PathVariable Long id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            String fullName = employee.getFirstName() + " " + employee.getLastName();
            List<AuditLog> logs = auditLogService.getLogsForEmployee(fullName);
            
            Map<String, Object> response = new HashMap<>();
            response.put("employee", employee);
            response.put("auditLogs", logs);
            
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/employees
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok(employee);
    }
}

