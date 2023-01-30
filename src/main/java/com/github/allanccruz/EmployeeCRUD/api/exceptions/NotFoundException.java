package com.github.allanccruz.EmployeeCRUD.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {

    private String message;

    private String errorCode;

}
