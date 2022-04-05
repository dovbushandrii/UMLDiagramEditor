package com.umleditor.classdiagram.impl;

import com.umleditor.classdiagram.enums.UMLCDRelationType;
import com.umleditor.classdiagram.exceptions.ClassIsAlreadyDefinedOnDiagramException;
import com.umleditor.classdiagram.exceptions.ClassIsNotPresentOnDiagramException;
import com.umleditor.classdiagram.interfaces.UMLCDRelation;
import com.umleditor.classdiagram.interfaces.UMLClassDiagram;
import com.umleditor.common.interfaces.UMLClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UMLClassDiagramImpl implements UMLClassDiagram {

    List<UMLClass> allClasses = new ArrayList<>();
    List<UMLCDRelation> relations = new ArrayList<>();

    @Override
    public List<UMLClass> getClasses() {
        return this.allClasses;
    }

    @Override
    public List<UMLCDRelation> getRelationships() {
        return this.relations;
    }

    @Override
    public void addClass(UMLClass newClass) {
        Optional<UMLClass> found = this.allClasses
                .stream()
                .filter(clazz -> clazz.getName().equals(newClass.getName()))
                .findAny();
        if(found.isPresent()) {
            throw new ClassIsAlreadyDefinedOnDiagramException("Class: " + newClass.getName() + "is already defined in diagram");
        }
        else {
            this.allClasses.add(newClass);
        }
    }

    @Override
    public void updateClass(UMLClass newClass) {
        Optional<UMLClass> found = this.allClasses
                .stream()
                .filter(clazz -> clazz.getName().equals(newClass.getName()))
                .findAny();
        if(found.isPresent()) {
            Integer index = this.allClasses.indexOf(found.get());
            this.allClasses.set(index,newClass);
        }
        else {
            throw new ClassIsNotPresentOnDiagramException("Class: " + newClass.getName() + "is not defined on diagram");
        }
    }

    @Override
    public void addRelationship(UMLClass from, UMLClass to, UMLCDRelationType type) {

    }

    @Override
    public void deleteClass(UMLClass toDelete) {

    }

    @Override
    public void deleteRelationship(UMLClass from, UMLClass to) {

    }

    @Override
    public void deleteClass(String className) {

    }

    @Override
    public void deleteRelationship(String from, String to) {

    }
}
