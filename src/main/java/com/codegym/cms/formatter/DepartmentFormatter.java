package com.codegym.cms.formatter;

import com.codegym.cms.model.Department;
import com.codegym.cms.repository.DepartmentRepository;
import com.codegym.cms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class DepartmentFormatter  implements Formatter<Department> {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    public DepartmentFormatter(DepartmentService departmentService){
        this.departmentService=departmentService;
    }
    @Override
    public Department parse(String text, Locale locale) throws ParseException {
        return departmentService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Department object, Locale locale) {
        return "["+object.getId()+"."+object.getName()+"]";
    }
}
