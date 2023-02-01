package com.github.allanccruz.EmployeeCRUD.api.service.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.allanccruz.EmployeeCRUD.api.dto.request.EmployeeRequest;
import com.github.allanccruz.EmployeeCRUD.api.dto.response.EmployeeResponse;
import com.github.allanccruz.EmployeeCRUD.api.entities.Employee;
import com.github.allanccruz.EmployeeCRUD.api.repository.EmployeeRepository;
import com.github.allanccruz.EmployeeCRUD.api.service.EmployeeService;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@AutoConfigureMockMvc
@ActiveProfiles("tests")
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {
    static UUID id = UUID.randomUUID();

    EmployeeService employeeService;

    @MockBean
    ModelMapper mapper;

    @MockBean
    EmployeeRepository employeeRepository;


    static EmployeeRequest createEmployeeRequest() {
        return EmployeeRequest.builder()
                .name("Allan")
                .lastName("Chaves Cruz")
                .email("allan@gmail.com")
                .nisPis("12345678907")
                .build();
    }

    static EmployeeResponse createEmployeeResponse() {
        return EmployeeResponse.builder()
                .id(id)
                .name("Allan")
                .lastName("Chaves Cruz")
                .email("allan@gmail.com")
                .nisPis("12345678907")
                .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .build();
    }

    static Employee createEmployee() {
        return Employee.builder()
                .id(id)
                .name("Allan")
                .lastName("Chaves Cruz")
                .email("allan@gmail.com")
                .nisPis("12345678907")
                .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .build();
    }

    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeServiceImpl(mapper, employeeRepository);
    }

    @Test
    @DisplayName("Must save an employee successfully.")
    void saveEmployee() {
        Employee employee = createEmployee();
        EmployeeRequest employeeRequest = createEmployeeRequest();
        EmployeeResponse expectedEmployeeResponse = createEmployeeResponse();

        when(employeeRepository.saveAndFlush(any(Employee.class))).thenReturn(employee);
        when(mapper.map(employeeRequest, Employee.class)).thenReturn(employee);
        when(mapper.map(employee, EmployeeResponse.class)).thenReturn(expectedEmployeeResponse);

        EmployeeResponse actualEmployeeResponse = employeeService.save(employeeRequest);

        assertThat(actualEmployeeResponse.getId()).isNotNull();
        assertThat(actualEmployeeResponse.getName()).isEqualTo(expectedEmployeeResponse.getName());
        assertThat(actualEmployeeResponse.getLastName()).isEqualTo(expectedEmployeeResponse.getLastName());
        assertThat(actualEmployeeResponse.getEmail()).isEqualTo(expectedEmployeeResponse.getEmail());
        assertThat(actualEmployeeResponse.getNisPis()).isEqualTo(expectedEmployeeResponse.getNisPis());
        assertThat(actualEmployeeResponse.getCreatedAt()).isEqualTo(expectedEmployeeResponse.getCreatedAt());
        assertThat(actualEmployeeResponse.getUpdatedAt()).isEqualTo(expectedEmployeeResponse.getUpdatedAt());

    }

    @Test
    @DisplayName("Must get an employee by id successfully.")
    void getById() {
        Employee employee = createEmployee();
        EmployeeRequest employeeRequest = createEmployeeRequest();
        EmployeeResponse expectedEmployeeResponse = createEmployeeResponse();

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(mapper.map(employeeRequest, Employee.class)).thenReturn(employee);
        when(mapper.map(employee, EmployeeResponse.class)).thenReturn(expectedEmployeeResponse);

        EmployeeResponse actualEmployeeResponse = employeeService.getById(id);

        assertThat(actualEmployeeResponse.getId()).isEqualTo(expectedEmployeeResponse.getId());
        assertThat(actualEmployeeResponse.getName()).isEqualTo(expectedEmployeeResponse.getName());
        assertThat(actualEmployeeResponse.getLastName()).isEqualTo(expectedEmployeeResponse.getLastName());
        assertThat(actualEmployeeResponse.getEmail()).isEqualTo(expectedEmployeeResponse.getEmail());
        assertThat(actualEmployeeResponse.getNisPis()).isEqualTo(expectedEmployeeResponse.getNisPis());
        assertThat(actualEmployeeResponse.getCreatedAt()).isEqualTo(expectedEmployeeResponse.getCreatedAt());
        assertThat(actualEmployeeResponse.getUpdatedAt()).isEqualTo(expectedEmployeeResponse.getUpdatedAt());
    }

    @Test
    @DisplayName("Must update an employee successfully.")
    void update() {
        Employee employee = createEmployee();
        EmployeeRequest employeeRequest = createEmployeeRequest();
        EmployeeResponse expectedEmployeeResponse = createEmployeeResponse();

        when(employeeRepository.findById(id)).thenReturn(java.util.Optional.of(employee));
        when(mapper.map(any(EmployeeResponse.class), eq(Employee.class))).thenReturn(employee);
        when(mapper.map(any(Employee.class), eq(EmployeeResponse.class))).thenReturn(expectedEmployeeResponse);

        EmployeeResponse actualEmployeeResponse = employeeService.update(id, employeeRequest);

        assertThat(actualEmployeeResponse.getId()).isEqualTo(expectedEmployeeResponse.getId());
        assertThat(actualEmployeeResponse.getName()).isEqualTo(expectedEmployeeResponse.getName());
        assertThat(actualEmployeeResponse.getLastName()).isEqualTo(expectedEmployeeResponse.getLastName());
        assertThat(actualEmployeeResponse.getEmail()).isEqualTo(expectedEmployeeResponse.getEmail());
        assertThat(actualEmployeeResponse.getNisPis()).isEqualTo(expectedEmployeeResponse.getNisPis());
        assertThat(actualEmployeeResponse.getCreatedAt()).isEqualTo(expectedEmployeeResponse.getCreatedAt());
        assertThat(actualEmployeeResponse.getUpdatedAt()).isEqualTo(expectedEmployeeResponse.getUpdatedAt());

    }

    @Test
    @DisplayName("Must delete an employee successfully.")
    void deleteById() {
        Employee employee = createEmployee();
        EmployeeResponse expectedEmployeeResponse = createEmployeeResponse();

        when(employeeRepository.findById(id)).thenReturn(java.util.Optional.of(employee));
        when(mapper.map(any(EmployeeResponse.class), eq(Employee.class))).thenReturn(employee);
        when(mapper.map(any(Employee.class), eq(EmployeeResponse.class))).thenReturn(expectedEmployeeResponse);

        employeeService.deleteById(employee.getId());

        verify(employeeRepository, times(1)).deleteById(employee.getId());
    }
}