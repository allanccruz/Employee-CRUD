package com.github.allanccruz.EmployeeCRUD.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private Integer httpCode;

    private String message;

    private String internalCode;

    private String path;

    private LocalDateTime timestamp;

    private List<FieldErrorResponse> errors;
}
