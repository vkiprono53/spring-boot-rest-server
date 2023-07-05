package com.vkiprono.springbootrestserver.models;

import com.vkiprono.springbootrestserver.constants.EmployeeConstant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * @author vkiprono
 * @created 6/8/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_seq")
    @SequenceGenerator(name="addresses_seq", sequenceName = "addresses_seq", allocationSize=1)
    private Long addressId;
    private String address;
    private Long employeeId;
    private String phone;
    private String postalCode;
    @CreatedBy
    private Long createdBy;
    @LastModifiedBy
    private Long updatedBy;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;

}
