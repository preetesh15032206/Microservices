package com.cognizant.ems.service;

import com.cognizant.ems.model.Employee;
import com.cognizant.ems.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    @Test
    public void testGetEmployeeById() {
        // AAA Pattern (Arrange, Act, Assert)
        
        // 1. Arrange
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setFirstName("Rohit");
        emp.setEmail("rohit@cognizant.com");
        
        when(repository.findById(1L)).thenReturn(Optional.of(emp));

        // 2. Act
        Employee result = service.getEmployeeById(1L);

        // 3. Assert
        assertNotNull(result);
        assertEquals("Rohit", result.getFirstName());
        assertEquals("rohit@cognizant.com", result.getEmail());
    }
}
