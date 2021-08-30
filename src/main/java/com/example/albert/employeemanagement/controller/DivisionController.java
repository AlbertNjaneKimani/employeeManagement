package com.example.albert.employeemanagement.controller;

import com.example.albert.employeemanagement.datalayer.Division;
import com.example.albert.employeemanagement.datalayer.DivisionRequest;
import com.example.albert.employeemanagement.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class DivisionController {
    @Autowired
    private DivisionService divisionService;

    @PostMapping("/department/{departmentId}/division")
    public ResponseEntity<Division> addDivision(@PathVariable("departmentId") String departmentId,
                                                @Valid @RequestBody Division division) {
        ResponseEntity<Division> savedDiv = divisionService.addDivision(departmentId, division);
        return savedDiv;
    }

    @GetMapping("/division")
    public List<Division> getAllDivisions() {
        return divisionService.findAllDivision();
    }

    @GetMapping("/division/{divisionId}")
    public Division getDivisionByDivisonId(@PathVariable String divisionId) {
        return divisionService.getDivisionByDivisionId(divisionId);
    }

    @GetMapping("/division/search")
    public Division searchDivision(@RequestParam String divisionName,
                                   @RequestParam String divisionCode) {
        return divisionService.searchByDivisionNameAndCode(divisionName, divisionCode);
    }

    @PutMapping("/division/{divisionId}")
    public Division updateDivision(@PathVariable String divisionId,
                                   @Valid @RequestBody Division division) {
        return divisionService.updateDivision(divisionId, division);
    }

    @PutMapping("/division/disable/{divisionId}")
    public String deleteDivision(@PathVariable String divisionId) {
       return  divisionService.deleteDivision(divisionId);
    }
}
