package com.github.allanccruz.EmployeeCRUD.api.service;

import com.github.allanccruz.EmployeeCRUD.api.dto.request.EmployeeRequest;
import com.github.allanccruz.EmployeeCRUD.api.dto.response.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse save(EmployeeRequest employeeRequest);
}
