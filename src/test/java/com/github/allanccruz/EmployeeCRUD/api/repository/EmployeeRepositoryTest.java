package com.github.allanccruz.EmployeeCRUD.api.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.allanccruz.EmployeeCRUD.api.entities.Employee;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("tests")
@DataJpaTest
@ExtendWith(SpringExtension.class)
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    static Employee createEmployee() {
        return Employee.builder()
                .name("Allan")
                .lastName("Chaves Cruz")
                .email("allan@gmail.com")
                .nisPis("12345678907")
                .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .build();
    }

    @Test
    @DisplayName("Must save and flush an employee successfully.")
    void saveAndFlush() {
        Employee employee = createEmployee();

        Employee savedEmployee = employeeRepository.saveAndFlush(employee);

        assertThat(savedEmployee.getId()).isNotNull();
        assertEquals(savedEmployee.getName(), employee.getName());
        assertEquals(savedEmployee.getLastName(), employee.getLastName());
        assertEquals(savedEmployee.getEmail(), employee.getEmail());
        assertEquals(savedEmployee.getNisPis(), employee.getNisPis());
        assertEquals(savedEmployee.getCreatedAt(), employee.getCreatedAt());
        assertEquals(savedEmployee.getUpdatedAt(), employee.getUpdatedAt());
    }

    @Test
    @DisplayName("Must save an employee successfully.")
    void save() {
        Employee employee = createEmployee();

        Employee savedEmployee = employeeRepository.save(employee);

        assertThat(savedEmployee.getId()).isNotNull();
        assertEquals(savedEmployee.getName(), employee.getName());
        assertEquals(savedEmployee.getLastName(), employee.getLastName());
        assertEquals(savedEmployee.getEmail(), employee.getEmail());
        assertEquals(savedEmployee.getNisPis(), employee.getNisPis());
        assertEquals(savedEmployee.getCreatedAt(), employee.getCreatedAt());
        assertEquals(savedEmployee.getUpdatedAt(), employee.getUpdatedAt());
    }

    @Test
    @DisplayName("Must find an employee by id successfully.")
    void findById() {
        Employee employee = createEmployee();
        employeeRepository.save(employee);

        Optional<Employee> foundEmployee = employeeRepository.findById(employee.getId());

        assertThat(foundEmployee).isPresent();
    }

    @Test
    @DisplayName("Must delete an employee by id successfully.")
    void delete() {
        Employee employee = createEmployee();
        employeeRepository.save(employee);
        Optional<Employee> foundEmployee = employeeRepository.findById(employee.getId());

        employeeRepository.deleteById(foundEmployee.get().getId());

        Optional<Employee> deletedEmployee = employeeRepository.findById(employee.getId());

        assertThat(deletedEmployee).isEmpty();

    }
}