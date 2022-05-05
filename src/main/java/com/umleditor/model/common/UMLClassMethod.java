package com.umleditor.model.common;

import com.umleditor.model.common.enums.UMLElementModifier;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * UML Class method data type
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
@Data
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
}
