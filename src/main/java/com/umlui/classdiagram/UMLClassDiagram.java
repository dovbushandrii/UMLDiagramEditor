package com.umlui.classdiagram;

import com.umlui.common.UMLClass;

import java.util.List;

public interface UMLClassDiagram {
    List<UMLClass> getClasses();
    List<UMLCDRelationship> getRelationships();

    void addClass(UMLClass newClass);
    void addRelationship(UMLClass from, UMLClass to, UMLCDRelationshipType type);

    // TODO: chose from this:
    void deleteClass(UMLClass toDelete);
    void deleteRelationship(UMLClass from, UMLClass to);
    // TODO: and this:
    void deleteClass(String className);
    void deleteRelationship(String from, String to);
}
