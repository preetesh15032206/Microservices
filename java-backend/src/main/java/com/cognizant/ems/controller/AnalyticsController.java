package com.cognizant.ems.controller;

import com.cognizant.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/salary-by-department")
    public ResponseEntity<List<Map<String, Object>>> getSalaryByDepartment() {
        List<Object[]> rawData = employeeService.getAverageSalaryByDepartment();
        List<Map<String, Object>> response = new ArrayList<>();
        
        for (Object[] row : rawData) {
            Map<String, Object> map = new HashMap<>();
            map.put("dept", row[0] != null ? row[0] : "No Department");
            map.put("avgSalary", row[1]);
            response.add(map);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/salary-ranges")
    public ResponseEntity<List<Map<String, Object>>> getSalaryRanges() {
        List<Object[]> rawData = employeeService.getEmployeeCountBySalaryRange();
        List<Map<String, Object>> response = new ArrayList<>();
        
        if (rawData != null && !rawData.isEmpty()) {
            Object[] counts = rawData.get(0);
            String[] ranges = {"0-30k", "30k-60k", "60k-100k", "100k+"};
            for (int i = 0; i < ranges.length; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("range", ranges[i]);
                map.put("count", counts[i] != null ? counts[i] : 0);
                response.add(map);
            }
        }
        return ResponseEntity.ok(response);
    }
}
