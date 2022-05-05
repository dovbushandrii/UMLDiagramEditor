package com.umleditor.model.sequencediagram;

import com.umleditor.model.UMLProject;
import com.umleditor.model.classdiagram.UMLRelation;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLElement;
import com.umleditor.model.common.interfaces.UMLDiagram;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UML Sequence Diagram class
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
@Data
public class UMLSequenceDiagram extends UMLElement implements UMLDiagram {

    private UMLProject parentProject = null;
    private List<UMLMessage> allMessages = new ArrayList<>();

    //Actors and Classes
    private List<UMLElement> allObjects = new ArrayList<>();

    @Override
    public List<UMLClass> getAllClasses() {
        return allObjects.stream()
                .filter(o -> o instanceof UMLClass)
                .map(o -> (UMLClass)o)
                .collect(Collectors.toList());
    }

    public void fullAddClass(UMLClass newClass) {
        parentProject.addNewClass(newClass);
        allObjects.add(newClass);
    }

    public void addObject(UMLElement element) {
        allObjects.add(element);
    }

    public void addMessage(UMLMessage message) {
        allMessages.add(message);
    }

    public void fullDeleteClass(UMLClass toDelete) {
        parentProject.deleteClass(toDelete);
        deleteObject(toDelete);
    }

    public void deleteClass(UMLClass toDelete) {
        deleteObject(toDelete);
    }

    public void deleteObject(UMLElement element) {
        this.allObjects.remove(element);
        deleteMessagesWithObject(element);
    }

    public void deleteMessage(UMLMessage message) {
        this.allMessages.remove(message);
    }

    public void deleteMessagesWithObject(UMLElement deleted) {
        this.allMessages = this.allMessages.stream()
                .filter(m ->
                        !(      //<==== NOT
                                m.getTo().getName().equals(deleted.getName()) ||
                                        m.getFrom().getName().equals(deleted.getName())
                        )
                ).collect(Collectors.toList());
    }

    public boolean classNameExits(String name) {
        return parentProject.classNameExists(name);
    }

    public boolean objectNameExits(String name) {
        return parentProject.classNameExists(name) || actorNameExists(name);
    }

    private boolean actorNameExists(String name) {
        Optional<UMLElement> found = this.allObjects.stream()
                .filter(o -> o instanceof UMLActor)
                .filter(a -> a.getName().equals(name))
                .findAny();
        return found.isPresent();
    }
}
