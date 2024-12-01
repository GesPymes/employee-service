package com.gespyme.application.port.input;

import com.gespyme.application.usecase.DeleteEmployeeUseCase;
import com.gespyme.commons.exeptions.NotFoundException;
import com.gespyme.domain.model.Employee;
import com.gespyme.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeleteEmployeePort implements DeleteEmployeeUseCase {
    private final EmployeeRepository repository;

    public void deleteEmployee(String employeeId) {
        Optional<Employee> employee = repository.findById(employeeId);
        if (employee.isEmpty()) {
            throw new NotFoundException("Employee " + employeeId + " not found");
        }
        repository.deleteById(employeeId);
    }
}
