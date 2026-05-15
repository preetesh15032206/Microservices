package com.cognizant.ems.service;

import com.cognizant.ems.model.Employee;
import com.cognizant.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        // Find by ID, throw RuntimeException if not found
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for id :: " + id));
    }

    public void deleteEmployeeById(Long id) {
        if(!employeeRepository.existsById(id)){
            throw new RuntimeException("Employee not found for id :: " + id);
        }
        employeeRepository.deleteById(id);
    }

    public List<Employee> searchEmployee(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(keyword, keyword);
        }
        return employeeRepository.findAll();
    }
}
