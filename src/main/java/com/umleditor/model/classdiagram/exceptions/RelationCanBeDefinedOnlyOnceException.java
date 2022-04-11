/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file RelationCanBeDefinedOnlyOnceException.java
 */
package com.umleditor.model.classdiagram.exceptions;

/**
 * Exception that is thrown when you try to create second relation on same classes
 */
public class RelationCanBeDefinedOnlyOnceException extends RuntimeException {
    public RelationCanBeDefinedOnlyOnceException(String message) {
        super(message);
    }
}
