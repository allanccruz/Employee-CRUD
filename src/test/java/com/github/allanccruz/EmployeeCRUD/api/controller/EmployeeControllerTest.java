package com.github.allanccruz.EmployeeCRUD.api.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.allanccruz.EmployeeCRUD.api.dto.request.EmployeeRequest;
import com.github.allanccruz.EmployeeCRUD.api.dto.response.EmployeeResponse;
import com.github.allanccruz.EmployeeCRUD.api.exceptions.NotFoundException;
import com.github.allanccruz.EmployeeCRUD.api.service.impl.EmployeeServiceImpl;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@ActiveProfiles("tests")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    static String API = "/api/employees";

    static UUID id = UUID.randomUUID();

    @Autowired
    MockMvc mvc;

    @MockBean
    EmployeeServiceImpl employeeService;

    @MockBean
    ModelMapper modelMapper;

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


    @Test
    @DisplayName("Must create an employee successfully.")
    void createEmployee() throws Exception {

        EmployeeRequest employeeRequest = createEmployeeRequest();
        EmployeeResponse employeeResponse = createEmployeeResponse();

        when(employeeService.save(any(EmployeeRequest.class))).thenReturn(employeeResponse);

        String requestBody = new ObjectMapper().writeValueAsString(employeeRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(employeeResponse.getId().toString()))
                .andExpect(jsonPath("name").value(employeeResponse.getName()))
                .andExpect(jsonPath("lastName").value(employeeResponse.getLastName()))
                .andExpect(jsonPath("email").value(employeeResponse.getEmail()))
                .andExpect(jsonPath("nisPis").value(employeeResponse.getNisPis()))
                .andExpect(jsonPath("createdAt").value(employeeResponse.getCreatedAt().toString()))
                .andExpect(jsonPath("updatedAt").value(employeeResponse.getUpdatedAt().toString()));

    }

    @Test
    @DisplayName("Must not create an employee and throw an exception with a list of errors of failed attributes validations.")
    void invalidEmployeeRequest() throws Exception {

        EmployeeRequest employeeRequest = new EmployeeRequest();

        String requestBody = new ObjectMapper().writeValueAsString(employeeRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mvc
                .perform(request)
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("httpCode").isNumber())
                .andExpect(jsonPath("message").isString())
                .andExpect(jsonPath("internalCode").isString())
                .andExpect(jsonPath("path").isString())
                .andExpect(jsonPath("timestamp").isString())
                .andExpect(jsonPath("errors").isArray())
                .andExpect(jsonPath("errors", hasSize(4)));

    }

    @Test
    @DisplayName("Must return an employee successfully.")
    void getEmployeeById() throws Exception {

        EmployeeResponse employeeResponse = createEmployeeResponse();

        when(employeeService.getById(any(UUID.class))).thenReturn(employeeResponse);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(API.concat("/" + id))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(employeeResponse.getId().toString()))
                .andExpect(jsonPath("name").value(employeeResponse.getName()))
                .andExpect(jsonPath("lastName").value(employeeResponse.getLastName()))
                .andExpect(jsonPath("email").value(employeeResponse.getEmail()))
                .andExpect(jsonPath("nisPis").value(employeeResponse.getNisPis()))
                .andExpect(jsonPath("createdAt").value(employeeResponse.getCreatedAt().toString()))
                .andExpect(jsonPath("updatedAt").value(employeeResponse.getUpdatedAt().toString()));
    }

    @Test
    @DisplayName("Must return NotFoundException when trying find an employee that doesn't exist.")
    void employeeNotFound() throws Exception {

        when(employeeService.getById(any(UUID.class))).thenThrow(NotFoundException.class);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(API.concat("/" + id))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Must update an employee successfully.")
    void updateEmployee() throws Exception {

        EmployeeRequest employeeRequest = createEmployeeRequest();
        EmployeeResponse employeeResponse = createEmployeeResponse();

        when(employeeService.update(any(UUID.class), any(EmployeeRequest.class))).thenReturn(employeeResponse);

        String requestBody = new ObjectMapper().writeValueAsString(employeeRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(API.concat("/" + id))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(employeeResponse.getId().toString()))
                .andExpect(jsonPath("name").value(employeeResponse.getName()))
                .andExpect(jsonPath("lastName").value(employeeResponse.getLastName()))
                .andExpect(jsonPath("email").value(employeeResponse.getEmail()))
                .andExpect(jsonPath("nisPis").value(employeeResponse.getNisPis()))
                .andExpect(jsonPath("createdAt").value(employeeResponse.getCreatedAt().toString()))
                .andExpect(jsonPath("updatedAt").value(employeeResponse.getUpdatedAt().toString()));
    }

    @Test
    @DisplayName("Must not update an employee and throw an exception with a list of errors of failed attributes validations.")
    void invalidEmployeeUpdateRequest() throws Exception {

        EmployeeRequest employeeRequest = new EmployeeRequest();

        String requestBody = new ObjectMapper().writeValueAsString(employeeRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(API.concat("/" + id))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mvc
                .perform(request)
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("httpCode").isNumber())
                .andExpect(jsonPath("message").isString())
                .andExpect(jsonPath("internalCode").isString())
                .andExpect(jsonPath("path").isString())
                .andExpect(jsonPath("timestamp").isString())
                .andExpect(jsonPath("errors").isArray())
                .andExpect(jsonPath("errors", hasSize(4)));

    }

    @Test
    void deleteEmployee() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(API.concat("/" + id))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isNoContent());
    }
}