package com.umleditor.model.common.enums;

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
