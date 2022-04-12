package com.umleditor.model.daos.exceptions;

import java.io.IOException;

/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class DiagramFileWriteException extends IOException {
    public DiagramFileWriteException(String message) {
        super(message);
    }
}
