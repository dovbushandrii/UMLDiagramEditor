package com.umleditor.model.classdiagram;

import com.umleditor.model.UMLProject;
import com.umleditor.model.classdiagram.enums.UMLRelationType;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLElement;
import com.umleditor.model.common.interfaces.UMLDiagram;
import lombok.Data;

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
@Data
public class UMLClassDiagram extends UMLElement implements UMLDiagram {

    private UMLProject parentProject = null;
    private List<UMLClass> allClasses = new ArrayList<>();
    private List<UMLRelation> allRelations = new ArrayList<>();

    public void fullAddClass(UMLClass newClass) {
        parentProject.addNewClass(newClass);
        allClasses.add(newClass);
    }

    public void addClass(UMLClass newClass) {
        allClasses.add(newClass);
    }

    public void fullDeleteClass(UMLClass toDelete) {
        parentProject.deleteClass(toDelete);
    }

    public void deleteClass(UMLClass toDelete) {
        allClasses = allClasses.stream()
                .filter(c -> !c.getName().equals(toDelete.getName()))
                .collect(Collectors.toList());
        deleteRelationWithClass(toDelete);
    }

    public void addRelation(UMLRelation relation) {
        allRelations.add(relation);
    }

    public void addRelation(UMLClass from, UMLClass to, UMLRelationType type) {
        UMLRelation newbie = new UMLRelation(from,to,type);
        allRelations.add(newbie);
    }

    public void deleteRelation(UMLRelation relation) {
        allRelations = allRelations.stream()
                .filter(r -> r.getTo().getName().equals(relation.getTo().getName())
                        && r.getFrom().getName().equals(relation.getFrom().getName())
                        && r.getType().equals(relation.getType()))
                .collect(Collectors.toList());
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
