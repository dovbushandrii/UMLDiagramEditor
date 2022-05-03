package com.umleditor.model.classdiagram;

import com.umleditor.model.classdiagram.enums.UMLCDRelationType;
import com.umleditor.model.classdiagram.exceptions.ClassIsAlreadyDefinedOnDiagramException;
import com.umleditor.model.classdiagram.exceptions.ClassIsNotPresentOnDiagramException;
import com.umleditor.model.classdiagram.exceptions.RelationCanBeDefinedOnlyOnceException;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLElement;
import com.umleditor.model.common.interfaces.UMLDiagram;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class Diagram implementation.
 * Contains UMLClasses and UMLCDRelations between them.
 * Controls that class names are unique.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
//TODO: Connect with Sequence Diagrams by Project Class
public class UMLClassDiagram extends UMLElement implements UMLDiagram {

    private String name;
    private List<UMLClass> allClasses = new ArrayList<>();
    private List<UMLCDRelation> allRelations = new ArrayList<>();

    public List<UMLClass> getClasses() {
        return this.allClasses;
    }

    public List<UMLCDRelation> getRelations() {
        return this.allRelations;
    }

    /**
     * Adds class element to diagram.
     * If class with same name is defined on diagram, throws exception
     */
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

    /**
     * Updates class element on diagram.
     */
    public void updateClass(UMLClass newClass) {
        Optional<UMLClass> found = this.allClasses
                .stream()
                .filter(clazz -> clazz == newClass)
                .findAny();
        if (found.isPresent()) {
            int index = this.allClasses.indexOf(found.get());
            this.allClasses.set(index, newClass);
        } else {
            throw new ClassIsNotPresentOnDiagramException("Class: " + newClass.getName() + "is not defined on diagram");
        }
    }

    /**
     * Adds relation element to diagram.
     * If relation with same pair of classes is defined, throws exception
     */
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
            UMLCDRelation newRelation = new UMLCDRelation(from, to, type);
            this.allRelations.add(newRelation);
        }
    }

    public void deleteClass(UMLClass toDelete) {
        this.allClasses.remove(toDelete);
    }

    public void deleteRelation(UMLClass from, UMLClass to) {
        this.allRelations = this.allRelations.stream()
                .filter(r ->
                        !(      //<==== NOT
                                r.getTo().getName().equals(to.getName()) &&
                                r.getFrom().getName().equals(from.getName())
                        )
                ).collect(Collectors.toList());
    }

    public void deleteRelationWithClass(UMLClass deleted) {
        this.allRelations = this.allRelations.stream()
                .filter(r ->
                        !(      //<==== NOT
                                r.getTo().getName().equals(deleted.getName()) ||
                                        r.getFrom().getName().equals(deleted.getName())
                        )
                ).collect(Collectors.toList());
    }

    public void deleteClass(String className) {
        this.allClasses = this.allClasses.stream()
                .filter(c ->!c.getName().equals(className))
                .collect(Collectors.toList());
    }

    public void deleteRelation(String from, String to) {
        this.allRelations = this.allRelations.stream()
                .filter(r ->
                        !(      //<==== NOT
                                r.getTo().getName().equals(to) &&
                                r.getFrom().getName().equals(from)
                        )
                ).collect(Collectors.toList());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean classNameExits(String name) {
        Optional<UMLClass> found = this.allClasses
                .stream()
                .filter(clazz -> clazz.getName().equals(name))
                .findAny();
        return found.isPresent();
    }
}
