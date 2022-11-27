package com.skypro.employee.repository;

import com.skypro.employee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final Map<Integer, Employee> employees = new HashMap<>();

    @Override
    public Employee add(Employee employee) {
        Objects.requireNonNull(employee);
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public Employee remove(int id) {
        return this.employees.remove(id);
    }

    @Override
    public Employee find(int id) {
        return this.employees.get(id);

    }

    @Override
    public Collection<Employee> getAll() {
        return this.employees.values();
    }


}
