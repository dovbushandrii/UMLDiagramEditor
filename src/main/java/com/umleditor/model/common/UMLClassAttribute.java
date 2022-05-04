package com.umleditor.model.common;

import com.umleditor.model.common.enums.UMLElementModifier;
import lombok.Data;

/**
 * UML Class Attribute class
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
@Data
public class UMLClassAttribute extends UMLElement {
    private UMLElementModifier modifier = UMLElementModifier.PRIVATE;
    protected String type = "";
}
