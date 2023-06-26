package com.geektrust.backend.exceptions;

public class DriverAlreadyPresentException extends Exception {
    public DriverAlreadyPresentException() {
        super();
    }
       
    public DriverAlreadyPresentException(String msg) {
        super(msg);
    }
}
