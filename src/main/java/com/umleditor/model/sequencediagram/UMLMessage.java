package com.umleditor.model.sequencediagram;

import com.umleditor.model.common.UMLClass;
import lombok.Data;

/**
 * UML Sequence Diagram Message Element
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
@Data
public class UMLMessage {
    private UMLClass from;
    private UMLClass to;
    private String message;
}
