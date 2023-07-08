package com.vkiprono.springbootrestserver.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vkiprono.springbootrestserver.dtos.EMPREQUESTSAVE;
import com.vkiprono.springbootrestserver.dtos.EmpSaveRequestDTO;
import com.vkiprono.springbootrestserver.dtos.EmployeeDTO;
import com.vkiprono.springbootrestserver.models.Employee;
import com.vkiprono.springbootrestserver.services.common.EntityMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author vkiprono
 * @created 6/12/23
 */

public interface EmployeeServiceI extends EntityMapper<EmployeeDTO, Employee> {
     List<Employee> findAll();

     EmployeeDTO save(EMPREQUESTSAVE empSaveRequestDTO) throws Exception;

     EmployeeDTO update(EMPREQUESTSAVE empSaveRequestDTO ) throws Exception;

     EmployeeDTO deleteEmployee(String employeeDtls) throws Exception;

     EmployeeDTO findEmployeeByPNo(String pNo) throws Exception;

}
