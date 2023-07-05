package com.vkiprono.springbootrestserver.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author vkiprono
 * @created 6/8/23
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "departments")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(name = "department_seq", sequenceName = "department_seq",  allocationSize = 1)
    private Long departmentId;
    private String name;
    private String code;
    private String description;
    private Long createdBy;
    private Long updatedBy;
    private Date createdDate;
    private Date updatedDate;
}
