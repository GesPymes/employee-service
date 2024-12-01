package com.gespyme.infrastructure.mapper;

import com.gespyme.commons.model.employee.EmployeeFilterModelApi;
import com.gespyme.commons.model.employee.EmployeeModelApi;
import com.gespyme.domain.model.Employee;
import com.gespyme.domain.model.EmployeeFilter;
import com.gespyme.infrastructure.adapters.output.model.entities.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeInfrastructureMapper {
    EmployeeFilter map(EmployeeFilterModelApi employeeFilterModelApi);

    Employee map(EmployeeModelApi employeeApiModel);

    List<EmployeeModelApi> map(List<Employee> employees);

    EmployeeModelApi map(Employee employees);

    List<Employee> mapEntityList(List<EmployeeEntity> employeeEntity);

    Employee map(EmployeeEntity employeeEntity);

    EmployeeEntity mapToEntity(Employee employee);

    Employee merge(Employee newEmployeeData, @MappingTarget Employee employee);
}
