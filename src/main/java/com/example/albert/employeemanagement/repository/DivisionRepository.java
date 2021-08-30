package com.example.albert.employeemanagement.repository;

import com.example.albert.employeemanagement.datalayer.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DivisionRepository extends JpaRepository<Division, String> {
    public Boolean existsDivisionByDivisionId(String divisionId);

    public Boolean existsDivisionByInTrash(int inTrash);

    public Boolean existsDivisionByDivisionName(String divisionName);

    public Boolean existsDivisionByDivisionCode(String divisionCode);

    public Division getDivisionByDivisionNameOrDivisionCode(String divisionName, String divisionCode);

    public Division findDivisionByDivisionIdAndInTrash(String divisionId, int inTrash);

    public List<Division> findDivisionByInTrash(int inTrash);

}
