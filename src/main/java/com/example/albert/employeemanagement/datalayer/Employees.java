package com.example.albert.employeemanagement.datalayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tbl_employees")
public class Employees {
    @Id
    @Column(name = "employee_id")
    private String employeeId;
    @NotEmpty(message = "first name cannot be empty")
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty(message = "last name cannot be empty")
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @NotEmpty(message = "country cannot be empty")
    private String country;
    @NotEmpty(message = "state cannot be empty")
    private String state;
    @NotEmpty(message = "city cannot be empty")
    private String city;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "joining_date")
    private LocalDate joiningDate;
    @NotEmpty(message = "department cannot be empty")
    private String department;
    @NotEmpty(message = "division cannot be empty")
    private String division;
    @Column(name = "employee_status")
    private int employeeStatus;
    @NotEmpty(message = "address name cannot be empty")
    private String address;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @NotEmpty(message = "phone number cannot be empty")
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "enter a valid email address")
    private String email;

    @Column(name = "national_id")
    @NotEmpty(message = "National Id cannot be empty")
    private String nationalId;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "employee")
    private Salary salary;
    @JsonIgnore
    @OneToMany(mappedBy = "employee",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<LeaveBalance> leaveBalances = new ArrayList<>();
    @OneToMany(mappedBy = "employees",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<LeaveApplication> leaveApplications = new ArrayList<>();
}
