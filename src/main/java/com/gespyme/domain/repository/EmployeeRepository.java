package com.gespyme.domain.repository;

import com.gespyme.commons.repository.GenericRepository;
import com.gespyme.commons.repository.criteria.SearchCriteria;
import com.gespyme.domain.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeRepository extends GenericRepository<Employee> {
    List<Employee> findByCriteria(List<SearchCriteria> filters);
}
