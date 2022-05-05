package com.umleditor.model.sequencediagram;

import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLElement;

/**
 * UML Sequence Diagram Message Element
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class UMLMessage {
    private UMLElement from;
    private UMLElement to;
    private String message;

    public UMLElement getFrom() {
        return from;
    }

    public void setFrom(UMLElement from) {
        this.from = from;
    }

    public UMLElement getTo() {
        return to;
    }

    public void setTo(UMLElement to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
