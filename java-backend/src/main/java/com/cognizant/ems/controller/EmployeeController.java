package com.cognizant.ems.controller;

import com.cognizant.ems.model.Employee;
import com.cognizant.ems.service.DepartmentService;
import com.cognizant.ems.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class EmployeeController {
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", null, "ACTIVE", null, model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @RequestParam(value = "keyword", required = false) String keyword,
                                @RequestParam(value = "status", defaultValue = "ACTIVE") String status,
                                @RequestParam(value = "departmentId", required = false) Long departmentId,
                                Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        // Non-admins can only see ACTIVE
        if (!isAdmin) {
            status = "ACTIVE";
        }

        int pageSize = 10;
        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir, keyword, status, departmentId);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("listEmployees", listEmployees);
        
        // KPIs
        model.addAttribute("totalActive", employeeService.getTotalActiveEmployees());
        model.addAttribute("avgSalary", employeeService.getAverageSalary());
        model.addAttribute("highestPaid", employeeService.getHighestPaidEmployee());
        model.addAttribute("newestHire", employeeService.getNewestHire());

        return "index";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, @RequestParam(value = "deptId", required = false) Long deptId, org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttrs) {
        if (deptId != null && deptId > 0) {
            employee.setDepartment(departmentService.getDepartmentById(deptId));
        } else {
            employee.setDepartment(null);
        }

        if (employee.getId() != null) {
            Employee existing = employeeService.getEmployeeById(employee.getId());
            employee.setIsActive(existing.getIsActive());
            employee.setJoinDate(existing.getJoinDate());
        } else {
            employee.setIsActive(true);
            employee.setJoinDate(java.time.LocalDate.now());
        }

        try {
            employeeService.saveEmployee(employee);
            redirectAttrs.addFlashAttribute("successMessage", "Employee saved successfully!");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", "Error saving employee: " + (e.getMessage() != null ? e.getMessage() : e.toString()));
            // Redirect back to the correct form based on whether it's an update or create
            return employee.getId() != null ? "redirect:/showFormForUpdate/" + employee.getId() : "redirect:/showNewEmployeeForm";
        }

        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "update_employee";
    }

    @PostMapping("/employees/{id}/toggle-status")
    public String toggleStatus(@PathVariable("id") Long id) {
        employeeService.toggleEmployeeStatus(id);
        return "redirect:/";
    }

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employees_export_" + System.currentTimeMillis() + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Employee> listEmployees = employeeService.getAllEmployees();

        PrintWriter writer = response.getWriter();
        writer.println("ID,First Name,Last Name,Email,Department,Designation,Salary,Status");
        for (Employee emp : listEmployees) {
            String deptName = emp.getDepartment() != null ? emp.getDepartment().getName() : "N/A";
            String status = emp.getIsActive() ? "Active" : "Inactive";
            writer.println(emp.getId() + "," + emp.getFirstName() + "," + emp.getLastName() + "," 
                + emp.getEmail() + "," + deptName + "," + emp.getDesignation() + "," 
                + (emp.getSalary() != null ? emp.getSalary() : "") + "," + status);
        }
    }
}

