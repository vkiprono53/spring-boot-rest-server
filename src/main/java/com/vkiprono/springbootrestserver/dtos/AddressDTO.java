package com.vkiprono.springbootrestserver.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author vkiprono
 * @created 6/8/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    @JsonIgnore
    private Long addressId;

    @JsonIgnore
    private Long employeeId;

  @JsonProperty("address")
    private String address;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonIgnore
    private Long createdBy;

    @JsonIgnore
    private Long updatedBy;

    @JsonIgnore
    private Date createdDate;

    @JsonIgnore
    private Date updatedDate;

}
