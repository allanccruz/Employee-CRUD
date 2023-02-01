package com.github.allanccruz.EmployeeCRUD.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {

    EC001("EC-001", "Invalid request."),
    EC002("EC-002", "Email or NisPis already in use by someone else."),
    EC101("EC-101", "This Employee does not exists.");

    private final String code;

    private final String message;

}
