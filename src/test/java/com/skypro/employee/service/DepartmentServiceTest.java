package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        Employee employee1 = new Employee("Ivan", "Ivanov", 1, 20_000);
        Employee employee2 = new Employee("Oleg", "Petrov", 1, 40_000);
        Employee employee3 = new Employee("Petr", "Smirnov", 2, 50_000);
        Employee employee4 = new Employee("Andrey", "Test", 2, 30_000);
        Employee employee5 = new Employee("Alex", "Test", 2, 90_000);
        when(employeeService.getAllEmployees()).thenReturn(Set.of(employee1, employee2, employee3, employee4, employee5));
    }

    @Test
    void getEmployeesByFirstDepartmentShouldReturnTwoEmployees() {
        assertEquals(2, departmentService.getEmployeesByDepartment(1).size());
    }

    @Test
    void getEmployeesByNotExistDepartmentShouldReturnEmptyList() {
        assertEquals(0, departmentService.getEmployeesByDepartment(10).size());
    }

    @Test
    void getSalarySumBySecondDepartmentShouldReturn170k() {
        assertEquals(170_000, departmentService.getSalarySumByDepartment(2));
    }

    @Test
    void getSalarySumByNotExistDepartmentShouldReturnZero() {
        assertEquals(0, departmentService.getSalarySumByDepartment(10));
    }

    @Test
    void getSalaryMinByFirstDepartmentShouldReturn20k() {
        assertEquals(20_000, departmentService.getSalaryMinByDepartment(1));
    }

    @Test
    void getSalaryMinByNotExistDepartmentShouldReturnZero() {
        assertEquals(0, departmentService.getSalaryMinByDepartment(-2));
    }

    @Test
    void getSalaryMaxByFirstDepartmentShouldReturn40k() {
        assertEquals(40_000, departmentService.getSalaryMaxByDepartment(1));
    }

    @Test
    void getSalaryMaxByNotExistDepartmentShouldReturnZero() {
        assertEquals(0, departmentService.getSalaryMaxByDepartment(100));
    }

    @Test
    void getEmployeesGroupedByDepartmentTest() {
        Map<Integer, List<Employee>> groupedEmployees = departmentService.getEmployeesGroupedByDepartment();
        assertEquals(2, groupedEmployees.get(1).size());
        assertEquals(3, groupedEmployees.get(2).size());
    }

}