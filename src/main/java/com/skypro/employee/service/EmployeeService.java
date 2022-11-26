package com.skypro.employee.service;

import com.skypro.employee.exception.InvalidNameException;
import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.repository.EmployeeRepository;
import org.apache.commons.lang3.StringUtils;
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
        return employeeRepository.getEmployees().values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {

        final String firstName = employeeRequest.getFirstName().trim().toLowerCase();

        if (isNotValidName(firstName)) {
            throw new InvalidNameException(firstName + " - invalid firstname");
        }

        final String lastName = employeeRequest.getLastName().trim().toLowerCase();

        if (isNotValidName(lastName)) {
            throw new InvalidNameException(lastName + " - invalid lastname");
        }

        Employee employee = new Employee(
                StringUtils.capitalize(firstName),
                StringUtils.capitalize(lastName),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary()
        );

        employeeRepository.getEmployees().put(employee.getId(), employee);

        return employee;
    }

    public Employee findEmployeeById(int id) {
        return this.employeeRepository.getEmployees().get(id);
    }

    public void removeEmployeeById(int id) {
        this.employeeRepository.getEmployees().remove(id);
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

    private static boolean isNotValidName(String name) {
        return !StringUtils.isAlpha(name);
    }

}
