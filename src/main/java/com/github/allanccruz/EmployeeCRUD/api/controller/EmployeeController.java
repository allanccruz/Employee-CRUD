package com.github.allanccruz.EmployeeCRUD.api.controller;

import com.github.allanccruz.EmployeeCRUD.api.dto.request.EmployeeRequest;
import com.github.allanccruz.EmployeeCRUD.api.dto.response.EmployeeResponse;
import com.github.allanccruz.EmployeeCRUD.api.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

    private final ModelMapper mapper;

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.save(employeeRequest);
    }

}
