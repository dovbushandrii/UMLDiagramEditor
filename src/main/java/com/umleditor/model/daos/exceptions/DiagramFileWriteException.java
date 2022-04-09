package com.umleditor.model.daos.exceptions;

import java.io.IOException;

public class DiagramFileWriteException extends IOException {
    public DiagramFileWriteException() {}
    public DiagramFileWriteException(String message) {
        super(message);
    }
    public DiagramFileWriteException(Throwable cause) {
        super(cause);
    }
}
