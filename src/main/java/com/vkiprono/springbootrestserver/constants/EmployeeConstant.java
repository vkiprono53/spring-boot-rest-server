package com.vkiprono.springbootrestserver.constants;

/**
 * @author vkiprono
 * @created 6/14/23
 */

public class EmployeeConstant {
    public static Long SYSTEM_ID = 1L;

    public static String ACTIVE = "Y";

    public static String IN_ACTIVE = "N";


    //Delete
    public static String SUCCESS_CODE_DELETE = "20000";
    public static String SUCCESS_MESSAGE_DELETE = "Deleted Successfully";

    public static String UNSUCCESSFUL_CODE_DELETE = "20007";
    public static String FAILED_MESSAGE_DELETE = "Error deleting";

    //save
    public static String SUCCESS_CODE_SAVE = "20001";
    public static String SUCCESS_MESSAGE_SAVE = "Successfully added";

    public static String UNSUCCESSFUL_CODE_SAVE_UPDATE = "20003";
    public static String UNSUCCESSFUL_MESSAGE_SAVE_UPDATE = "Failed to update or save";

    //update
    public static String SUCCESS_CODE_UPDATE = "20004";
    public static String SUCCESS_MESSAGE_UPDATE = "Successfully updated";

    //View
    public static String SUCCESS_CODE_VIEW = "20005";
    public static String SUCCESS_MESSAGE_VIEW = "Employee found";

    public static String EMPLOYEE_NOT_FOUND_CODE= "20006";
    public static String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee Not found";

    //Data Validation:

    public static String DATA_VALIDATION_CODE_ERROR = "20008";
    public static String DATA_VALIDATION_MESSAGE_ERROR = "Data Validation Error";

    public static String USER_NOT_FOUND_MESSAGE = "User Not found";
    public static String USER_NOT_FOUND_CODE= "20009";


    //Status
    public static String STATUS_OK = "OK";
    public static String STATUS_NOK = "NOK";

}
