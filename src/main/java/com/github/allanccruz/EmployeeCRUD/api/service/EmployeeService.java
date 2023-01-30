package com.github.allanccruz.EmployeeCRUD.api.service;

import com.github.allanccruz.EmployeeCRUD.api.dto.request.EmployeeRequest;
import com.github.allanccruz.EmployeeCRUD.api.dto.response.EmployeeResponse;
import java.util.UUID;

public interface EmployeeService {
    EmployeeResponse save(EmployeeRequest employeeRequest);

    EmployeeResponse getById(UUID id);

    EmployeeResponse update(UUID id, EmployeeRequest employeeRequest);

    void deleteById(UUID id);
}
