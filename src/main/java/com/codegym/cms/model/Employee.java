package com.codegym.cms.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private LocalDate birthdate;
    private  String address;
    private String avatar;
    private Double salary;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    public Employee() {
    }
    public Employee(String name, LocalDate birthdate, String address, String avatar, Double salary, Department department) {
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
        this.avatar = avatar;
        this.salary = salary;
        this.department = department;
    }
    public Employee(Long id,String name, LocalDate birthdate, String address, String avatar, Double salary, Department department) {
        this.id=id;
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
        this.avatar = avatar;
        this.salary = salary;
        this.department = department;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }




    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}