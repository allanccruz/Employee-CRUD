package com.github.allanccruz.EmployeeCRUD.api.service.impl;

import com.github.allanccruz.EmployeeCRUD.api.dto.request.EmployeeRequest;
import com.github.allanccruz.EmployeeCRUD.api.dto.response.EmployeeResponse;
import com.github.allanccruz.EmployeeCRUD.api.entities.Employee;
import com.github.allanccruz.EmployeeCRUD.api.enums.Errors;
import com.github.allanccruz.EmployeeCRUD.api.exceptions.NotFoundException;
import com.github.allanccruz.EmployeeCRUD.api.repository.EmployeeRepository;
import com.github.allanccruz.EmployeeCRUD.api.service.EmployeeService;
import java.time.LocalDateTime;
import java.util.UUID;
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

    @Override
    public EmployeeResponse getById(UUID id) {
        return mapper.map(employeeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(Errors.EC101.getMessage(), Errors.EC101.getCode())), EmployeeResponse.class);
    }

    @Override
    @Transactional
    public EmployeeResponse update(UUID id, EmployeeRequest employeeRequest) {
        Employee employee = mapper.map(getById(id), Employee.class);
        setNewEmployeeAttributes(employeeRequest, employee);
        employeeRepository.save(employee);
        return mapper.map(employee, EmployeeResponse.class);
    }

    @Override
    public void deleteById(UUID id) {
        Employee employee = mapper.map(getById(id), Employee.class);
        employeeRepository.deleteById(employee.getId());
    }

    private void setNewEmployeeAttributes(EmployeeRequest employeeRequest, Employee employee) {

        employee.setName(employeeRequest.getName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setNisPis(employeeRequest.getNisPis());
        employee.setUpdatedAt(LocalDateTime.now().withNano(0));

    }
}
