package com.skypro.employee.repository;

import com.skypro.employee.model.Employee;

import java.util.Collection;

public interface EmployeeRepository {

    Employee add(Employee employee);

    Employee remove(int id);

    Employee find(int id);

    Collection<Employee> getAll();

}
