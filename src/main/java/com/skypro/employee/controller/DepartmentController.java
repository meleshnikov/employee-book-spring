package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployeesByDepartment(@RequestParam int department) {
        return this.departmentService.getEmployeesByDepartment(department);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSalarySumByDepartment(@PathVariable int id) {
        return this.departmentService.getSalarySumByDepartment(id);
    }

    @GetMapping("/{id}/salary/min")
    public int getSalaryMinByDepartment(@PathVariable int id) {
        return this.departmentService.getSalaryMinByDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public int getSalaryMaxByDepartment(@PathVariable int id) {
        return this.departmentService.getSalaryMaxByDepartment(id);
    }

    @GetMapping("/grouped")
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return this.departmentService.getEmployeesGroupedByDepartment();
    }

}
