package com.gespyme.infrastructure.adapters.input.controller;

import com.gespyme.application.usecase.*;
import com.gespyme.commons.model.employee.EmployeeBaseModelApi;
import com.gespyme.commons.model.employee.EmployeeFilterModelApi;
import com.gespyme.commons.model.employee.EmployeeModelApi;
import com.gespyme.commons.validator.Validator;
import com.gespyme.commons.validator.ValidatorService;
import com.gespyme.domain.model.Employee;
import com.gespyme.domain.model.EmployeeFilter;
import com.gespyme.infrastructure.mapper.EmployeeInfrastructureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employee")
public class EmployeeController {
    private final EmployeeInfrastructureMapper employeeMapper;
    private final FindEmployeesUseCase findEmployeesUseCase;
    private final FindEmployeeByIdUseCase findEmployeeByIdUseCase;
    private final DeleteEmployeeUseCase deleteEmployeeUseCase;
    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final ModifyEmployeeUseCase modifyEmployeesUseCase;

    private final ValidatorService<EmployeeBaseModelApi> validator;


    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeModelApi> getEmployeeById(@PathVariable("employeeId") String employeeId) {
        validator.validateId(employeeId);
        Employee employee = findEmployeeByIdUseCase.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeMapper.map(employee));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeModelApi>> findEmployees(@RequestBody EmployeeFilterModelApi employeeFilterModelApi) {
        validator.validate(employeeFilterModelApi, List.of(Validator.ONE_PARAM_NOT_NULL));
        EmployeeFilter employeeFilter = employeeMapper.map(employeeFilterModelApi);
        List<Employee> employees = findEmployeesUseCase.findEmployee(employeeFilter);
        return ResponseEntity.ok(employeeMapper.map(employees));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") String employeeId) {
        validator.validateId(employeeId);
        deleteEmployeeUseCase.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmployeeModelApi> createEmployee(@RequestBody EmployeeModelApi EmployeeModelApi) {
        validator.validate(EmployeeModelApi, List.of(Validator.ALL_PARAMS_NOT_NULL));
        Employee employee = createEmployeeUseCase.createEmployee(employeeMapper.map(EmployeeModelApi));
        URI location = URI.create("/employee/" + employee.getEmployeeId());
        return ResponseEntity.created(location).body(employeeMapper.map(employee));
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeModelApi> modifyEmployee(@PathVariable("employeeId") String employeeId, @RequestBody EmployeeModelApi employeeModelApi) {
        validator.validateId(employeeId);
        validator.validate(employeeModelApi, List.of(Validator.ONE_PARAM_NOT_NULL));
        Employee employee = modifyEmployeesUseCase.modifyEmployee(employeeId, employeeMapper.map(employeeModelApi));
        return ResponseEntity.ok(employeeMapper.map(employee));
    }
}
