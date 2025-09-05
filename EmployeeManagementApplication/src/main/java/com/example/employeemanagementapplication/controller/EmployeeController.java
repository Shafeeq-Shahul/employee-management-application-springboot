package com.example.employeemanagementapplication.controller;

import com.example.employeemanagementapplication.dto.ApiResponse;
import com.example.employeemanagementapplication.dto.EmployeeDTO;
import com.example.employeemanagementapplication.mapper.EmployeeMapper;
import com.example.employeemanagementapplication.model.Employee;
import com.example.employeemanagementapplication.service.EmployeeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService,
                              EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper =  employeeMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<EmployeeDTO>> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee savedEmployee = employeeMapper.toEntity(employeeDTO);
        employeeService.save(savedEmployee);
        EmployeeDTO savedEmployeeDTO = employeeMapper.toDTO(savedEmployee);
        ApiResponse<EmployeeDTO> response = new ApiResponse<>(true, "Data saved successfully", savedEmployeeDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saveAll")
    @Transactional
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> saveEmployees(@Valid @RequestBody List<EmployeeDTO> employeesDTO) {
        List<Employee> savedEmployees = employeesDTO
                .stream()
                .map(employeeMapper::toEntity)
                .toList();
        List<Employee> employeeList = employeeService.saveAll(savedEmployees);
        List<EmployeeDTO> savedEmployeesDTO = employeeList
                .stream()
                .map(employeeMapper::toDTO)
                .toList();
        ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(true, "Data saved successfully", savedEmployeesDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.update(employeeDTO.getId(), employeeMapper.toEntity(employeeDTO));
        EmployeeDTO updatedEmployeeDTO = employeeMapper.toDTO(updatedEmployee);
        ApiResponse<EmployeeDTO> response = new ApiResponse<>(true, "data updated successfully", updatedEmployeeDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getEmployees() {
        List<EmployeeDTO> employeeDTOs = employeeService
                .findAll()
                .stream()
                .map(employeeMapper::toDTO)
                .toList();
        ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(true, "Data retrieved successfully", employeeDTOs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployee(@RequestParam Long id) {
        EmployeeDTO getEmployeeDTO = employeeMapper.toDTO(employeeService.findById(id));
        ApiResponse<EmployeeDTO> response = new ApiResponse<>(true, "Data retrieved successfully", getEmployeeDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeByEmail(@PathVariable String email) {
        EmployeeDTO getEmployeeDTO = employeeMapper.toDTO(employeeService.findByEmail(email));
        ApiResponse<EmployeeDTO> response = new ApiResponse<>(true, "Data retrieved successfully", getEmployeeDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@RequestParam Long id) {
        employeeService.deleteById(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Data deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<ApiResponse<Void>> deleteAllEmployees() {
        employeeService.deleteAll();
        ApiResponse<Void> response = new ApiResponse<>(true, "Data deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}