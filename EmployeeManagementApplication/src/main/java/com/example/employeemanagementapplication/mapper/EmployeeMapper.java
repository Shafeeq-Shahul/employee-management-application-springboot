package com.example.employeemanagementapplication.mapper;

import com.example.employeemanagementapplication.dto.EmployeeDTO;
import com.example.employeemanagementapplication.model.Employee;
import org.springframework.stereotype.Component;

// Mapper is used for mapping the objects across the layers of spring boot application.
@Component
public class EmployeeMapper {

    public EmployeeDTO toDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPassword(),
                employee.getRole()
        );
    }
    public Employee toEntity(EmployeeDTO employeeDTO) {
        return new Employee(
                employeeDTO.getId(),
                employeeDTO.getName(),
                employeeDTO.getEmail(),
                employeeDTO.getPassword(),
                employeeDTO.getRole()
        );
    }
}
