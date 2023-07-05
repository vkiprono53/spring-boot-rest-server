package com.vkiprono.springbootrestserver.restcontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.vkiprono.springbootrestserver.constants.EmployeeConstant;
import com.vkiprono.springbootrestserver.dtos.*;
import com.vkiprono.springbootrestserver.services.AddressServiceI;
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
    public String save(@RequestBody String employeeDetails) throws JsonProcessingException {

        logger.info(":::::START EmployeeRestController.save():::::");

        logger.info(":::::JSON to parse is:::::" + employeeDetails);

        ResponseDTO responseDTO = new ResponseDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        employeeDTO = employeeServiceI.save(employeeDetails);

        if (employeeDTO.getPNo() != null) {
            responseDTO.setCode(EmployeeConstant.SUCCESS_CODE_SAVE);
            responseDTO.setMessage(EmployeeConstant.SUCCESS_MESSAGE_SAVE);
            responseDTO.setStatus(EmployeeConstant.STATUS_OK);
        } else {
            responseDTO.setCode(EmployeeConstant.UNSUCCESSFUL_CODE_SAVE_UPDATE);
            responseDTO.setMessage(EmployeeConstant.UNSUCCESSFUL_MESSAGE_SAVE_UPDATE);
            responseDTO.setStatus(EmployeeConstant.STATUS_NOK);
        }
        logger.info(":::::END EmployeeRestController.save():::::");

        return objectMapper.writeValueAsString(responseDTO);
    }


    //update
    @PutMapping
    public String update(@RequestBody String employeeDetails) throws JsonProcessingException {

        logger.info(":::::START EmployeeRestController.update():::::");

        logger.info(":::::JSON parsed for update:::::" + employeeDetails);

        ResponseDTO responseDTO = new ResponseDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        employeeDTO = employeeServiceI.update(employeeDetails);


        if (employeeDTO.getPNo() != null) {
            responseDTO.setCode(EmployeeConstant.SUCCESS_CODE_UPDATE);
            responseDTO.setMessage(EmployeeConstant.SUCCESS_MESSAGE_UPDATE);
            responseDTO.setStatus(EmployeeConstant.STATUS_OK);
        } else {
            responseDTO.setCode(EmployeeConstant.UNSUCCESSFUL_CODE_SAVE_UPDATE);
            responseDTO.setMessage(EmployeeConstant.UNSUCCESSFUL_MESSAGE_SAVE_UPDATE);
            responseDTO.setStatus(EmployeeConstant.STATUS_NOK);
        }
        logger.info(":::::END EmployeeRestController.update():::::");

        return objectMapper.writeValueAsString(responseDTO);
    }

    //Get Employee Details:
    @GetMapping
    public String getEmployeeDetails(@RequestBody String employeePno) throws JsonProcessingException {
        logger.info(":::::START EmployeeRestController.getEmployeeDetails():::::");

        StringBuilder output = new StringBuilder();
        JsonMapper jsonMapper = new JsonMapper();
        EmpGetAndDeleteRequestDTO requestDTO = new EmpGetAndDeleteRequestDTO();
        EmpGetResponseDTO empGetResponseDTO = new EmpGetResponseDTO();
        ResponseDTO response = new ResponseDTO();
        EmployeeDTO employeeDTO = null;
        EmployeeDtls employeeDtls = new EmployeeDtls();

        if (employeePno == null || employeePno.isEmpty()) {
            response.setCode(EmployeeConstant.DATA_VALIDATION_CODE_ERROR);
            response.setMessage(EmployeeConstant.DATA_VALIDATION_MESSAGE_ERROR);
            response.setStatus(EmployeeConstant.STATUS_NOK);

            empGetResponseDTO.setResponseDTO(response);
            return jsonMapper.writeValueAsString(empGetResponseDTO);

        }
        requestDTO = jsonMapper.readValue(employeePno, EmpGetAndDeleteRequestDTO.class);

        employeeDTO = employeeServiceI.findEmployeeByPNo(requestDTO.getPNo());

        if (employeeDTO != null){
            response.setCode(EmployeeConstant.SUCCESS_CODE_VIEW);
            response.setMessage(EmployeeConstant.SUCCESS_MESSAGE_VIEW);
            response.setStatus(EmployeeConstant.STATUS_OK);

            employeeDtls.setPNo(employeeDTO.getPNo());
            employeeDtls.setFirstName(employeeDTO.getFirstName());
            employeeDtls.setLastName(employeeDTO.getLastName());
            empGetResponseDTO.setEmployeeDtls(employeeDtls);

        }
        else {
            response.setCode(EmployeeConstant.UNSUCCESSFUL_CODE_VIEW);
            response.setMessage(EmployeeConstant.UNSUCCESSFUL_MESSAGE_VIEW);
            response.setStatus(EmployeeConstant.STATUS_NOK);

        }
        empGetResponseDTO.setResponseDTO(response);

        logger.info("::::END EmployeeRestController.getEmployeeDetails():::::");

        return jsonMapper.writeValueAsString(empGetResponseDTO);
    }

   //Delete Employee Details:
    @DeleteMapping
    public String deleteEmployee(@RequestBody String employeePno) throws JsonProcessingException {
        logger.info(":::::START EmployeeRestController.deleteEmployee():::::");

        JsonMapper jsonMapper = new JsonMapper();
        EmpGetAndDeleteRequestDTO requestDTO = new EmpGetAndDeleteRequestDTO();
        EmpGetResponseDTO empGetResponseDTO = new EmpGetResponseDTO();
        ResponseDTO response = new ResponseDTO();
        EmployeeDTO employeeDTO = null;
        EmployeeDtls employeeDtls = new EmployeeDtls();

        if (employeePno == null || employeePno.isEmpty()) {
            response.setCode(EmployeeConstant.DATA_VALIDATION_CODE_ERROR);
            response.setMessage(EmployeeConstant.DATA_VALIDATION_MESSAGE_ERROR);
            response.setStatus(EmployeeConstant.STATUS_NOK);

            empGetResponseDTO.setResponseDTO(response);
            return jsonMapper.writeValueAsString(empGetResponseDTO);

        }

        employeeDTO = employeeServiceI.deleteEmployee(employeePno);

        if (employeeDTO != null){
            response.setCode(EmployeeConstant.SUCCESS_CODE_VIEW);
            response.setMessage(EmployeeConstant.SUCCESS_MESSAGE_VIEW);
            response.setStatus(EmployeeConstant.STATUS_OK);

            employeeDtls.setPNo(employeeDTO.getPNo());
            employeeDtls.setFirstName(employeeDTO.getFirstName());
            employeeDtls.setLastName(employeeDTO.getLastName());

        }
        else {
            response.setCode(EmployeeConstant.UNSUCCESSFUL_CODE_VIEW);
            response.setMessage(EmployeeConstant.UNSUCCESSFUL_MESSAGE_VIEW);
            response.setStatus(EmployeeConstant.STATUS_NOK);

        }
        empGetResponseDTO.setResponseDTO(response);
        empGetResponseDTO.setEmployeeDtls(employeeDtls);

        logger.info("::::END EmployeeRestController.deleteEmployee():::::");

        return jsonMapper.writeValueAsString(empGetResponseDTO);
    }


}
