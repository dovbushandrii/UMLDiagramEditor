package com.umleditor.classdiagram.impl;

import com.umleditor.classdiagram.enums.UMLCDRelationType;
import com.umleditor.classdiagram.interfaces.UMLCDRelation;
import com.umleditor.common.impl.UMLElementImpl;
import com.umleditor.common.interfaces.UMLClass;

public class UMLCDRelationImpl extends UMLElementImpl implements UMLCDRelation {

    private UMLClass from;
    private UMLClass to;
    private UMLCDRelationType type;

    public UMLCDRelationImpl(UMLClass from, UMLClass to, UMLCDRelationType type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    @Override
    public UMLCDRelationType getType() {
        return this.type;
    }

    @Override
    public void setType(UMLCDRelationType type) {
        this.type = type;
    }

    @Override
    public UMLClass getFrom() {
        return this.from;
    }

    @Override
    public void setFrom(UMLClass from) {
        this.from = from;
    }

    @Override
    public UMLClass getTo() {
        return this.to;
    }

    @Override
    public void setTo(UMLClass to) {
        this.to = to;
    }
}
