package com.gespyme.domain.model.filter;

import com.gespyme.commons.model.filter.FieldFilter;
import com.gespyme.commons.repository.criteria.SearchCriteria;
import com.gespyme.commons.repository.criteria.SearchOperation;
import com.gespyme.domain.model.EmployeeFilter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class EmployeeLastNameFilter implements FieldFilter<EmployeeFilter> {

    @Override
    public boolean apply(EmployeeFilter employeeFilter) {
        return Objects.nonNull(employeeFilter.getLastName());
    }

    @Override
    public void addSearchCriteria(EmployeeFilter employeeFilter, List<SearchCriteria> searchCriteriaList) {
        searchCriteriaList.add(SearchCriteria.builder().key("last_name").operation(SearchOperation.LIKE).value(employeeFilter.getLastName()).build());
    }
}
