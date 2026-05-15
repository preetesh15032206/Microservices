package com.cognizant.ems.controller;

import com.cognizant.ems.model.Employee;
import com.cognizant.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class EmployeeController {
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    // Login page endpoint
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Display list of all employees
    @GetMapping("/")
    public String viewHomePage(Model model, @RequestParam(value="keyword", required=false) String keyword) {
        logger.info("Accessing homepage dashboard. Keyword: {}", keyword);
        model.addAttribute("listEmployees", employeeService.searchEmployee(keyword));
        return "index";
    }

    // Show form for new Employee
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // Create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    // Save Employee via Add or Update form
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        logger.info("Employee saved successfully: {}", employee.getEmail());
        return "redirect:/"; // Redirect back to index page
    }

    // Show form to update existing Employee
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        // Set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    // Delete Employee (Only ADMIN)
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        employeeService.deleteEmployeeById(id);
        logger.info("Employee with ID {} deleted", id);
        return "redirect:/";
    }
}
