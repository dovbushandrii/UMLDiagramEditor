package com.umleditor.model.classdiagram;

import com.umleditor.model.UMLProject;
import com.umleditor.model.classdiagram.enums.UMLRelationType;
import com.umleditor.model.classdiagram.exceptions.ClassIsAlreadyDefinedException;
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
public class UMLClassDiagram extends UMLElement implements UMLDiagram {

    private UMLProject parentProject = null;
    private List<UMLClass> allClasses = new ArrayList<>();
    private List<UMLRelation> allRelations = new ArrayList<>();

    public UMLProject getParentProject() {
        return parentProject;
    }

    public void setParentProject(UMLProject parentProject) {
        this.parentProject = parentProject;
    }

    @Override
    public List<UMLClass> getAllClasses() {
        return allClasses;
    }

    public void setAllClasses(List<UMLClass> allClasses) {
        this.allClasses = allClasses;
    }

    public List<UMLRelation> getAllRelations() {
        return allRelations;
    }

    public void setAllRelations(List<UMLRelation> allRelations) {
        this.allRelations = allRelations;
    }

    public void fullAddClass(UMLClass newClass) {
        parentProject.addNewClass(newClass);
        allClasses.add(newClass);
    }

    public void addClass(UMLClass newClass) {
        if(!classPresentOnDiagram(newClass)) {
            allClasses.add(newClass);
        }
        else {
            throw new ClassIsAlreadyDefinedException("Class cannot be added twice");
        }
    }

    public void fullDeleteClass(UMLClass toDelete) {
        parentProject.deleteClass(toDelete);
    }

    public void deleteClass(UMLClass toDelete) {
        allClasses.remove(toDelete);
        deleteRelationWithClass(toDelete);
    }

    public void addRelation(UMLRelation relation) {
        allRelations.add(relation);
    }

    public void addRelation(UMLClass from, UMLClass to, UMLRelationType type) {
        if(relationExists(from,to)) {
            throw new RelationCanBeDefinedOnlyOnceException("Relation is already defined");
        }
        else {
            if(classPresentOnDiagram(from) && classPresentOnDiagram(to)){
                UMLRelation newbie = new UMLRelation(from,to,type);
                allRelations.add(newbie);
            }
            else {
                throw new ClassIsNotPresentOnDiagramException("Class from relation is not present on diagram");
            }
        }

    }

    public void deleteRelation(UMLRelation relation) {
        allRelations.remove(relation);
    }

    private void deleteRelationWithClass(UMLClass deleted) {
        this.allRelations = this.allRelations.stream()
                .filter(r ->
                        !(      //<==== NOT
                                r.getTo().getName().equals(deleted.getName()) ||
                                        r.getFrom().getName().equals(deleted.getName())
                        )
                ).collect(Collectors.toList());
    }

    public boolean classNameExits(String name) {
        return parentProject.classNameExists(name);
    }

    public boolean classPresentOnDiagram(UMLClass clazz) {
        Optional<UMLClass> found = allClasses.stream()
                .filter(c -> c.getName().equals(clazz.getName()))
                .findAny();
        return found.isPresent();
    }

    public boolean relationExists(UMLClass from, UMLClass to) {
        if (from == null || to == null) {
            return true;
        }
        Optional<UMLRelation> found = this.allRelations
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
        return found.isPresent();
    }
}
