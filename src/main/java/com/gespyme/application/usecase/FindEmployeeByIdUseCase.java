package com.gespyme.application.usecase;

import com.gespyme.domain.model.Employee;

public interface FindEmployeeByIdUseCase {
    Employee getEmployeeById(String employeeId);
}
