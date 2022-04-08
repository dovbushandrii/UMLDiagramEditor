package com.umleditor.model.daos.exceptions;

public class DiagramFileReadException extends RuntimeException {
    public DiagramFileReadException() {}
    public DiagramFileReadException(String message) {
        super(message);
    }
    public DiagramFileReadException(Throwable cause) {
        super(cause);
    }
}