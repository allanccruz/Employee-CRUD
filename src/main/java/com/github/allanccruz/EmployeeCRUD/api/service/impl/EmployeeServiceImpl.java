package com.github.allanccruz.EmployeeCRUD.api.service.impl;

import com.github.allanccruz.EmployeeCRUD.api.dto.request.EmployeeRequest;
import com.github.allanccruz.EmployeeCRUD.api.dto.response.EmployeeResponse;
import com.github.allanccruz.EmployeeCRUD.api.entities.Employee;
import com.github.allanccruz.EmployeeCRUD.api.repository.EmployeeRepository;
import com.github.allanccruz.EmployeeCRUD.api.service.EmployeeService;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper mapper;

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public EmployeeResponse save(EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.saveAndFlush(mapper.map(employeeRequest, Employee.class));
        return mapper.map(employee, EmployeeResponse.class);
    }
}
