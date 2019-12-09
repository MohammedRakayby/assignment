package com.rakayby.assignment.models;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Mohammed.Rakayby
 */
@Entity(name = "EMPLOYEES")
@Getter @Setter @NoArgsConstructor @ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;
    
    @Column(name = "LAST_NAME")
    private String lastName;

    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "HIRING_DATE")
    private Date hiringDate;
    
    private Integer salary;
    
    @Column(name = "MANAGER_ID")
    private Long managerId;
    
    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;

}