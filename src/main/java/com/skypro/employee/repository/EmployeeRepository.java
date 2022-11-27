package com.skypro.employee.repository;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;

import java.util.Collection;

public interface EmployeeRepository {

    Employee add(EmployeeRequest employeeRequest);

    String remove(int id);

    Employee find(int id);

    Collection<Employee> getAll();

}
