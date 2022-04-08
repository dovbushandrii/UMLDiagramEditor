package com.umleditor.model.classdiagram.interfaces;

import com.umleditor.model.classdiagram.enums.UMLCDRelationType;
import com.umleditor.model.common.interfaces.UMLClass;
import com.umleditor.model.common.interfaces.UMLElement;

public interface UMLCDRelation extends UMLElement {
    UMLCDRelationType getType();
    void setType(UMLCDRelationType type);
    // Connection: FROM Class -----> TO Class
    UMLClass getFrom();
    void setFrom(UMLClass from);
    UMLClass getTo();
    void setTo(UMLClass to);
}
