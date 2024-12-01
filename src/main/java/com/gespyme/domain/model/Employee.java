package com.gespyme.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Employee {
    private String employeeId;
    private String userId;
    private String name;
    private String lastName;
    private String address;
    private String mobilePhone;
    private String socialSecurityNumber;
    private String idNumber;
    private String email;
}
