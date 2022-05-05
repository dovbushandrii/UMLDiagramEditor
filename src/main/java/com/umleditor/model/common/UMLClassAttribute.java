package com.umleditor.model.common;

import com.umleditor.model.common.enums.UMLElementModifier;

/**
 * UML Class Attribute class
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
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

    public UMLElementModifier getModifier() {
        return modifier;
    }

    public void setModifier(UMLElementModifier modifier) {
        this.modifier = modifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
