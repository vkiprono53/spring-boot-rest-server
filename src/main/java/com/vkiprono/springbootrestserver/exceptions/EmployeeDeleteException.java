package com.vkiprono.springbootrestserver.exceptions;

/**
 * @author vkiprono
 * @created 7/8/23
 */

public class EmployeeDeleteException extends Exception{
    private String message;

    public EmployeeDeleteException(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
