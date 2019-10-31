package com.codegym.cms.repository;

import com.codegym.cms.model.Department;
import com.codegym.cms.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department,Long> {

}
