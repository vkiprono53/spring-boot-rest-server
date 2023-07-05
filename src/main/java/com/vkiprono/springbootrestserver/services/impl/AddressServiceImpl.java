package com.vkiprono.springbootrestserver.services.impl;

import com.vkiprono.springbootrestserver.constants.EmployeeConstant;
import com.vkiprono.springbootrestserver.dtos.AddressDTO;
import com.vkiprono.springbootrestserver.models.Address;
import com.vkiprono.springbootrestserver.respositories.AddressRepository;
import com.vkiprono.springbootrestserver.services.AddressServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author vkiprono
 * @created 6/12/23
 */
@Service
public class AddressServiceImpl implements AddressServiceI {
    private final AddressRepository addressRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        LOGGER.info(":::::START AddressServiceImpl.save():::::");

        Address address = new Address();
        if (addressDTO == null) {
            return null;
        }
        //Convert DTO to entity
        addressDTO.setCreatedBy(EmployeeConstant.SYSTEM_ID);
        addressDTO.setCreatedDate(new Date());

        address = this.dtoToEntity(addressDTO);
        address = addressRepository.save(address);

        //Convert entity back to dto
        addressDTO = this.entityToDTO(address);

        LOGGER.info(":::::START AddressServiceImpl.save():::::");

        return addressDTO;
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) {
        LOGGER.info(":::::START AddressServiceImpl.update():::::");
        LOGGER.info("::::: Employee Id is ::::::" + addressDTO.getEmployeeId());

        AddressDTO dtoFromDB = new AddressDTO();

        Address address = new Address();
        if (addressDTO == null) {
            return null;
        }

        dtoFromDB = this.findAddressByEmployeeId(addressDTO.getEmployeeId());

        if (dtoFromDB.getAddressId() != null) {
            addressDTO.setAddressId(dtoFromDB.getAddressId());
            addressDTO.setUpdatedBy(EmployeeConstant.SYSTEM_ID);
            addressDTO.setUpdatedDate(new Date());
            addressDTO.setCreatedBy(dtoFromDB.getCreatedBy());
            addressDTO.setCreatedDate(dtoFromDB.getCreatedDate());
            // addressDTO.setEmployeeId(addressDTO.getEmployeeId());
            address = this.dtoToEntity(addressDTO);


        }
        address = addressRepository.save(address);

        //Convert entity back to dto
        addressDTO = this.entityToDTO(address);

        LOGGER.info(":::::START AddressServiceImpl.update():::::");

        return addressDTO;
    }


    @Override
    public AddressDTO findAddressByEmployeeId(Long employeeId) {
        AddressDTO addressDTO = null;
        Address address = new Address();
        if (employeeId != null) {
            address = addressRepository.findAddressByEmployeeId(employeeId);
        }
        addressDTO = this.entityToDTO(address);
        return addressDTO;
    }


    @Override
    public Boolean deleteById(Long addressId) {
        boolean isDeleted = false;
        if (addressId != null) {
            addressRepository.deleteById(addressId);
            isDeleted = true;
        }

        return isDeleted;
    }


    //DTO to Entity
    @Override
    public Address dtoToEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        Address address = new Address();

        if (addressDTO.getAddressId() != null) {
            address.setAddressId(addressDTO.getAddressId());
        }
        if (addressDTO.getAddress() != null) {
            address.setAddress(addressDTO.getAddress());
        }
        if (addressDTO.getPhone() != null) {
            address.setPhone(addressDTO.getPhone());
        }
        if (addressDTO.getEmployeeId() != null) {
            address.setEmployeeId(addressDTO.getEmployeeId());
        }
        if (addressDTO.getPostalCode() != null) {
            address.setPostalCode(addressDTO.getPostalCode());
        }
        if (addressDTO.getCreatedBy() != null) {
            address.setCreatedBy(addressDTO.getCreatedBy());
        }
        if (addressDTO.getCreatedDate() != null) {
            address.setCreatedDate(addressDTO.getCreatedDate());
        }
        if (addressDTO.getUpdatedBy() != null) {
            address.setUpdatedBy(addressDTO.getUpdatedBy());
        }
        if (addressDTO.getUpdatedDate() != null) {
            address.setUpdatedDate(addressDTO.getUpdatedDate());
        }
        return address;
    }

    //Entity to DTO
    @Override
    public AddressDTO entityToDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        if (address == null) {
            return null;
        }

        if (address.getAddressId() != null) {
            dto.setAddressId(address.getAddressId());
        }

        if (address.getAddress() != null) {
            dto.setAddress(address.getAddress());
        }
        if (address.getPhone() != null) {
            dto.setAddress(address.getPhone());
        }
        if (address.getEmployeeId() != null) {
            dto.setEmployeeId(address.getEmployeeId());
        }
        if (address.getPostalCode() != null) {
            dto.setPostalCode(address.getPostalCode());
        }
        if (address.getCreatedBy() != null) {
            dto.setCreatedBy(address.getCreatedBy());
        }
        if (address.getCreatedDate() != null) {
            dto.setCreatedDate(address.getCreatedDate());
        }
        if (address.getUpdatedBy() != null) {
            dto.setUpdatedBy(address.getUpdatedBy());
        }
        if (address.getUpdatedDate() != null) {
            dto.setUpdatedDate(address.getUpdatedDate());
        }

        return dto;
    }
}
