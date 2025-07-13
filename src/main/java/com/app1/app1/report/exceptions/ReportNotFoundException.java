package com.app1.app1.report.exceptions;

public class ReportNotFoundException extends RuntimeException {
    /**
     * Constructs a new <code>ReportNotFoundException</code> exception with
     * <code>null</code> as its detail message.
     */
    public ReportNotFoundException() {
        super();
    }

    /**
     * Constructs a new <code>ReportNotFoundException</code> exception with
     * <code>null</code> as its detail message.
     */
    public ReportNotFoundException(Exception cause) {
        super(cause);
    }

    /**
     * Constructs a new <code>ReportNotFoundException</code> exception with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public ReportNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new <code>ReportNotFoundException</code> exception with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public ReportNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
