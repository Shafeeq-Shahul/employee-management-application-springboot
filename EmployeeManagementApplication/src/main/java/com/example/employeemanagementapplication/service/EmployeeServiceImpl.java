package com.example.employeemanagementapplication.service;

import com.example.employeemanagementapplication.exception.DuplicateEmployeeException;
import com.example.employeemanagementapplication.exception.EmployeeNotFoundException;
import com.example.employeemanagementapplication.exception.NoEmployeeFoundInDatabaseException;
import com.example.employeemanagementapplication.model.Employee;
import com.example.employeemanagementapplication.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> isEmptyEmployeeList = employeeRepository.findAll();
        if (isEmptyEmployeeList.isEmpty()) {
            throw new NoEmployeeFoundInDatabaseException("No Employee Found in the Database");
        }
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with email " + email));
    }

    @Override
    public void save(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new DuplicateEmployeeException("Duplicate Entry with email " + employee.getEmail());
        }
        Employee savedEmployee = new Employee();
        savedEmployee.setId(employee.getId());
        savedEmployee.setName(employee.getName());
        savedEmployee.setEmail(employee.getEmail());
        savedEmployee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));;
        savedEmployee.setRole(employee.getRole());
        employeeRepository.save(savedEmployee);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPassword(employee.getPassword());
        existingEmployee.setRole(employee.getRole());
        return employeeRepository.save(existingEmployee);
    }

    @Override
    @Transactional
    public List<Employee> saveAll(List<Employee> employees) {
        Set<String> seenEmails = new HashSet<>();
        List<Employee> savedEmployees = employees.stream()
                .filter(filterEmployee -> {
                    boolean isNewEmployeeInDB = !employeeRepository.existsByEmail(filterEmployee.getEmail());
                    boolean isUniqueInBatch = seenEmails.add(filterEmployee.getEmail());
                    return isNewEmployeeInDB && isUniqueInBatch;
                })
                .map(mapEmployee -> {
                    Employee newEmployee = new Employee();
                    newEmployee.setId(mapEmployee.getId());
                    newEmployee.setName(mapEmployee.getName());
                    newEmployee.setEmail(mapEmployee.getEmail());
                    newEmployee.setPassword(bCryptPasswordEncoder.encode(mapEmployee.getPassword()));
                    newEmployee.setRole(mapEmployee.getRole());
                    return newEmployee;
                })
                .collect(Collectors.toList());
        return employeeRepository.saveAll(savedEmployees);
    }

    @Override
    public void deleteById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id " + id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional // used for safe database operation
    public void deleteAll() {
        List<Employee> isEmptyEmployeeList = employeeRepository.findAll();
        if (isEmptyEmployeeList.isEmpty()) {
            throw new NoEmployeeFoundInDatabaseException("No Employee Found in the Database");
        }
        log.warn("Deleting all employee records");
        employeeRepository.deleteAll();
    }
}
