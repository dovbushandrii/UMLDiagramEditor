package com.umleditor.model.common;

/**
 * UML Element is root parent for add UML diagram elements.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class UMLElement {
    protected String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
