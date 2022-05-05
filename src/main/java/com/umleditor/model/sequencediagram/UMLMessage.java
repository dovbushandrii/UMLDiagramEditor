package com.umleditor.model.sequencediagram;

import com.umleditor.model.common.UMLClass;

/**
 * UML Sequence Diagram Message Element
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class UMLMessage {
    private UMLClass from;
    private UMLClass to;
    private String message;

    public UMLClass getFrom() {
        return from;
    }

    public void setFrom(UMLClass from) {
        this.from = from;
    }

    public UMLClass getTo() {
        return to;
    }

    public void setTo(UMLClass to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
