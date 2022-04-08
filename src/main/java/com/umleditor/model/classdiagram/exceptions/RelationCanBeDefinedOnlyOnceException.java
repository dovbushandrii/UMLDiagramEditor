package com.umleditor.model.classdiagram.exceptions;

public class RelationCanBeDefinedOnlyOnceException extends RuntimeException {
    public RelationCanBeDefinedOnlyOnceException() {
    }

    public RelationCanBeDefinedOnlyOnceException(String message) {
        super(message);
    }
}
