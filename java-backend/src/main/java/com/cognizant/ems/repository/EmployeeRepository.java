package com.cognizant.ems.repository;

import com.cognizant.ems.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Pagination and searching query with status filter
    @Query("SELECT e FROM Employee e WHERE " +
           "(:status = 'ALL' OR (:status = 'ACTIVE' AND e.isActive = true) OR (:status = 'INACTIVE' AND e.isActive = false)) AND " +
           "(LOWER(e.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Employee> searchEmployees(String keyword, String status, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE " +
           "(:status = 'ALL' OR (:status = 'ACTIVE' AND e.isActive = true) OR (:status = 'INACTIVE' AND e.isActive = false))")
    Page<Employee> findByStatus(String status, Pageable pageable);

    // Filter by department
    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId AND " +
           "(:status = 'ALL' OR (:status = 'ACTIVE' AND e.isActive = true) OR (:status = 'INACTIVE' AND e.isActive = false))")
    Page<Employee> findByDepartmentAndStatus(Long departmentId, String status, Pageable pageable);

    // KPI Queries
    long countByIsActiveTrue();

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.isActive = true")
    Double getAverageSalaryOfActiveEmployees();

    Employee findFirstByIsActiveTrueOrderBySalaryDesc();

    Employee findFirstByIsActiveTrueOrderByJoinDateDesc();

    // Chart Queries
    @Query("SELECT e.department.name, AVG(e.salary) FROM Employee e WHERE e.isActive = true GROUP BY e.department.name")
    List<Object[]> getAverageSalaryByDepartment();

    @Query("SELECT " +
           "SUM(CASE WHEN e.salary < 30000 THEN 1 ELSE 0 END), " +
           "SUM(CASE WHEN e.salary >= 30000 AND e.salary < 60000 THEN 1 ELSE 0 END), " +
           "SUM(CASE WHEN e.salary >= 60000 AND e.salary < 100000 THEN 1 ELSE 0 END), " +
           "SUM(CASE WHEN e.salary >= 100000 THEN 1 ELSE 0 END) " +
           "FROM Employee e WHERE e.isActive = true")
    List<Object[]> getEmployeeCountBySalaryRange();
}

