package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee name should be set");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());

        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values()
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getSalaryAverage() {
        return !employees.isEmpty() ? getSalarySum() / employees.size() : 0;
    }

    public Employee getEmployeeWithMinSalary() {
        return employees.values()
                .stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .get();
    }

    public Employee getEmployeeWithMaxSalary() {
        return employees.values()
                .stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();
    }

    public Map<Integer, Employee> getEmployeesWithSalaryMoreAverage() {
        return employees.values()
                .stream().filter(e -> e.getSalary() > getSalaryAverage())
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
    }

}
