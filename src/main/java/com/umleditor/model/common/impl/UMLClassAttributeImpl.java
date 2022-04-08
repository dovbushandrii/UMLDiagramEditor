package com.umleditor.model.common.impl;

import com.umleditor.model.common.enums.UMLElementModifier;
import com.umleditor.model.common.interfaces.UMLClassAttribute;

public class UMLClassAttributeImpl extends UMLElementImpl implements UMLClassAttribute {

    private UMLElementModifier modifier;
    protected String type;

    @Override
    public UMLElementModifier getModifier() {
        return this.modifier;
    }

    @Override
    public void setModifier(UMLElementModifier modifier) {
        this.modifier = modifier;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        if (type.equals("void")) {
            throw new IllegalArgumentException("Class field cannot have key-word type");
        }
        this.type = type;
    }
}
