package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {

        final String firstName = employeeRequest.getFirstName().trim().toLowerCase();
        final String lastName = employeeRequest.getLastName().trim().toLowerCase();

        if (!isValidName(firstName) || !isValidName(lastName)) {
            throw new RuntimeException("400 Bad Request");
        }

        Employee employee = new Employee(
                StringUtils.capitalize(firstName),
                StringUtils.capitalize(lastName),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary()
        );

        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values()
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public double getSalaryAverage() {
        return !employees.isEmpty() ? (double) getSalarySum() / employees.size() : 0;
    }

    public Employee getEmployeeWithMinSalary() {
        return employees.values()
                .stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee getEmployeeWithMaxSalary() {
        return employees.values()
                .stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public List<Employee> getEmployeesWithSalaryMoreAverage() {
        return employees.values()
                .stream().filter(e -> e.getSalary() > getSalaryAverage())
                .collect(Collectors.toList());
    }

    private static boolean isValidName(String name) {
        return !StringUtils.isEmpty(name) && StringUtils.isAlpha(name);
    }

}
