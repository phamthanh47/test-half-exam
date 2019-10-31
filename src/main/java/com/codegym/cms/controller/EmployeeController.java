package com.codegym.cms.controller;

import com.codegym.cms.model.Department;
import com.codegym.cms.model.Employee;
import com.codegym.cms.model.EmployeeForm;
import com.codegym.cms.service.DepartmentService;
import com.codegym.cms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.*;
import javax.jws.WebParam;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
//@RequestMapping("/employee")
@PropertySource("classpath:global_config_app.properties")
public class EmployeeController {
    @Autowired
    Environment env;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @ModelAttribute("departments")
    public Iterable<Department> departments(){
        return departmentService.findAll();
    }

    @GetMapping("/employees")
    public ModelAndView listFormEmployee(@RequestParam("s")Optional<String> s,@PageableDefault(value=5,sort="salary") Pageable pageable){
        //Page<Employee> employees=employeeService.findAll(pageable);
//        employees.forEach(item->{
//            System.out.println("emp:"+item);
//        });
        Page<Employee> employees;
        if(s.isPresent()){
            employees = employeeService.findAllByDepartmentName(s.get(),pageable);

        }else {
            employees = employeeService.findAll(pageable);
        }
        ModelAndView modelAndView=new ModelAndView("/employee/list");
        modelAndView.addObject("employees",employees);
        return modelAndView;
    }
    @GetMapping("/create-employee")
    public ModelAndView showFormCreateEmployee(){
        ModelAndView modelAndView=new ModelAndView("/employee/create");
        modelAndView.addObject("employee",new Employee());
        return modelAndView;
    }
    @PostMapping("/create-employee")
    public ModelAndView createFormEmployee(@ModelAttribute EmployeeForm employeeform, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
           ModelAndView modelAndView = new ModelAndView("/employee/create");
           modelAndView.addObject("employee", new Employee());
           modelAndView.addObject("errorMessage",bindingResult.getAllErrors());
            return modelAndView;
        }

        // lay ten file
        MultipartFile multipartFile = employeeform.getAvatar();
        String fileName = multipartFile.getOriginalFilename();

        String fileUpload = env.getProperty("file_upload").toString();

        // luu file len server
        try {
            FileCopyUtils.copy(employeeform.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // tao doi tuong de luu vao db
        Employee employeeObject = new Employee(employeeform.getId(),employeeform.getName(),employeeform.getBirthdate(),employeeform.getAddress(),fileName,employeeform.getSalary(),employeeform.getDepartment());

        // luu vao db
        employeeService.save(employeeObject);

        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employee", new EmployeeForm());
        modelAndView.addObject("errorMessage","successes!");
        return modelAndView;

    }
    @GetMapping("/edit-employee/{id}")
    public  ModelAndView showFormEdit(@PathVariable Long id){
        Employee employee=employeeService.findById(id);
      //EmployeeForm employeeForm=new EmployeeForm(employee.getId(),employee.getName(),employee.getBirthdate(),employee.getAddress(),null,employee.getSalary(),employee.getDepartment());
        ModelAndView modelAndView=new ModelAndView("/employee/edit");
      // modelAndView.addObject("employeeForm",employeeForm);
        modelAndView.addObject("employee",employee);
        return modelAndView;
    }
    @PostMapping("/edit-employee")
    public ModelAndView editFormEmployee(@ModelAttribute("employee") EmployeeForm employeeForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("/employee/edit");
        }

//        MultipartFile multipartFile = employeeForm.getAvatar();
//        String fileName = multipartFile.getOriginalFilename();
//
//        String fileUpload = env.getProperty("file_upload").toString();
//
//        try {
//            FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//employeeService.edit(employeeForm,fileName);
        Long id = employeeForm.getId();
        String name =employeeForm.getName();
        LocalDate birthDate = employeeForm.getBirthdate();
        String  address = employeeForm.getAddress();
        String fileName = employeeForm.getAvatar().getOriginalFilename();
        Double salary = employeeForm.getSalary();
        Department department = employeeForm.getDepartment();
//      Employee employee=new Employee(employeeForm.getId(),employeeForm.getName(),employeeForm.getBirthdate(),employeeForm.getAddress(),
//              employeeForm.getAvatar().getOriginalFilename(),employeeForm.getSalary(),employeeForm.getDepartment());
        Employee employee = new Employee(id,name,birthDate,address,fileName,salary,department);
      employeeService.save(employee);

     //   employeeService.edit(employee,fileName);
        ModelAndView modelAndView = new ModelAndView("/employee/edit");
       // modelAndView.addObject("employeeForm",employeeForm);
        modelAndView.addObject("employees",employee);
        modelAndView.addObject("errorMessage","successes!");
        return modelAndView;

    }
    @GetMapping("/delete-employee/{id}")
    public ModelAndView showFormDeleteEmployee(@PathVariable Long id){
        Employee employee=employeeService.findById(id);
        ModelAndView modelAndView=new ModelAndView("/employee/delete");
        modelAndView.addObject("employee",employee);
        return modelAndView;
    }
    @PostMapping("/delete-employee")
    public String deleteEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.remove(employee.getId());
        return "redirect:employees";
    }
}
