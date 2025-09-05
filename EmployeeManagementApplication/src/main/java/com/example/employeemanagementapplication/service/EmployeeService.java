package com.example.employeemanagementapplication.service;

import com.example.employeemanagementapplication.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(Long id);

    List<Employee> findAll();

    Employee findByEmail(String email);

    void save(Employee employee);

    Employee update(Long id, Employee employee);

    List<Employee> saveAll(List<Employee> employees);

    void deleteById(Long id);

    void deleteAll();
}
