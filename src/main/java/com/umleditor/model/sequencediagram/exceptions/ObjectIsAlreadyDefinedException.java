package com.umleditor.model.sequencediagram.exceptions;

import com.umleditor.model.common.exceptions.UMLException;

public class ObjectIsAlreadyDefinedException extends UMLException {
    public ObjectIsAlreadyDefinedException(String message) {
        super(message);
    }
}
