package com.umleditor.model.common.enums;

import java.util.NoSuchElementException;

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

    public static UMLElementModifier getModifierBySymbol(String symbol) {
        for (UMLElementModifier value : values()) {
            if(value.getModifierSymbol().equals(symbol)) {
                return value;
            }
        }
        throw new NoSuchElementException();
    }
}
