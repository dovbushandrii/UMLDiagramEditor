package com.umleditor.classdiagram.interfaces;

import com.umleditor.classdiagram.enums.UMLCDRelationType;
import com.umleditor.common.interfaces.UMLClass;
import com.umleditor.common.interfaces.UMLDiagram;

import java.util.List;

public interface UMLClassDiagram extends UMLDiagram {
    List<UMLClass> getClasses();
    List<UMLCDRelation> getRelationships();

    void addClass(UMLClass newClass);
    void updateClass(UMLClass newClass);
    void addRelationship(UMLClass from, UMLClass to, UMLCDRelationType type);

    // TODO: chose from this:
    void deleteClass(UMLClass toDelete);
    void deleteRelationship(UMLClass from, UMLClass to);
    // TODO: and this:
    void deleteClass(String className);
    void deleteRelationship(String from, String to);
}
