package com.github.allanccruz.EmployeeCRUD.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeRequest {

    private String name;

    private String lastName;

    private String email;

    private String nisPis;

}
