package com.example.employeemanagementapplication.dto;

import com.example.employeemanagementapplication.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
