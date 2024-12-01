package com.gespyme.domain.model.filter;

import com.gespyme.commons.model.filter.FieldFilter;
import com.gespyme.commons.repository.criteria.SearchCriteria;
import com.gespyme.commons.repository.criteria.SearchOperation;
import com.gespyme.domain.model.EmployeeFilter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class EmployeeAddressFilter implements FieldFilter<EmployeeFilter> {
    @Override
    public boolean apply(EmployeeFilter employeeFilter) {
        return Objects.nonNull(employeeFilter.getAddress());
    }

    @Override
    public void addSearchCriteria(EmployeeFilter employeeFilter, List<SearchCriteria> searchCriteriaList) {
        searchCriteriaList.add(SearchCriteria.builder().key("address").operation(SearchOperation.LIKE).value(employeeFilter.getAddress()).build());
    }
}
