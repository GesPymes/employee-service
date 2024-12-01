package com.gespyme.infrastructure.adapters.output.repository.jpa;

import com.gespyme.infrastructure.adapters.output.model.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositorySpringJpa extends JpaRepository<EmployeeEntity, String>, QuerydslPredicateExecutor<EmployeeEntity> {
}
