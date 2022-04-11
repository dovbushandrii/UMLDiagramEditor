/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file ClassIsNotPresentOnDiagramException.java
 */
package com.umleditor.model.classdiagram.exceptions;

/**
 * Exception that is thrown when you try update not defined on diagram class
 */
public class ClassIsNotPresentOnDiagramException extends RuntimeException {

    public ClassIsNotPresentOnDiagramException(String message) {
        super(message);
    }
}
