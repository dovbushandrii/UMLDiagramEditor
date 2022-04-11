/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file UMLElementModifier.java
 */
package com.umleditor.model.common.enums;

/**
 * UML Attribute Modifiers
 */
public enum UMLElementModifier {
    PUBLIC("+"),
    PROTECTED("#"),
    PRIVATE("-"),
    DEFAULT("");

    private final String modifierSymbol;

    UMLElementModifier(String modifierSymbol) {
        this.modifierSymbol = modifierSymbol;
    }

    public String getModifierSymbol() {
        return modifierSymbol;
    }
}
