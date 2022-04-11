/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file ClassIsAlreadyDefinedOnDiagramException.java
 */
package com.umleditor.model.classdiagram.exceptions;

/**
 * Exception that is thrown when you try to add class that is already defined on diagram
 */
public class ClassIsAlreadyDefinedOnDiagramException extends RuntimeException {
    public ClassIsAlreadyDefinedOnDiagramException(String message) {
        super(message);
    }
}
