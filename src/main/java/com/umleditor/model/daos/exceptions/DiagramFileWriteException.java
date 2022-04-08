package com.umleditor.model.daos.exceptions;

public class DiagramFileWriteException extends RuntimeException {
    public DiagramFileWriteException() {}
    public DiagramFileWriteException(String message) {
        super(message);
    }
    public DiagramFileWriteException(Throwable cause) {
        super(cause);
    }
}