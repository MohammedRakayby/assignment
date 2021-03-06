package com.rakayby.assignment.controllers;

import com.rakayby.assignment.common.Utility;
import com.rakayby.assignment.defines.ApiEndPoints;
import com.rakayby.assignment.facades.EmployeeFacade;
import com.rakayby.assignment.models.Employee;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mohammed.Rakayby
 */
@RestController
@RequestMapping(value = ApiEndPoints.Controllers.EMPLOYEE, produces = "application/json")
public class EmployeeController {

    private static final Logger debugLogger = LogManager.getLogger("DEBUG");
    private static final Logger errorLogger = LogManager.getLogger("ERROR");
    private EmployeeFacade employeeFacade;

    @Autowired
    public EmployeeController(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    @GetMapping(ApiEndPoints.GET_ALL)
    @ApiOperation(value = "Fetches all emplyees",
            response = Employee.class)
    public List<Employee> getAll() {
        debugLogger.debug("@controller EmployeeController, received request @getAll");
        return employeeFacade.findAll();
    }

    @PostMapping(ApiEndPoints.UPDATE)
    @ApiOperation(value = "Updates employee",
            notes = "Updates certain employee by Id, you can use find by name",
            response = Employee.class)
    public Employee update(@RequestBody Employee e, @RequestParam(name = "employeeId", required = true) Long employeeId) {
        debugLogger.debug("@controller EmployeeController, received request @update");
        if (Utility.validateEmployee(e)) {
            Optional updatedEmployee = employeeFacade.Update(e, employeeId);
            if (updatedEmployee.isPresent()) {
                return (Employee) updatedEmployee.get();
            }
        }
        errorLogger.error("@controller EmployeeController, request @update failed validations");
        return null;
    }

    @GetMapping(ApiEndPoints.UPDATE_BY_NAME)
    @ApiOperation(value = "Updates employee's name by ID",
            notes = "Updates certain employee's name, this is not availed for front end, only used to demonstrate JPA's @query annotation",
            response = Integer.class)
    public Integer updateByName(@RequestParam(name = "firstName", required = true) String firstName,
            @RequestParam(name = "lastName", required = true) String lastName,
            @RequestParam(name = "employeeId", required = true) Long employeeId) {
        debugLogger.debug("@controller EmployeeController, received request @updateByName");
        if (!(firstName.isEmpty() && lastName.isEmpty())) {
            return employeeFacade.updateEmployeeName(firstName, lastName, employeeId);
        }
        errorLogger.error("@controller EmployeeController, request @updateByName failed validations");
        return 0;
    }

    @GetMapping(ApiEndPoints.DELETE)
    @ApiOperation(value = "Delete employee by ID")
    public void delete(@RequestParam(name = "employeeId", required = true) Long employeeId) {
        debugLogger.debug("@controller EmployeeController, received request @delete");
        employeeFacade.deleteById(employeeId);
    }

    @PostMapping(ApiEndPoints.CREATE)
    @ApiOperation(value = "Create a new employee",
            notes = "You can delete the id param from the model, the id is generated automatically",
            response = Employee.class)
    public Employee create(@RequestBody Employee e) {
        debugLogger.debug("@controller EmployeeController, received request @create");
        if (Utility.validateEmployee(e)) {
            return employeeFacade.create(e);
        }
        errorLogger.error("@controller EmployeeController, request @create failed validations");
        return null;
    }
}
