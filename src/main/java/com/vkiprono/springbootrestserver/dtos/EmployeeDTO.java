package com.vkiprono.springbootrestserver.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;

/**
 * @author vkiprono
 * @created 6/8/23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {
    @JsonIgnore
    private Long employeeId;

    @JsonProperty("pNo")
    private String PNo;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("addressId")
    private Long addressId;

    @JsonProperty("departmentId")
    private Long departmentId;

    @JsonIgnore
    private String active;

    @JsonIgnore
    private Long createdBy;

    @JsonIgnore
    private Long updatedBy;

    @JsonIgnore
    private Date createdDate;

    @JsonIgnore
    private Date updatedDate;
}
