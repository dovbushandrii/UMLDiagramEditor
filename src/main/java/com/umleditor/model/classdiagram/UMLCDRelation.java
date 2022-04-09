package com.umleditor.model.classdiagram;

import com.umleditor.model.classdiagram.enums.UMLCDRelationType;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLElement;

public class UMLCDRelation extends UMLElement {

    private UMLClass from;
    private UMLClass to;
    private UMLCDRelationType type;

    public UMLCDRelation(UMLClass from, UMLClass to, UMLCDRelationType type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    public UMLCDRelationType getType() {
        return this.type;
    }

    public void setType(UMLCDRelationType type) {
        this.type = type;
    }

    public UMLClass getFrom() {
        return this.from;
    }

    public void setFrom(UMLClass from) {
        this.from = from;
    }

    public UMLClass getTo() {
        return this.to;
    }

    public void setTo(UMLClass to) {
        this.to = to;
    }
}
