package com.umleditor.model.common.interfaces;

import com.umleditor.model.common.UMLClass;

import java.util.List;

/**
 * Interface-Marker to define UML Diagrams.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public interface UMLDiagram {
    List<UMLClass> getAllClasses();
}
