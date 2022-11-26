package com.skypro.employee.model;

import java.util.Objects;

public class Employee implements Comparable<Integer> {
    private static int counter;
    private final int id;
    private final String firstName;
    private final String lastName;
    private final int department;
    private final int salary;

    public Employee(String firstName, String lastName, int department, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;

        this.id = ++counter;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("id: %4d| %s %s |отдел: %2d|ЗП: %6d руб%n",
                getId(),
                getFirstName(),
                getLastName(),
                getDepartment(),
                getSalary());
    }

    @Override
    public int compareTo(Integer o) {
        return Integer.valueOf(id).compareTo(o);
    }
}
