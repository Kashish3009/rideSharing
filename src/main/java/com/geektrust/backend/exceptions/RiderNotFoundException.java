package com.geektrust.backend.exceptions;

public class RiderNotFoundException extends RuntimeException {
    
    public RiderNotFoundException() {
     super();
    }
    
    public RiderNotFoundException(String msg) {
     super(msg);
    }
}
