package com.github.allanccruz.EmployeeCRUD.api.controller;

import com.github.allanccruz.EmployeeCRUD.api.dto.request.EmployeeRequest;
import com.github.allanccruz.EmployeeCRUD.api.dto.response.EmployeeResponse;
import com.github.allanccruz.EmployeeCRUD.api.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
@Tag(name = "Employee Controller")
public class EmployeeController {

    private final ModelMapper mapper;

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register employee")
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.save(employeeRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get employee by id")
    public EmployeeResponse getEmployeeById(@PathVariable UUID id) {
        return employeeService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update employee by id")
    public EmployeeResponse updateEmployee(@PathVariable UUID id, @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.update(id, employeeRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete employee by id")
    public void deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteById(id);
    }

}
