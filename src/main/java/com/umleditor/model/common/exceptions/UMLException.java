package com.umleditor.model.common.exceptions;

/**
 * Parent for all exceptions defined in this project
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class UMLException extends RuntimeException {
    public UMLException(String message) {
        super(message);
    }
}
