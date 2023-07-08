package com.vkiprono.springbootrestserver.exceptions;

/**
 * @author vkiprono
 * @created 7/8/23
 */

public class EmployeeSaveOrUpdateException extends Exception{
    private String message;

    public EmployeeSaveOrUpdateException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
