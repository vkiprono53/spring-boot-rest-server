package com.vkiprono.springbootrestserver.exceptions;

/**
 * @author vkiprono
 * @created 7/7/23
 */

public class EmployeeNotFoundException extends Exception {
    private String message;

    public EmployeeNotFoundException(String message) {
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