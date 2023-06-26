package com.geektrust.backend.exceptions;

public class NoSuchCommandException extends Exception {
    public NoSuchCommandException() {
        super();
    }
    public NoSuchCommandException(String msg) {
        super(msg);
    }
}
