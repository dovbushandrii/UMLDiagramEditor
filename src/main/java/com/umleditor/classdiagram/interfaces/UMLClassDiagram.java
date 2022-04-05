package com.umleditor.classdiagram.interfaces;

import com.umleditor.classdiagram.enums.UMLCDRelationType;
import com.umleditor.common.interfaces.UMLClass;
import com.umleditor.common.interfaces.UMLDiagram;

import java.util.List;

public interface UMLClassDiagram extends UMLDiagram {
    List<UMLClass> getClasses();
    List<UMLCDRelation> getRelations();

    void addClass(UMLClass newClass);
    void updateClass(UMLClass newClass);
    void addRelation(UMLClass from, UMLClass to, UMLCDRelationType type);

    // TODO: chose from this:
    void deleteClass(UMLClass toDelete);
    void deleteRelation(UMLClass from, UMLClass to);
    // TODO: and this:
    void deleteClass(String className);
    void deleteRelation(String from, String to);
}
