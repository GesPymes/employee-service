package com.gespyme.application.port.input;

import com.gespyme.application.usecase.CreateEmployeeUseCase;
import com.gespyme.commons.model.user.UserModelApi;
import com.gespyme.domain.facade.UserFacade;
import com.gespyme.domain.model.Employee;
import com.gespyme.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateEmployeePort implements CreateEmployeeUseCase {
    private final EmployeeRepository repository;
    private final UserFacade userFacade;

    public Employee createEmployee(Employee employee) {
        UserModelApi userModelApi = userFacade.getUserModelApi(employee.getEmail());
        Employee toSave = employee.toBuilder().userId(userModelApi.getId()).build();
        return repository.save(toSave);
    }
}
