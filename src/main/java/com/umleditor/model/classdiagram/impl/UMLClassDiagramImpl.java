package com.umleditor.model.classdiagram.impl;

import com.umleditor.model.classdiagram.enums.UMLCDRelationType;
import com.umleditor.model.classdiagram.exceptions.ClassIsAlreadyDefinedOnDiagramException;
import com.umleditor.model.classdiagram.exceptions.ClassIsNotPresentOnDiagramException;
import com.umleditor.model.classdiagram.exceptions.RelationCanBeDefinedOnlyOnceException;
import com.umleditor.model.classdiagram.interfaces.UMLCDRelation;
import com.umleditor.model.classdiagram.interfaces.UMLClassDiagram;
import com.umleditor.model.common.interfaces.UMLClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UMLClassDiagramImpl implements UMLClassDiagram {

    private String name;
    private List<UMLClass> allClasses = new ArrayList<>();
    private List<UMLCDRelation> allRelations = new ArrayList<>();

    @Override
    public List<UMLClass> getClasses() {
        return this.allClasses;
    }

    @Override
    public List<UMLCDRelation> getRelations() {
        return this.allRelations;
    }

    @Override
    public void addClass(UMLClass newClass) {
        Optional<UMLClass> found = this.allClasses
                .stream()
                .filter(clazz -> clazz.getName().equals(newClass.getName()))
                .findAny();
        if (found.isPresent()) {
            throw new ClassIsAlreadyDefinedOnDiagramException("Class: " + newClass.getName() + "is already defined in diagram");
        } else {
            this.allClasses.add(newClass);
        }
    }

    @Override
    public void updateClass(UMLClass newClass) {
        Optional<UMLClass> found = this.allClasses
                .stream()
                .filter(clazz -> clazz.getName().equals(newClass.getName()))
                .findAny();
        if (found.isPresent()) {
            Integer index = this.allClasses.indexOf(found.get());
            this.allClasses.set(index, newClass);
        } else {
            throw new ClassIsNotPresentOnDiagramException("Class: " + newClass.getName() + "is not defined on diagram");
        }
    }

    @Override
    public void addRelation(UMLClass from, UMLClass to, UMLCDRelationType type) {
        Optional<UMLCDRelation> found = this.allRelations
                .stream()
                .filter(r ->
                        (
                                r.getTo().getName().equals(to.getName()) &&
                                r.getFrom().getName().equals(from.getName())
                        ) || (
                                r.getTo().getName().equals(from.getName()) &&
                                r.getFrom().getName().equals(to.getName())
                        )
                )
                .findAny();
        if (found.isPresent()) {
            throw new RelationCanBeDefinedOnlyOnceException("Relation was already defined for these classes");
        } else {
            UMLCDRelation newRelation = new UMLCDRelationImpl(from, to, type);
            this.allRelations.add(newRelation);
        }
    }

    @Override
    public void deleteClass(UMLClass toDelete) {
        this.allClasses.remove(toDelete);
    }

    @Override
    public void deleteRelation(UMLClass from, UMLClass to) {
        this.allRelations = this.allRelations.stream()
                .filter(r ->
                        !(      //<==== NOT
                                r.getTo().getName().equals(to.getName()) &&
                                r.getFrom().getName().equals(from.getName())
                        )
                ).collect(Collectors.toList());
    }

    @Override
    public void deleteClass(String className) {
        this.allClasses = this.allClasses.stream()
                .filter(c ->!c.getName().equals(className))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRelation(String from, String to) {
        this.allRelations = this.allRelations.stream()
                .filter(r ->
                        !(      //<==== NOT
                                r.getTo().getName().equals(to) &&
                                r.getFrom().getName().equals(from)
                        )
                ).collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
