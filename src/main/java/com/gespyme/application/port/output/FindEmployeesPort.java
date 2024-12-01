package com.gespyme.application.port.output;

import com.gespyme.application.usecase.FindEmployeesUseCase;
import com.gespyme.commons.model.filter.FieldFilter;
import com.gespyme.commons.repository.criteria.SearchCriteria;
import com.gespyme.domain.model.Employee;
import com.gespyme.domain.model.EmployeeFilter;
import com.gespyme.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindEmployeesPort implements FindEmployeesUseCase {
    private final EmployeeRepository employeeRepository;
    private final List<FieldFilter<EmployeeFilter>> employeeFilters;

    public FindEmployeesPort(EmployeeRepository employeeRepository, List<FieldFilter<EmployeeFilter>> employeeFilters) {
        this.employeeRepository = employeeRepository;
        this.employeeFilters = employeeFilters;
    }

    public List<Employee> findEmployee(EmployeeFilter employeeFilter) {
        List<SearchCriteria> searchCriterias = new ArrayList<>();
        employeeFilters.stream().filter(f -> f.apply(employeeFilter)).forEach(f -> f.addSearchCriteria(employeeFilter, searchCriterias));
        return employeeRepository.findByCriteria(searchCriterias);
    }
}
