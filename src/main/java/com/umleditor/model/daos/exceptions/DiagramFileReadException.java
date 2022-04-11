/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file DiagramFileReadException.java
 */
package com.umleditor.model.daos.exceptions;

import java.io.IOException;

public class DiagramFileReadException extends IOException {
    public DiagramFileReadException(String message) {
        super(message);
    }
}
