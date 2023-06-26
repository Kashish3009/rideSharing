package com.geektrust.backend.exceptions;

public class RiderAlreadyPresentException extends Exception{
    public RiderAlreadyPresentException() {
        super();
    }
    
    public RiderAlreadyPresentException(String msg) {
        super(msg);
    }
}
