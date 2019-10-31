package com.codegym.cms.service;

import com.codegym.cms.model.Department;
import com.codegym.cms.model.Employee;
import com.codegym.cms.model.EmployeeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);
    Employee findById(Long id);
     void remove(Long id);
    void save(Employee employee);
    Iterable<Employee> findAllByDepartment(Department department);

    void edit(EmployeeForm employeeForm, String image);

    Page<Employee> findAllByDepartmentName(String name,Pageable pageable);
}
