package com.app1.app1.user.exceptions;

public class UserExceptions extends RuntimeException {
    /**
     * Constructs a new <code>UserExceptions</code> exception with
     * <code>null</code> as its detail message.
     */
    public UserExceptions() {
        super();
    }

    /**
     * Constructs a new <code>UserExceptions</code> exception with
     * <code>null</code> as its detail message.
     */
    public UserExceptions(String message) {
        super(message);
    }

    /**
     * Constructs a new <code>UserExceptions</code> exception with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public UserExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new <code>UserExceptions</code> exception with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public UserExceptions(Throwable cause) {
        super(cause);
    }
}
