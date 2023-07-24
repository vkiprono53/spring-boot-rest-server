package com.vkiprono.springbootrestserver.repositories;

import com.vkiprono.springbootrestserver.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vkiprono
 * @created 6/9/23
 */

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByEmployeeId(Long employeeId);
}
