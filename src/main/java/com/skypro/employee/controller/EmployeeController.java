package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employee/salary/sum")
    public int getSalarySum() {
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employee/salary/average")
    public double getSalaryAverage() {
        return this.employeeService.getSalaryAverage();
    }

    @GetMapping("/employee/salary/min")
    public Employee getEmployeeWithMinSalary() {
        return this.employeeService.getEmployeeWithMinSalary();
    }

    @GetMapping("/employee/salary/max")
    public Employee getEmployeeWithMaxSalary() {
        return this.employeeService.getEmployeeWithMaxSalary();
    }

    @GetMapping("/employee/high-salary")
    public Map<Integer, Employee> getEmployeesWithSalaryMoreAverage() {
        return this.employeeService.getEmployeesWithSalaryMoreAverage();
    }

}
