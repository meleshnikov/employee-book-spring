package com.skypro.employee.service;

import com.skypro.employee.exception.EmployeeNotExistException;
import com.skypro.employee.exception.InvalidNameException;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.repository.EmployeeRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(new EmployeeRepositoryImpl());
        employeeService.addEmployee(new EmployeeRequest("Ivan", "Ivanov", 1, 20_000));
    }

    @Test
    void addEmployee() {

        EmployeeRequest employeeRequest = new EmployeeRequest(
                "test",
                "test",
                1,
                50_000);
        employeeService.addEmployee(employeeRequest);
        Assertions.assertEquals(2, employeeService.getAllEmployees().size());
    }

    @Test
    void addEmployeeWithInvalidFirstNameShouldThrowException() {

        EmployeeRequest employeeRequest = new EmployeeRequest(
                "te123",
                "test",
                1,
                50_000);
        Assertions.assertThrows(InvalidNameException.class, () -> employeeService.addEmployee(employeeRequest));
    }

    @Test
    void addEmployeeWithInvalidLastNameShouldThrowException() {

        EmployeeRequest employeeRequest = new EmployeeRequest(
                "test",
                " 4 est",
                1,
                50_000);
        Assertions.assertThrows(InvalidNameException.class, () -> employeeService.addEmployee(employeeRequest));
    }

    @Test
    void findEmployeeByIdShouldThrowException() {
        Assertions.assertThrows(EmployeeNotExistException.class, () -> employeeService.findEmployeeById(4));
    }

    @Test
    void findEmployeeByIdShouldReturnNotNullEmployee() throws EmployeeNotExistException {
        Assertions.assertNotNull(employeeService.findEmployeeById(1));
    }

    @Test
    void removeEmployeeByIdShouldThrowException() {
        Assertions.assertThrows(EmployeeNotExistException.class, () -> employeeService.removeEmployeeById(10));
    }

    @Test
    void removeEmployeeByIdShouldDecreaseSize() throws EmployeeNotExistException {
        employeeService.removeEmployeeById(1);
        Assertions.assertEquals(0, employeeService.getAllEmployees().size());
    }

}