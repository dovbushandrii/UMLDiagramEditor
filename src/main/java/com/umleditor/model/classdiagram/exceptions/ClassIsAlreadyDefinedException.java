/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file ClassIsAlreadyDefinedOnDiagramException.java
 */
package com.umleditor.model.classdiagram.exceptions;

import com.umleditor.model.common.exceptions.UMLException;

/**
 * Exception that is thrown when you try to add class that is already defined on diagram
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class ClassIsAlreadyDefinedException extends UMLException {
    public ClassIsAlreadyDefinedException(String message) {
        super(message);
    }
}
