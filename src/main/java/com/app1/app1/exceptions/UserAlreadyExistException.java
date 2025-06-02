package com.app1.app1.exceptions;

public class UserAlreadyExistException extends Exception {
	/**
	 * Constructs a new <code>UserAlreadyExistException</code> exception with
	 * <code>null</code> as its detail message.
	 */
	public UserAlreadyExistException() {
		super();
	}

	/**
	 * Constructs a new <code>UserAlreadyExistException</code> exception with
	 * <code>null</code> as its detail message.
	 */
	public UserAlreadyExistException(Exception cause) {
		super(cause);
	}

	/**
	 * Constructs a new <code>UserAlreadyExistException</code> exception with the
	 * specified detail message.
	 * 
	 * @param message
	 *            the detail message.
	 */
	public UserAlreadyExistException(String message) {
		super(message);
	}

	/**
	 * Constructs a new <code>UserAlreadyExistException</code> exception with the
	 * specified detail message.
	 *
	 * @param message
	 *            the detail message.
	 */
	public UserAlreadyExistException(String message, Exception cause) {
		super(message, cause);
	}
}
