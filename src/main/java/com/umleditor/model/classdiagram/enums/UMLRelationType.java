package com.umleditor.model.classdiagram.enums;

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
}
