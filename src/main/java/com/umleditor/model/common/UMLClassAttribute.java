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
    protected UMLElementModifier modifier = UMLElementModifier.PRIVATE;
    protected String type = "";

    public UMLClassAttribute() {
        super();
    }

    public UMLClassAttribute(String name, UMLElementModifier modifier, String type) {
        this.name = name;
        this.modifier = modifier;
        this.type = type;
    }
}
