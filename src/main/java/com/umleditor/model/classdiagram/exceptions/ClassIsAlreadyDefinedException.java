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
public class ClassIsAlreadyDefinedException extends RuntimeException {
    public ClassIsAlreadyDefinedException(String message) {
        super(message);
    }
}
