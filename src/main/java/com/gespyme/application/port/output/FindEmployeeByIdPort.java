package com.gespyme.application.port.output;

import com.gespyme.application.usecase.FindEmployeeByIdUseCase;
import com.gespyme.commons.exeptions.NotFoundException;
import com.gespyme.domain.model.Employee;
import com.gespyme.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindEmployeeByIdPort implements FindEmployeeByIdUseCase {
    private final EmployeeRepository repository;

    public Employee getEmployeeById(String employeeId) {
        Optional<Employee> employee = repository.findById(employeeId);
        if (employee.isEmpty()) {
            throw new NotFoundException("Employee " + employeeId + " not found");
        }
        return employee.get();
    }
}
