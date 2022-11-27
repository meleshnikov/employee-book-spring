package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Collection<Employee> getAllEmployees() {
        return this.employeeRepository.getAll();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        return this.employeeRepository.add(employeeRequest);
    }

    public Employee findEmployeeById(int id) {
        return this.employeeRepository.find(id);
    }

    public String removeEmployeeById(int id) {
        return this.employeeRepository.remove(id);
    }

    public int getSalarySum() {
        return getAllEmployees()
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public double getSalaryAverage() {
        return getAllEmployees()
                .stream()
                .mapToInt(Employee::getSalary)
                .average().
                orElse(0);
    }

    public Employee getEmployeeWithMinSalary() {
        return getAllEmployees()
                .stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee getEmployeeWithMaxSalary() {
        return getAllEmployees()
                .stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public List<Employee> getEmployeesWithSalaryMoreAverage() {
        return getAllEmployees()
                .stream().filter(e -> e.getSalary() > getSalaryAverage())
                .collect(Collectors.toList());
    }

}
