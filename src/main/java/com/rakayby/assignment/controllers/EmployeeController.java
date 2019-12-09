package com.rakayby.assignment.controllers;

import com.rakayby.assignment.defines.ApiEndPoints;
import com.rakayby.assignment.models.Employee;
import com.rakayby.assignment.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mohammed.Rakayby
 */
@RestController
@RequestMapping(value = ApiEndPoints.Controllers.EMPLOYEE, consumes = "application/json", produces = "application/json")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(ApiEndPoints.GET_ALL)
    public List<Employee> getAll() {
        return employeeService.findAll();
    }
}
