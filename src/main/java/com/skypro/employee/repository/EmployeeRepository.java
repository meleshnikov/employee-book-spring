package com.skypro.employee.repository;

import com.skypro.employee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepository {

    private final Map<Integer, Employee> employees = new HashMap<>();

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

}
