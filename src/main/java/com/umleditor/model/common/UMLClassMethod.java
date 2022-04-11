/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file UMLClassMethod.java
 */
package com.umleditor.model.common;

import com.umleditor.model.common.enums.UMLElementModifier;

import java.util.ArrayList;
import java.util.List;

/**
 * UML Class method data type
 */
public class UMLClassMethod extends UMLClassAttribute {

    private List<String> argumentTypes = new ArrayList<>();

    public UMLClassMethod() {}

    public UMLClassMethod(String name,
                          List<String> argTypes,
                          UMLElementModifier modifier,
                          String type) {
        super.setName(name);
        setArgumentTypes(argTypes);
        super.setModifier(modifier);
        setType(type);
    }

    public List<String> getArgumentTypes() {
        return this.argumentTypes;
    }

    public void setArgumentTypes(List<String> argumentTypes) {
        if (argumentTypes != null) {
            this.argumentTypes = argumentTypes;
        }
    }

    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}
