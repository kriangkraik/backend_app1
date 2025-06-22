package com.app1.app1.product.exceptions;

public class ProductNotFoundException extends RuntimeException {
    /**
     * Constructs a new <code>UserNotFoundException</code> exception with
     * <code>null</code> as its detail message.
     */
    public ProductNotFoundException() {
        super();
    }

    /**
     * Constructs a new <code>UserAlreadyExistException</code> exception with
     * <code>null</code> as its detail message.
     */
    public ProductNotFoundException(Exception cause) {
        super(cause);
    }

    /**
     * Constructs a new <code>ProductNotFoundException</code> exception with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new <code>ProductNotFoundException</code> exception with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public ProductNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
