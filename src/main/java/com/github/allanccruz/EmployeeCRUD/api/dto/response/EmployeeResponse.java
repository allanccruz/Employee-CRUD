package com.github.allanccruz.EmployeeCRUD.api.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private UUID id;

    private String name;

    private String lastName;

    private String email;

    private String nisPis;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
