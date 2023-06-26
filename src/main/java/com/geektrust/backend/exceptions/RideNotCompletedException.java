package com.geektrust.backend.exceptions;

public class RideNotCompletedException extends Exception {
    public RideNotCompletedException() {
        super();
    }
         
    public RideNotCompletedException(String msg) {
        super(msg);
    }
}
