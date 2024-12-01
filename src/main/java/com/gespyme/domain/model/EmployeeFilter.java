package com.gespyme.domain.model;

import lombok.Data;

@Data
public class EmployeeFilter {
    private String name;
    private String email;
    private String lastName;
    private String address;
    private String mobilePhone;
    private String idNumber;
    private String socialSecurityNumber;
}
