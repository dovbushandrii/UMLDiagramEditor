package com.umleditor.model.daos.exceptions;

import java.io.IOException;

public class DiagramFileReadException extends IOException {
    public DiagramFileReadException() {}
    public DiagramFileReadException(String message) {
        super(message);
    }
    public DiagramFileReadException(Throwable cause) {
        super(cause);
    }
}
