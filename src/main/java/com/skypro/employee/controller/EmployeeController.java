package com.skypro.employee.controller;

import com.skypro.employee.exception.EmployeeNotExistException;
import com.skypro.employee.exception.InvalidNameException;
import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/salary/sum")
    public int getSalarySum() {
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/salary/average")
    public double getSalaryAverage() {
        return this.employeeService.getSalaryAverage();
    }

    @GetMapping("/salary/min")
    public Employee getEmployeeWithMinSalary() {
        return this.employeeService.getEmployeeWithMinSalary();
    }

    @GetMapping("/salary/max")
    public Employee getEmployeeWithMaxSalary() {
        return this.employeeService.getEmployeeWithMaxSalary();
    }

    @GetMapping("/high-salary")
    public List<Employee> getEmployeesWithSalaryMoreAverage() {
        return this.employeeService.getEmployeesWithSalaryMoreAverage();
    }

    @GetMapping("/find/{id}")
    public Employee findEmployeeById(@PathVariable int id) {
        return this.employeeService.findEmployeeById(id);
    }

    @GetMapping("/remove/{id}")
    public String removeEmployeeById(@PathVariable int id) {
        return this.employeeService.removeEmployeeById(id);
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<Object> invalidNameException(InvalidNameException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotExistException.class)
    public ResponseEntity<Object> employeeNotFoundException(EmployeeNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
