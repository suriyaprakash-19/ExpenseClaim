package com.example.ExpenseClaim.Employee.controller;

import com.example.ExpenseClaim.Employee.entity.Employee;
import com.example.ExpenseClaim.Employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @RequestBody Employee employee) {

        return ResponseEntity.ok(
                employeeService.createEmployee(employee)
        );
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return ResponseEntity.ok(
                employeeService.getAllEmployees()
        );
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable Long id) {

        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get employee by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Employee> getEmployeeByEmail(
            @PathVariable String email) {

        return employeeService.getEmployeeByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get employees by department
    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(
            @PathVariable String department) {

        return ResponseEntity.ok(
                employeeService.getEmployeesByDepartment(department)
        );
    }

    // Get employees by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<Employee>> getEmployeesByRole(
            @PathVariable String role) {

        return ResponseEntity.ok(
                employeeService.getEmployeesByRole(role)
        );
    }

    // Update employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, employee)
        );
    }

    // Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok(
                "Employee deleted successfully"
        );
    }
}