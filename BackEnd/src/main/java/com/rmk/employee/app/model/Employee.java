package com.rmk.employee.app.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1083273L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Name -> can only contain alphabets
    @NotEmpty(message = "name is required")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "name is invalid")
    private String name;

    //department -> alphanumeric with -_* as special characters
    @NotEmpty(message = "department is required")
    @Pattern(regexp = "^[A-Za-z0-9-_*]*$", message = "department is invalid")
    private String department;

    //designation -> Developer, Senior Developer, Manager, Team Lead, VP, CEO
    @NotNull(message = "designation is required")
    @Enumerated(value = EnumType.STRING)
    private Designation designation;

    //Salary -> can only contain Numeric value
    @NotNull(message = "salary is required")
    private Integer salary;

    //joining date -> yyyy-MM-dd format
    @NotNull(message = "joiningDate is required")
    @Column(name="joining_date")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonAlias({"joining_date", "joiningDate"})
    private LocalDate joiningDate;

    public Employee() {}

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

}