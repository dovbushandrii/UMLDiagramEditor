/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file UMLElement.java
 */
package com.umleditor.model.common;

import java.io.Serializable;

/**
 * UML Element is root parent for add UML diagram elements.
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
