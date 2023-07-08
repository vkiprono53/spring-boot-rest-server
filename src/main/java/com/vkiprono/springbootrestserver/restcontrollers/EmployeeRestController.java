package com.vkiprono.springbootrestserver.restcontrollers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.vkiprono.springbootrestserver.constants.EmployeeConstant;
import com.vkiprono.springbootrestserver.dtos.*;
import com.vkiprono.springbootrestserver.exceptions.DataValidationException;
import com.vkiprono.springbootrestserver.exceptions.EmployeeDeleteException;
import com.vkiprono.springbootrestserver.exceptions.EmployeeNotFoundException;
import com.vkiprono.springbootrestserver.exceptions.EmployeeSaveOrUpdateException;
import com.vkiprono.springbootrestserver.services.EmployeeServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author vkiprono
 * @created 6/9/23
 */

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeRestController {
    private final EmployeeServiceI employeeServiceI;

    private final Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);

    @Autowired
    public EmployeeRestController(EmployeeServiceI employeeServiceI) {
        this.employeeServiceI = employeeServiceI;
    }


    //Save
    @PostMapping
    public String save(@RequestBody String employeeDetails) throws Exception {

        logger.info(":::::START EmployeeRestController.save():::::");

        logger.info(":::::JSON to parse is:::::" + employeeDetails);

        ResponseDTO responseDTO = new ResponseDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        JsonMapper jsonMapper = new JsonMapper();
        AddressDTO addressDTO = new AddressDTO();
        EMPREQUESTSAVE empSaveRequestDTO = new EMPREQUESTSAVE();
        String responseCode = "";
        String responseMessage = "";
        String responseStatus = "";
        StringBuilder output = new StringBuilder();

        try {

           if (employeeDetails != null) {
               empSaveRequestDTO = jsonMapper.readValue(employeeDetails, EMPREQUESTSAVE.class);
           }
           if (empSaveRequestDTO != null) {
               employeeDTO = empSaveRequestDTO.getRequestDTO().getEmployeeDTO();
               addressDTO = empSaveRequestDTO.getRequestDTO().getAddressDTO();
           }
           //check for null:Employee Details
           if (employeeDTO.getPNo() == null || employeeDTO.getPNo().trim().isEmpty()
                   || employeeDTO.getFirstName() == null || employeeDTO.getFirstName().trim().isEmpty()
                   || employeeDTO.getLastName() == null || employeeDTO.getLastName().trim().isEmpty()
                   || employeeDTO.getEmail() == null || employeeDTO.getEmail().trim().isEmpty()
                   || employeeDTO.getDepartmentId() == null)   {

               throw new DataValidationException(EmployeeConstant.DATA_VALIDATION_MESSAGE_ERROR);
           }
           //address details
           if (addressDTO.getAddress() == null || addressDTO.getAddress().trim().isEmpty()
                   || addressDTO.getPhone() == null || addressDTO.getPhone().trim().isEmpty()
                   || addressDTO.getPostalCode() == null || addressDTO.getPostalCode().trim().isEmpty()){

               throw new DataValidationException(EmployeeConstant.DATA_VALIDATION_MESSAGE_ERROR);

           }

           employeeDTO = employeeServiceI.save(empSaveRequestDTO);

           if (employeeDTO.getPNo() != null) {
               responseCode = EmployeeConstant.SUCCESS_CODE_SAVE;
               responseMessage = EmployeeConstant.SUCCESS_MESSAGE_SAVE;
               responseStatus = EmployeeConstant.STATUS_OK;
           } else {
               throw new EmployeeSaveOrUpdateException(EmployeeConstant.UNSUCCESSFUL_MESSAGE_SAVE_UPDATE);
           }

       }
       catch (DataValidationException dataValidationException) {
           responseCode = EmployeeConstant.DATA_VALIDATION_CODE_ERROR;
           responseMessage = EmployeeConstant.DATA_VALIDATION_MESSAGE_ERROR;
           responseStatus = EmployeeConstant.STATUS_NOK;
       }
       catch (EmployeeSaveOrUpdateException saveOrUpdateException) {
           logger.info(":::::EmployeeSaveOrUpdateException EmployeeRestController.update():::::");
           responseCode = EmployeeConstant.UNSUCCESSFUL_CODE_SAVE_UPDATE;
           responseMessage = EmployeeConstant.UNSUCCESSFUL_MESSAGE_SAVE_UPDATE;
           responseStatus = EmployeeConstant.STATUS_NOK;
       }
        responseDTO.setCode(responseCode);
        responseDTO.setMessage(responseMessage);
        responseDTO.setStatus(responseStatus);

        logger.info(":::::END EmployeeRestController.save():::::");

        output.append(jsonMapper.writeValueAsString(responseDTO));
        return output.toString();
    }


    //update
    @PutMapping
    public String update(@RequestBody String employeeDetails) throws Exception {

        logger.info(":::::START EmployeeRestController.update():::::");

        logger.info(":::::JSON parsed for update:::::" + employeeDetails);
        ResponseDTO responseDTO = new ResponseDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        EmployeeDTO employeeDTOExist = null;
        AddressDTO addressDTO = new AddressDTO();
        EMPREQUESTSAVE empSaveRequestDTO = new EMPREQUESTSAVE();

        StringBuilder output = new StringBuilder();
        JsonMapper jsonMapper = new JsonMapper();
        String responseCode = "";
        String responseMessage = "";
        String responseStatus = "";

        try {

            if (employeeDetails != null) {
                empSaveRequestDTO = jsonMapper.readValue(employeeDetails, EMPREQUESTSAVE.class);
            }
            if (empSaveRequestDTO != null) {
                employeeDTO = empSaveRequestDTO.getRequestDTO().getEmployeeDTO();
                addressDTO = empSaveRequestDTO.getRequestDTO().getAddressDTO();
            }
            //check for null:Employee Details
            if (employeeDTO.getPNo() == null || employeeDTO.getPNo().trim().isEmpty()
               || employeeDTO.getFirstName() == null || employeeDTO.getFirstName().trim().isEmpty()
               || employeeDTO.getLastName() == null || employeeDTO.getLastName().trim().isEmpty()
               || employeeDTO.getEmail() == null || employeeDTO.getEmail().trim().isEmpty()
               || employeeDTO.getDepartmentId() == null)   {

                throw new DataValidationException(EmployeeConstant.DATA_VALIDATION_MESSAGE_ERROR);
            }
            //address details
            if (addressDTO.getAddress() == null || addressDTO.getAddress().trim().isEmpty()
               || addressDTO.getPhone() == null || addressDTO.getPhone().trim().isEmpty()
               || addressDTO.getPostalCode() == null || addressDTO.getPostalCode().trim().isEmpty()){

                throw new DataValidationException(EmployeeConstant.DATA_VALIDATION_MESSAGE_ERROR);

            }
            //check if user exist
            employeeDTOExist = employeeServiceI.findEmployeeByPNo(employeeDTO.getPNo());

            if (employeeDTOExist == null){

                throw new EmployeeNotFoundException(EmployeeConstant.EMPLOYEE_NOT_FOUND_MESSAGE);

            }

            employeeDTO = employeeServiceI.update(empSaveRequestDTO);


            if (employeeDTO.getPNo() != null) {
                responseCode = EmployeeConstant.SUCCESS_CODE_UPDATE;
                responseMessage = EmployeeConstant.SUCCESS_MESSAGE_UPDATE;
                responseStatus = EmployeeConstant.STATUS_OK;
            } else {
                throw new EmployeeSaveOrUpdateException(EmployeeConstant.UNSUCCESSFUL_MESSAGE_SAVE_UPDATE);
            }
        }
        catch (DataValidationException dataValidationException) {
            responseCode = EmployeeConstant.DATA_VALIDATION_CODE_ERROR;
            responseMessage = EmployeeConstant.DATA_VALIDATION_MESSAGE_ERROR;
            responseStatus = EmployeeConstant.STATUS_NOK;
        }
        catch (EmployeeNotFoundException employeeNotFoundException) {
            logger.info(":::::EmployeeNotFoundException EmployeeRestController.update():::::");
            responseCode = EmployeeConstant.EMPLOYEE_NOT_FOUND_CODE;
            responseMessage = EmployeeConstant.EMPLOYEE_NOT_FOUND_MESSAGE;
            responseStatus = EmployeeConstant.STATUS_NOK;
        }
        catch (EmployeeSaveOrUpdateException saveOrUpdateException) {
            logger.info(":::::EmployeeSaveOrUpdateException EmployeeRestController.update():::::");
            responseCode = EmployeeConstant.UNSUCCESSFUL_CODE_SAVE_UPDATE;
            responseMessage = EmployeeConstant.UNSUCCESSFUL_MESSAGE_SAVE_UPDATE;
            responseStatus = EmployeeConstant.STATUS_NOK;
        }

        responseDTO.setCode(responseCode);
        responseDTO.setMessage(responseMessage);
        responseDTO.setStatus(responseStatus);

        logger.info(":::::END EmployeeRestController.update():::::");

        output.append(jsonMapper.writeValueAsString(responseDTO));
        return output.toString();
    }

    //Get Employee Details:
    @GetMapping
    public String getEmployeeDetails(@RequestBody String employeePno) throws Exception {
        logger.info(":::::START EmployeeRestController.getEmployeeDetails():::::");

        StringBuilder output = new StringBuilder();
        JsonMapper jsonMapper = new JsonMapper();
        String responseCode = "";
        String responseMessage = "";
        String responseStatus = "";
        EmpGetAndDeleteRequestDTO requestDTO = new EmpGetAndDeleteRequestDTO();
        EmpGetResponseDTO empGetResponseDTO = new EmpGetResponseDTO();
        ResponseDTO response = new ResponseDTO();
        EmployeeDTO employeeDTO = null;
        EmployeeDtls employeeDtls = new EmployeeDtls();

        try {

            requestDTO = jsonMapper.readValue(employeePno, EmpGetAndDeleteRequestDTO.class);

            if (requestDTO.getPNo() == null || requestDTO.getPNo().trim().isEmpty()) {
                throw new DataValidationException(EmployeeConstant.DATA_VALIDATION_MESSAGE_ERROR);
              }

            employeeDTO = employeeServiceI.findEmployeeByPNo(requestDTO.getPNo());

            if (employeeDTO != null){
                responseCode = EmployeeConstant.SUCCESS_CODE_VIEW;
                responseMessage = EmployeeConstant.SUCCESS_MESSAGE_VIEW;
                responseStatus = EmployeeConstant.STATUS_OK;

                employeeDtls.setPNo(employeeDTO.getPNo());
                employeeDtls.setFirstName(employeeDTO.getFirstName());
                employeeDtls.setLastName(employeeDTO.getLastName());
                empGetResponseDTO.setEmployeeDtls(employeeDtls);

            }
            else {

                throw new EmployeeNotFoundException(EmployeeConstant.EMPLOYEE_NOT_FOUND_MESSAGE);

            }

        }
        catch (EmployeeNotFoundException employeeNotFoundException){
            responseCode = EmployeeConstant.EMPLOYEE_NOT_FOUND_CODE;
            responseMessage = EmployeeConstant.EMPLOYEE_NOT_FOUND_MESSAGE;
            responseStatus = EmployeeConstant.STATUS_NOK;
        }

         catch (DataValidationException dataValidationException){
            responseCode = EmployeeConstant.DATA_VALIDATION_CODE_ERROR;
            responseMessage = EmployeeConstant.DATA_VALIDATION_MESSAGE_ERROR;
            responseStatus = EmployeeConstant.STATUS_NOK;
        }

        response.setCode(responseCode);
        response.setMessage(responseMessage);
        response.setStatus(responseStatus);

        empGetResponseDTO.setResponseDTO(response);

        output.append(jsonMapper.writeValueAsString(empGetResponseDTO));

        logger.info("::::END EmployeeRestController.getEmployeeDetails():::::");

        return output.toString();
    }

   //Delete Employee Details:
    @DeleteMapping
    public String deleteEmployee(@RequestBody String employeePno) throws Exception {
        logger.info(":::::START EmployeeRestController.deleteEmployee():::::");

        JsonMapper jsonMapper = new JsonMapper();
        EmpGetAndDeleteRequestDTO requestDTO = new EmpGetAndDeleteRequestDTO();
        EmpGetResponseDTO empGetResponseDTO = new EmpGetResponseDTO();
        ResponseDTO response = new ResponseDTO();
        EmployeeDTO employeeDTO = null;
        EmployeeDtls employeeDtls = new EmployeeDtls();
        String responseCode = "";
        String responseMessage = "";
        String responseStatus = "";
        StringBuilder output = new StringBuilder();

        try {
            requestDTO = jsonMapper.readValue(employeePno,EmpGetAndDeleteRequestDTO.class);

            if (requestDTO.getPNo() == null || requestDTO.getPNo().trim().isEmpty()) {

                throw new DataValidationException(EmployeeConstant.DATA_VALIDATION_MESSAGE_ERROR);

            }
            //Check if employee Exist:
            employeeDTO = employeeServiceI.findEmployeeByPNo(requestDTO.getPNo());

            if (employeeDTO == null){
                throw new EmployeeNotFoundException(EmployeeConstant.EMPLOYEE_NOT_FOUND_MESSAGE);
            }

            employeeDTO = employeeServiceI.deleteEmployee(requestDTO.getPNo());

            if (employeeDTO.getActive().equalsIgnoreCase(EmployeeConstant.IN_ACTIVE)){
                responseCode = EmployeeConstant.SUCCESS_CODE_DELETE;
                responseMessage = EmployeeConstant.SUCCESS_MESSAGE_DELETE;
                responseStatus = EmployeeConstant.STATUS_OK;

                employeeDtls.setPNo(employeeDTO.getPNo());
                employeeDtls.setFirstName(employeeDTO.getFirstName());
                employeeDtls.setLastName(employeeDTO.getLastName());
                
                empGetResponseDTO.setEmployeeDtls(employeeDtls);

            }
            else {
                throw new EmployeeDeleteException(EmployeeConstant.FAILED_MESSAGE_DELETE);
            }
        }

        catch (DataValidationException exception){
            logger.info(":::::DataValidationException EmployeeRestController.deleteEmployee():::::");
            responseCode = EmployeeConstant.DATA_VALIDATION_CODE_ERROR;
            responseMessage = EmployeeConstant.DATA_VALIDATION_MESSAGE_ERROR;
            responseStatus = EmployeeConstant.STATUS_NOK;
        }
        catch (EmployeeNotFoundException employeeNotFoundException){
            logger.info(":::::EmployeeNotFoundException EmployeeRestController.deleteEmployee():::::");
            responseCode = EmployeeConstant.EMPLOYEE_NOT_FOUND_CODE;
            responseMessage = EmployeeConstant.EMPLOYEE_NOT_FOUND_MESSAGE;
            responseStatus = EmployeeConstant.STATUS_NOK;
        }
        catch (EmployeeDeleteException employeeDeleteException){
            logger.info(":::::EmployeeDeleteException EmployeeRestController.deleteEmployee():::::");
            responseCode = EmployeeConstant.UNSUCCESSFUL_CODE_DELETE;
            responseMessage = EmployeeConstant.FAILED_MESSAGE_DELETE;
            responseStatus = EmployeeConstant.STATUS_NOK;
        }
        response.setCode(responseCode);
        response.setMessage(responseMessage);
        response.setStatus(responseStatus);

        empGetResponseDTO.setResponseDTO(response);

        output.append(jsonMapper.writeValueAsString(empGetResponseDTO));

        logger.info("::::END EmployeeRestController.deleteEmployee():::::");

        return output.toString();
    }


}
