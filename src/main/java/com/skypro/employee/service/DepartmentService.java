package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesByDepartment(int department) {
        return getEmployeesByDepartmentStream(department).toList();
    }

    public int getSalarySumByDepartment(int department) {
        return getEmployeesByDepartmentStream(department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getSalaryMinByDepartment(int department) {
        return getEmployeesByDepartmentStream(department)
                .mapToInt(Employee::getSalary)
                .min().
                orElse(0);
    }

    public int getSalaryMaxByDepartment(int department) {
        return getEmployeesByDepartmentStream(department)
                .mapToInt(Employee::getSalary)
                .max().
                orElse(0);
    }

    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return this.employeeService.getAllEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    private Stream<Employee> getEmployeesByDepartmentStream(int department) {
        return this.employeeService.getAllEmployees()
                .stream()
                .filter(e -> e.getDepartment() == department);
    }

}
