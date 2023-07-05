package com.vkiprono.springbootrestserver.services;

import com.vkiprono.springbootrestserver.dtos.AddressDTO;
import com.vkiprono.springbootrestserver.models.Address;
import com.vkiprono.springbootrestserver.services.common.EntityMapper;

import java.util.List;

/**
 * @author vkiprono
 * @created 6/12/23
 */

public interface AddressServiceI extends EntityMapper<AddressDTO,Address> {
    AddressDTO save(AddressDTO address);
    AddressDTO update(AddressDTO address);
    AddressDTO findAddressByEmployeeId(Long employeeId);
    Boolean deleteById(Long addressId);
}
