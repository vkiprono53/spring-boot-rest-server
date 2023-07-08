package com.vkiprono.springbootrestserver.exceptions;

/**
 * @author vkiprono
 * @created 7/7/23
 */

public class DataValidationException extends Exception{
    private String message;

    public DataValidationException(String message) {
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
