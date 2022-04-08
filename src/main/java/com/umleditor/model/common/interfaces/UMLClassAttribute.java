package com.umleditor.model.common.interfaces;

import com.umleditor.model.common.enums.UMLElementModifier;

public interface UMLClassAttribute extends UMLElement{
    UMLElementModifier getModifier();
    void setModifier(UMLElementModifier modifier);
    String getType();
    void setType(String type);
}
