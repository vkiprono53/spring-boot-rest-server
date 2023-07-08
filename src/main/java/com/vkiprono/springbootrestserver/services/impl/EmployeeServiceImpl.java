package com.vkiprono.springbootrestserver.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.vkiprono.springbootrestserver.constants.EmployeeConstant;
import com.vkiprono.springbootrestserver.dtos.*;
import com.vkiprono.springbootrestserver.models.Employee;
import com.vkiprono.springbootrestserver.respositories.EmployeeRepository;
import com.vkiprono.springbootrestserver.services.AddressServiceI;
import com.vkiprono.springbootrestserver.services.EmployeeServiceI;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author vkiprono
 * @created 6/12/23
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeServiceI {

    private final EmployeeRepository employeeRepository;
    private final AddressServiceI addressServiceI;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressServiceI addressServiceI) {
        this.employeeRepository = employeeRepository;
        this.addressServiceI = addressServiceI;
    }


    @Override
    public EmployeeDTO findEmployeeByPNo(String pNo) throws Exception{
        LOGGER.info(":::::START EmployeeServiceImpl.findEmployeeByPNo():::::");
        Employee employee = new Employee();
        EmployeeDTO dto = null;
        if (pNo != null && !pNo.isEmpty()) {
            employee = employeeRepository.findEmployeeBypNo(pNo);
        }

        dto = this.entityToDTO(employee);

        LOGGER.info(":::::EXIT EmployeeServiceImpl.findEmployeeByPNo():::::");

        return dto;
    }

    @Override
    public List<Employee> findAll() {
        LOGGER.info(":::::START EmployeeServiceImpl.findAll():::::");

        List<Employee> employees = null;
        employees = employeeRepository.findAll();
        LOGGER.info(":::::EXIT EmployeeServiceImpl.findAll():::::");

        return employees;
    }

    //Saving Employee and Address Dtls
    @Override
    public EmployeeDTO save(EMPREQUESTSAVE empSaveRequestDTO) throws Exception {
        LOGGER.info(":::::START EmployeeServiceImpl.save():::::");

        EmpSaveRequestDTO requestDTO = new EmpSaveRequestDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        AddressDTO addressDTO = new AddressDTO();
        Employee employee = new Employee();

        Long employeeId = null;

        if (empSaveRequestDTO != null) {
            employeeDTO = empSaveRequestDTO.getRequestDTO().getEmployeeDTO();
            addressDTO = empSaveRequestDTO.getRequestDTO().getAddressDTO();
        }
        //Saving Employee Dtls:
        employeeDTO.setActive(EmployeeConstant.ACTIVE);
        employeeDTO.setCreatedBy(EmployeeConstant.SYSTEM_ID);
        employeeDTO.setCreatedDate(new Date());

        employee = this.dtoToEntity(employeeDTO);
        employee = employeeRepository.save(employee);

        //Save Address
        employeeId = employee.getEmployeeId();
        if (employeeId != null) {

            addressDTO.setEmployeeId(employeeId);
            addressDTO = addressServiceI.save(addressDTO);

        }
        //convert employee dto to entity
        employeeDTO = this.entityToDTO(employee);
        LOGGER.info(":::::EXIT EmployeeServiceImpl.save():::::");

        return employeeDTO;
    }

    //Update
    @Override
    public EmployeeDTO update(EMPREQUESTSAVE empSaveRequestDTO) throws Exception {

        LOGGER.info(":::::START EmployeeServiceImpl.update():::::");

        EmpSaveRequestDTO requestDTO = new EmpSaveRequestDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        EmployeeDTO employeeDTOFromDB = new EmployeeDTO();
        AddressDTO addressDTO = new AddressDTO();
        Employee employee = new Employee();
        Long employeeId = null;

        if (empSaveRequestDTO != null) {
            employeeDTO = empSaveRequestDTO.getRequestDTO().getEmployeeDTO();
            addressDTO = empSaveRequestDTO.getRequestDTO().getAddressDTO();
        }

        //Check if employee exist then update:
        employeeDTOFromDB = findEmployeeByPNo(employeeDTO.getPNo());

        if (employeeDTOFromDB.getEmployeeId() != null) {
            employeeDTOFromDB.setPNo(employeeDTO.getPNo());
            employeeDTOFromDB.setLastName(employeeDTO.getLastName());
            employeeDTOFromDB.setFirstName(employeeDTO.getFirstName());
            employeeDTOFromDB.setEmail(employeeDTO.getEmail());
            employeeDTOFromDB.setDepartmentId(employeeDTO.getDepartmentId());

            employeeDTOFromDB.setUpdatedBy(EmployeeConstant.SYSTEM_ID);
            employeeDTOFromDB.setUpdatedDate(new Date());

            employee = this.dtoToEntity(employeeDTOFromDB);

            employee = employeeRepository.save(employee);
        }

        //update Address
        employeeId = employee.getEmployeeId();

        if (employeeId != null) {
            addressDTO.setEmployeeId(employeeId);
            addressDTO = addressServiceI.update(addressDTO);

        }
        //convert employee dto to entity
        employeeDTO = this.entityToDTO(employee);
        LOGGER.info(":::::EXIT EmployeeServiceImpl.update():::::");

        return employeeDTO;
    }

    //Delete
    @Override
    public EmployeeDTO deleteEmployee(String employeeDtls) throws Exception {
        LOGGER.info(":::::START EmployeeServiceImpl.deleteEmployee():::::");
        EmpSaveRequestDTO requestDTO = new EmpSaveRequestDTO();
        String employeeNo = null;
        EmpGetAndDeleteRequestDTO deleteRequestDTO  = new EmpGetAndDeleteRequestDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        EmployeeDTO employeeDTOFromDB = new EmployeeDTO();
        Employee employee = new Employee();

        if (employeeDtls != null) {
            //Check if employee exist then update:
            employeeDTOFromDB  = findEmployeeByPNo(employeeDtls);
        }

        if (employeeDTOFromDB.getEmployeeId() != null) {

            employeeDTOFromDB.setUpdatedDate(new Date());
            employeeDTOFromDB.setUpdatedBy(EmployeeConstant.SYSTEM_ID);
            employeeDTOFromDB.setActive(EmployeeConstant.IN_ACTIVE);

            employee = this.dtoToEntity(employeeDTOFromDB);

            employee = employeeRepository.save(employee);

            //convert employee dto to entity
            employeeDTO = this.entityToDTO(employee);
        }


        LOGGER.info(":::::EXIT EmployeeServiceImpl.deleteEmployee():::::");

        return employeeDTO;
    }

    /**
     * Convert EMPLOYEE DTO TO EMPLOYEE ENTITY
     *
     * @param employeeDTO
     * @return
     */
    @Override
    public Employee dtoToEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return null;
        }
        Employee employee = new Employee();

        if (employeeDTO.getEmployeeId() != null) {
            employee.setEmployeeId(employeeDTO.getEmployeeId());
        }
        if (employeeDTO.getPNo() != null) {
            employee.setPNo(employeeDTO.getPNo());
        }
        if (employeeDTO.getFirstName() != null) {
            employee.setFirstName(employeeDTO.getFirstName());
        }
        if (employeeDTO.getLastName() != null) {
            employee.setLastName(employeeDTO.getLastName());
        }
        if (employeeDTO.getEmail() != null) {
            employee.setEmail(employeeDTO.getEmail());
        }
   if (employeeDTO.getActive() != null) {
            employee.setActive(employeeDTO.getActive());
        }

        if (employeeDTO.getDepartmentId() != null) {
            employee.setDepartmentId(employeeDTO.getDepartmentId());
        }

        if(employeeDTO.getCreatedBy() != null){
            employee.setCreatedBy(employeeDTO.getCreatedBy());
        }
        if (employeeDTO.getCreatedDate() != null){
            employee.setCreatedDate(employeeDTO.getCreatedDate());
        }
        if (employeeDTO.getUpdatedBy() != null){
            employee.setUpdatedBy(employeeDTO.getUpdatedBy());
        }
        if (employeeDTO.getUpdatedDate() != null){
            employee.setUpdatedDate(employeeDTO.getUpdatedDate());
        }

        return employee;
    }

    /**
     * Convert ENTITY to DTO
     *
     * @param employee
     * @return
     */
    @Override
    public EmployeeDTO entityToDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();

        if (employee.getEmployeeId() != null) {
            employeeDTO.setEmployeeId(employee.getEmployeeId());
        }
        if (employee.getPNo() != null) {
            employeeDTO.setPNo(employee.getPNo());
        }

        if (employee.getFirstName() != null) {
            employeeDTO.setFirstName(employee.getFirstName());
        }
         if (employee.getActive() != null) {
            employeeDTO.setActive(employee.getActive());
        }

        if (employee.getLastName() != null) {
            employeeDTO.setLastName(employee.getLastName());
        }
        if (employee.getEmail() != null) {
            employeeDTO.setEmail(employee.getEmail());
        }

        if (employee.getDepartmentId() != null) {
            employeeDTO.setDepartmentId(employee.getDepartmentId());
        }
        if (employee.getCreatedBy() != null){
            employeeDTO.setCreatedBy(employee.getCreatedBy());
        }
        if (employee.getCreatedDate() != null){
            employeeDTO.setCreatedDate(employee.getCreatedDate());
        }
        if (employee.getUpdatedBy() != null){
            employeeDTO.setUpdatedBy(employee.getUpdatedBy());
        }
        if (employee.getUpdatedDate() != null){
            employeeDTO.setUpdatedDate(employee.getUpdatedDate());
        }

        return employeeDTO;
    }

}
