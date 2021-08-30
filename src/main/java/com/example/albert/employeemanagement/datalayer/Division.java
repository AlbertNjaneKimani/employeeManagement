package com.example.albert.employeemanagement.datalayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tbl_division")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Division {
    @Id
    @Column(name = "division_id")
    private String divisionId;
    @NotEmpty(message = "division name cannot be empty")
    @Column(name = "division_name")
    private String divisionName;
    @NotEmpty(message = "division code cannot be empty")
    @Column(name = "division_code")
    private String divisionCode;
    private int inTrash;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Division)) return false;
        return divisionId != null && divisionId.equals(((Division) o).getDivisionId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
