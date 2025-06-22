package com.app1.app1.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // Optional
public class ProductAlreadyExistException extends RuntimeException {
    /**
     * Constructs a new ProductAlreadyExistException with null as its detail
     * message.
     */
    public ProductAlreadyExistException() {
        super();
    }

    /**
     * Constructs a new ProductAlreadyExistException with the specified cause.
     *
     * @param cause the cause of the exception.
     */
    public ProductAlreadyExistException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ProductAlreadyExistException with the specified detail
     * message.
     *
     * @param message the detail message.
     */
    public ProductAlreadyExistException(String message) {
        super(message);
    }

    /**
     * Constructs a new ProductAlreadyExistException with the specified detail
     * message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause of the exception.
     */
    public ProductAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
