package com.umleditor.classdiagram.interfaces;

import com.umleditor.classdiagram.enums.UMLCDRelationType;
import com.umleditor.common.interfaces.UMLClass;
import com.umleditor.common.interfaces.UMLElement;

public interface UMLCDRelation extends UMLElement {
    UMLCDRelationType getType();
    void setType(UMLCDRelationType type);
    // Connection: FROM Class -----> TO Class
    UMLClass getFrom();
    void setFrom(UMLClass from);
    UMLClass getTo();
    void setTo(UMLClass to);
}
