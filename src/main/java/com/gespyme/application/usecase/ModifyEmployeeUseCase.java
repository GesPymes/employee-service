package com.gespyme.application.usecase;

import com.gespyme.domain.model.Employee;

public interface ModifyEmployeeUseCase {
    Employee modifyEmployee(String employeeId, Employee newEmployeeData);
}
