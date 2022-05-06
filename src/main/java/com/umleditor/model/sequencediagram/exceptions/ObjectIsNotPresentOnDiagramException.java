package com.umleditor.model.sequencediagram.exceptions;

import com.umleditor.model.common.exceptions.UMLException;

public class ObjectIsNotPresentOnDiagramException extends UMLException {
    public ObjectIsNotPresentOnDiagramException(String message) {
        super(message);
    }
}
