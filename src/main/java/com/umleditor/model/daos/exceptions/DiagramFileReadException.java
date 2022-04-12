package com.umleditor.model.daos.exceptions;

import java.io.IOException;

/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class DiagramFileReadException extends IOException {
    public DiagramFileReadException(String message) {
        super(message);
    }
}
