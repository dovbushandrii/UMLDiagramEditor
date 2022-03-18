package com.umlui.classdiagram;

import com.umlui.common.UMLClass;
import com.umlui.common.UMLElement;

public interface UMLCDRelationship extends UMLElement {
    UMLCDRelationshipType getType();
    // Connection: FROM Class -----> TO Class
    UMLClass getFrom();
    UMLClass getTo();
}
