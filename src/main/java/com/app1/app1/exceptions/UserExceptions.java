package com.app1.app1.exceptions;

public class UserExceptions extends RuntimeException {
    public UserExceptions() {
        super();
    }

    public UserExceptions(String message) {
        super(message);
    }

    public UserExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExceptions(Throwable cause) {
        super(cause);
    }
}
