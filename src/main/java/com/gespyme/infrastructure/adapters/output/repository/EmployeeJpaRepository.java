package com.gespyme.infrastructure.adapters.output.repository;

import com.gespyme.commons.repository.QueryField;
import com.gespyme.commons.repository.criteria.SearchCriteria;
import com.gespyme.domain.model.Employee;
import com.gespyme.domain.repository.EmployeeRepository;
import com.gespyme.infrastructure.adapters.output.model.entities.EmployeeEntity;
import com.gespyme.infrastructure.adapters.output.repository.jpa.EmployeeRepositorySpringJpa;
import com.gespyme.infrastructure.mapper.EmployeeInfrastructureMapper;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeJpaRepository implements EmployeeRepository {
    private final Map<String, QueryField> queryFieldMap;
    private final EmployeeRepositorySpringJpa employeeRepositorySpringJpa;

    private final EmployeeInfrastructureMapper mapper;

    public EmployeeJpaRepository(List<QueryField> queryFields, EmployeeRepositorySpringJpa employeeRepositorySpringJpa, EmployeeInfrastructureMapper mapper) {
        this.employeeRepositorySpringJpa = employeeRepositorySpringJpa;
        this.mapper = mapper;
        queryFieldMap = queryFields.stream().collect(Collectors.toMap(QueryField::getFieldName, queryField -> queryField));
    }

    @Override
    public List<Employee> findByCriteria(List<SearchCriteria> searchCriteria) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        searchCriteria.stream().forEach(sc -> queryFieldMap.get(sc.getKey()).addToQuery(booleanBuilder, sc));
        List<EmployeeEntity> entities = (List<EmployeeEntity>) employeeRepositorySpringJpa.findAll(booleanBuilder);
        return mapper.mapEntityList(entities);
    }

    @Override
    public Optional<Employee> findById(String id) {
        return employeeRepositorySpringJpa.findById(id).map(mapper::map);
    }

    @Override
    public void deleteById(String id) {
        employeeRepositorySpringJpa.deleteById(id);
    }

    @Override
    public Employee save(Employee employee) {

        EmployeeEntity employeeEntity = employeeRepositorySpringJpa.save(mapper.mapToEntity(employee));
        return mapper.map(employeeEntity);
    }

    @Override
    public Employee merge(Employee newEmployeeData, Employee employee) {
        Employee merged = mapper.merge(newEmployeeData, employee);
        EmployeeEntity savedEntity = employeeRepositorySpringJpa.save(mapper.mapToEntity(merged));
        return mapper.map(savedEntity);
    }
}
