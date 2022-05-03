package com.umleditor.model.common;

import com.umleditor.model.common.enums.UMLElementModifier;

/**
 * UML Class Attribute DTO class
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class UMLClassAttribute extends UMLElement {

    private UMLElementModifier modifier = UMLElementModifier.PRIVATE;
    protected String type = "";

    public UMLClassAttribute() {}

    public UMLClassAttribute(String name,
                             UMLElementModifier modifier,
                             String type) {
        super.setName(name);
        setModifier(modifier);
        setType(type);
    }

    public UMLElementModifier getModifier() {
        return this.modifier;
    }

    public void setModifier(UMLElementModifier modifier) {
        this.modifier = modifier;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        if (type.equals("void")) {
            throw new IllegalArgumentException("Class field cannot have key-word type");
        }
        this.type = type;
    }
}
