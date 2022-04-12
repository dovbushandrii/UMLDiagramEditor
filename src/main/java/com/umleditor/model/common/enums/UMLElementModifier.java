package com.umleditor.model.common.enums;

/**
 * UML Attribute Modifiers
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
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
