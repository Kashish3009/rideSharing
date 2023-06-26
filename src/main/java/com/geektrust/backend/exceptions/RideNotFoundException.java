package com.geektrust.backend.exceptions;

public class RideNotFoundException extends RuntimeException {
    
    public RideNotFoundException() {
     super();
    }
    public RideNotFoundException(String msg) {
     super(msg);
    }
}
