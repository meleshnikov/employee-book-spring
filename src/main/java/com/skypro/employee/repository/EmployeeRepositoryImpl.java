package com.skypro.employee.repository;

import com.skypro.employee.exception.EmployeeNotExistException;
import com.skypro.employee.exception.InvalidNameException;
import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final Map<Integer, Employee> employees = new HashMap<>();

    @Override
    public Employee add(EmployeeRequest employeeRequest) {

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

        this.employees.put(employee.getId(), employee);

        return employee;
    }

    @Override
    public String remove(int id) {
        Employee employee = this.employees.remove(id);
        if (employee == null) {
            throw new EmployeeNotExistException("Employee with id " + id + " not found!");
        }
        return employee + " was removed!";
    }

    @Override
    public Employee find(int id) {
        Employee employee = this.employees.get(id);
        if (employee == null) {
            throw new EmployeeNotExistException("Employee with id " + id + " not found!");
        }
        return employee;
    }

    @Override
    public Collection<Employee> getAll() {
        return this.employees.values();
    }

    private static boolean isNotValidName(String name) {
        return !StringUtils.isAlpha(name);
    }
}
