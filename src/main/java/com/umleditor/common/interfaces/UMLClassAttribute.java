package com.umleditor.common.interfaces;

import com.umleditor.common.enums.UMLElementModifier;

public interface UMLClassAttribute extends UMLElement{
    UMLElementModifier getModifier();
    void setModifier(UMLElementModifier modifier);
    String getType();
    void setType(String type);
}
