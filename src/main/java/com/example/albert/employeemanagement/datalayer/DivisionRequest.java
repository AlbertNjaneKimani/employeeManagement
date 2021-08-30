package com.example.albert.employeemanagement.datalayer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DivisionRequest {
    private String divisionId;
    @NotEmpty(message = "division name cannot be empty")
    private String divisionName;
    @NotEmpty(message = "division code cannot be empty")
    private String divisionCode;
    private int inTrash;
    private  String department_id;
}
