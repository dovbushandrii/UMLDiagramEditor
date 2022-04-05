package com.umleditor.classdiagram;

import com.umleditor.common.UMLClass;
import com.umleditor.common.UMLElement;

public interface UMLCDRelationship extends UMLElement {
    UMLCDRelationshipType getType();
    // Connection: FROM Class -----> TO Class
    UMLClass getFrom();
    UMLClass getTo();
}
