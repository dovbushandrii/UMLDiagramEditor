package com.umleditor.model;

import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.classdiagram.exceptions.ClassIsAlreadyDefinedException;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.sequencediagram.UMLSequenceDiagram;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UMLProject {

    private String name = "";
    private List<UMLClass> allClasses = new ArrayList<>();
    private List<UMLClassDiagram> classDiagrams = new ArrayList<>();
    private List<UMLSequenceDiagram> sequenceDiagrams = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<UMLClass> getAllClasses() {
        return allClasses;
    }

    public List<UMLClassDiagram> getClassDiagrams() {
        return classDiagrams;
    }

    public List<UMLSequenceDiagram> getSequenceDiagrams() {
        return sequenceDiagrams;
    }

    public void addNewClass(UMLClass umlClass) {
        if(!classNameExists(umlClass.getName())) {
            allClasses.add(umlClass);
        }
        else {
            throw new ClassIsAlreadyDefinedException("Class with this name already exists");
        }
    }

    public void deleteClass(UMLClass umlClass) {
        deleteClassInChildren(umlClass);
        allClasses.remove(umlClass);
    }

    private void deleteClassInChildren(UMLClass umlClass) {
        deleteClassInCDiagrams(umlClass);
        deleteClassInSDiagrams(umlClass);
    }

    private void deleteClassInCDiagrams(UMLClass umlClass) {
        classDiagrams.forEach(cd -> cd.deleteClass(umlClass));
    }

    private void deleteClassInSDiagrams(UMLClass umlClass) {
        sequenceDiagrams.forEach(sd -> sd.deleteClass(umlClass));
    }

    public boolean classNameExists(String name) {
        Optional<UMLClass> found = allClasses.stream()
                .filter(c -> c.getName().equals(name))
                .findAny();
        return found.isPresent();
    }

    public UMLClassDiagram createNewClassDiagram(String diagramName) {
        UMLClassDiagram newbie = new UMLClassDiagram();
        newbie.setName(diagramName);
        newbie.setParentProject(this);
        classDiagrams.add(newbie);
        return newbie;
    }

    public void deleteClassDiagram(UMLClassDiagram diagram) {
        classDiagrams.remove(diagram);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllClasses(List<UMLClass> allClasses) {
        this.allClasses = allClasses;
    }

    public void setClassDiagrams(List<UMLClassDiagram> classDiagrams) {
        this.classDiagrams = classDiagrams;
    }

    public void setSequenceDiagrams(List<UMLSequenceDiagram> sequenceDiagrams) {
        this.sequenceDiagrams = sequenceDiagrams;
    }

    public UMLSequenceDiagram createNewSequenceDiagram(String diagramName) {
        UMLSequenceDiagram newbie = new UMLSequenceDiagram();
        newbie.setName(diagramName);
        newbie.setParentProject(this);
        sequenceDiagrams.add(newbie);
        return newbie;
    }

    public void deleteSequenceDiagram(UMLSequenceDiagram diagram) {
        sequenceDiagrams.remove(diagram);
    }
}
