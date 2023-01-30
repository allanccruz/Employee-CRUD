package com.github.allanccruz.EmployeeCRUD.api.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private String name;

    private String lastName;

    private String email;

    private String nisPis;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
