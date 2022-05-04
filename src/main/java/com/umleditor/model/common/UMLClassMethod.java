package com.umleditor.model.common;

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
}
