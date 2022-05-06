package com.umleditor.model.sequencediagram.exceptions;

import com.umleditor.model.common.exceptions.UMLException;

/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class ObjectIsAlreadyDefinedException extends UMLException {
    public ObjectIsAlreadyDefinedException(String message) {
        super(message);
    }
}
