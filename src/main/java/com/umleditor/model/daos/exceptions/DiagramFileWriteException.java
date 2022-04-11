/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file DiagramFileWriteException.java
 */
package com.umleditor.model.daos.exceptions;

import java.io.IOException;

public class DiagramFileWriteException extends IOException {
    public DiagramFileWriteException(String message) {
        super(message);
    }
}
