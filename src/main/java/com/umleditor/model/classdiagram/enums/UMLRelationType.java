package com.umleditor.model.classdiagram.enums;

import com.umleditor.model.common.enums.UMLElementModifier;

import java.util.NoSuchElementException;

/**
 * UML Class Diagram Relation Types
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public enum UMLRelationType {
    ASSOCIATION ("Association"),
    GENERALIZATION ("Generalization"),
    AGGREGATION("Aggregation"),
    COMPOSITION("Composition");

    private final String typeSymbol;

    UMLRelationType(String typeSymbol) {
        this.typeSymbol = typeSymbol;
    }

    public String getTypeSymbol() {
        return typeSymbol;
    }

    public static UMLRelationType getTypeBySymbol(String symbol) {
        for (UMLRelationType value : values()) {
            if(value.getTypeSymbol().equals(symbol)) {
                return value;
            }
        }
        throw new NoSuchElementException();
    }
}
