package com.umleditor.model.common;

import java.io.Serializable;

/**
 * UML Element is root parent for add UML diagram elements.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class UMLElement implements Serializable {

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
