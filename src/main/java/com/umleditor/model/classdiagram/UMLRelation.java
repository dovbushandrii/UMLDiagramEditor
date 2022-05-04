package com.umleditor.model.classdiagram;

import com.umleditor.model.classdiagram.enums.UMLRelationType;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLElement;

/**
 * UML Class Diagram Relation.
 * Connects 2 UML classes, based on Relation Type
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class UMLRelation extends UMLElement {

    private UMLClass from;
    private UMLClass to;
    private UMLRelationType type = UMLRelationType.ASSOCIATION;

    public UMLRelation() {
    }

    public UMLRelation(UMLClass from, UMLClass to, UMLRelationType type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    public UMLRelationType getType() {
        return this.type;
    }

    public void setType(UMLRelationType type) {
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
