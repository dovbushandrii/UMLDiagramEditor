package com.umleditor.model.sequencediagram.exceptions;

import com.umleditor.model.common.exceptions.UMLException;

/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class ObjectIsNotPresentOnDiagramException extends UMLException {
    public ObjectIsNotPresentOnDiagramException(String message) {
        super(message);
    }
}
