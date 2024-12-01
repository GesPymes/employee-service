package com.gespyme.application.usecase;

import com.gespyme.domain.model.Employee;
import com.gespyme.domain.model.EmployeeFilter;

import java.util.List;

public interface FindEmployeesUseCase {
    List<Employee> findEmployee(EmployeeFilter employeeFilter);
}
