package com.codegym.cms.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class EmployeeForm {

    private Long id;
    private String name;
    private LocalDate birthdate;
    private  String address;
    private MultipartFile avatar;
    private Double salary;
    private Department department;

    public EmployeeForm() {
    }

    public EmployeeForm(Long id, String name, LocalDate birthdate, String address, MultipartFile avatar, Double salary, Department department) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
        this.avatar = avatar;
        this.salary = salary;
        this.department = department;
    }


    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
