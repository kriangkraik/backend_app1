package com.app1.app1.user.exceptions;

public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new <code>UserNotFoundException</code> exception with
     * <code>null</code> as its detail message.
     */
    public UserNotFoundException() {
        super();
    }

    /**
     * Constructs a new <code>UserAlreadyExistException</code> exception with
     * <code>null</code> as its detail message.
     */
    public UserNotFoundException(Exception cause) {
        super(cause);
    }

    /**
     * Constructs a new <code>UserNotFoundException</code> exception with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new <code>UserNotFoundException</code> exception with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public UserNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
