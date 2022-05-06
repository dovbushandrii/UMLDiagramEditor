/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file ClassIsNotPresentOnDiagramException.java
 */
package com.umleditor.model.classdiagram.exceptions;

import com.umleditor.model.common.exceptions.UMLException;

/**
 * Exception that is thrown when you try update not defined on diagram class
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class ClassIsNotPresentOnDiagramException extends UMLException {

    public ClassIsNotPresentOnDiagramException(String message) {
        super(message);
    }
}
