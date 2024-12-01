package com.gespyme.application.port.input;

import com.gespyme.application.usecase.ModifyEmployeeUseCase;
import com.gespyme.commons.exeptions.NotFoundException;
import com.gespyme.domain.model.Employee;
import com.gespyme.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ModifyEmployeePort implements ModifyEmployeeUseCase {
    private final EmployeeRepository repository;

    public Employee modifyEmployee(String employeeId, Employee newEmployeeData) {
        Optional<Employee> employee = repository.findById(employeeId);
        if (employee.isEmpty()) {
            throw new NotFoundException("Employee " + employeeId + " not found");
        }
        return repository.merge(newEmployeeData, employee.get());
    }
}
