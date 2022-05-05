package com.umleditor.model.common;

import com.umleditor.model.common.enums.UMLElementModifier;

import java.util.ArrayList;
import java.util.List;

/**
 * UML Class method data type
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class UMLClassMethod extends UMLClassAttribute {
    private List<String> argumentTypes = new ArrayList<>();

    public UMLClassMethod() {
        super();
    }

    public UMLClassMethod(String name, List<String> argumentTypes, UMLElementModifier modifier, String type) {
        this.name = name;
        this.argumentTypes = argumentTypes;
        this.modifier = modifier;
        this.type = type;
    }

    public List<String> getArgumentTypes() {
        return argumentTypes;
    }

    public void setArgumentTypes(List<String> argumentTypes) {
        this.argumentTypes = argumentTypes;
    }
}
