package com.vkiprono.springbootrestserver.respositories;

import com.vkiprono.springbootrestserver.dtos.AddressDTO;
import com.vkiprono.springbootrestserver.models.Address;
import jakarta.transaction.Transactional;
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
