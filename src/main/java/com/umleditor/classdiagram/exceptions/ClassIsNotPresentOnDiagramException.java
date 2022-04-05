package com.umleditor.classdiagram.exceptions;

public class ClassIsNotPresentOnDiagramException extends RuntimeException {
    public ClassIsNotPresentOnDiagramException() {
    }

    public ClassIsNotPresentOnDiagramException(String message) {
        super(message);
    }
}
