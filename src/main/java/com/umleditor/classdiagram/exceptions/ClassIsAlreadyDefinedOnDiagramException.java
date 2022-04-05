package com.umleditor.classdiagram.exceptions;

public class ClassIsAlreadyDefinedOnDiagramException extends RuntimeException {
    public ClassIsAlreadyDefinedOnDiagramException() {
    }

    public ClassIsAlreadyDefinedOnDiagramException(String message) {
        super(message);
    }
}
