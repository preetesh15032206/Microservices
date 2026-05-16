package com.cognizant.ems.service;

import com.cognizant.ems.model.Employee;
import com.cognizant.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuditLogService auditLogService;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void saveEmployee(Employee employee) {
        boolean isNew = (employee.getId() == null);
        employeeRepository.save(employee);
        
        String action = isNew ? "CREATED" : "UPDATED";
        auditLogService.logAction(action, employee.getFirstName() + " " + employee.getLastName(), getCurrentUsername());
    }

    public void toggleEmployeeStatus(Long id) {
        Employee emp = getEmployeeById(id);
        emp.setIsActive(!emp.getIsActive());
        employeeRepository.save(emp);
        
        String action = emp.getIsActive() ? "REACTIVATED" : "DEACTIVATED";
        auditLogService.logAction(action, emp.getFirstName() + " " + emp.getLastName(), getCurrentUsername());
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for id :: " + id));
    }

    public void deleteEmployeeById(Long id) {
        if(!employeeRepository.existsById(id)){
            throw new RuntimeException("Employee not found for id :: " + id);
        }
        Employee emp = getEmployeeById(id);
        employeeRepository.deleteById(id);
        auditLogService.logAction("DELETED", emp.getFirstName() + " " + emp.getLastName(), getCurrentUsername());
    }

    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword, String status, Long departmentId) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        
        if (departmentId != null && departmentId > 0) {
            return employeeRepository.findByDepartmentAndStatus(departmentId, status, pageable);
        }
        
        if (keyword != null && !keyword.isEmpty()) {
            return employeeRepository.searchEmployees(keyword, status, pageable);
        }
        
        return employeeRepository.findByStatus(status, pageable);
    }

    // KPIs
    public long getTotalActiveEmployees() {
        return employeeRepository.countByIsActiveTrue();
    }

    public Double getAverageSalary() {
        Double avg = employeeRepository.getAverageSalaryOfActiveEmployees();
        return avg != null ? avg : 0.0;
    }

    public Employee getHighestPaidEmployee() {
        return employeeRepository.findFirstByIsActiveTrueOrderBySalaryDesc();
    }

    public Employee getNewestHire() {
        return employeeRepository.findFirstByIsActiveTrueOrderByJoinDateDesc();
    }

    public List<Object[]> getAverageSalaryByDepartment() {
        return employeeRepository.getAverageSalaryByDepartment();
    }

    public List<Object[]> getEmployeeCountBySalaryRange() {
        return employeeRepository.getEmployeeCountBySalaryRange();
    }
}

