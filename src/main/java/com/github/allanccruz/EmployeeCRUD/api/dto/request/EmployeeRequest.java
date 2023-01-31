package com.github.allanccruz.EmployeeCRUD.api.dto.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    @NotBlank(message = "Name field cannot be blank")
    @Size(min = 2, max = 30, message = "Name field has a min/max range of 2 and 30 characters")
    private String name;

    @NotBlank(message = "Last name field cannot be blank")
    @Size(min = 2, max = 50, message = "Last name field has a min/max range of 2 and 50 characters")
    private String lastName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email field cannot be blank")
    private String email;

    @NotBlank(message = "NisPis field cannot be blank")
    @Size(min = 11, max = 11, message = "NisPis field requires an 11-digit number")
    @Digits(integer = 11, fraction = 0, message = "NisPis field accepts only digits")
    private String nisPis;

}
