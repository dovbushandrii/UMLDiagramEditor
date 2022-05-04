package com.umleditor.model.sequencediagram;

import com.umleditor.model.UMLProject;
import com.umleditor.model.classdiagram.UMLRelation;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLElement;
import com.umleditor.model.common.interfaces.UMLDiagram;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UML Sequence Diagram data type implementation
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
@Data
public class UMLSequenceDiagram extends UMLElement implements UMLDiagram {

    private UMLProject parentProject = null;
    private List<UMLClass> allClasses = new ArrayList<>();
    private List<UMLMessage> allMessages = new ArrayList<>();

    public void addClass(UMLClass newClass) {
    }

    public void addRelation(UMLRelation relation) {
    }

    public void deleteClass(UMLClass toDelete) {
    }

    public void deleteMessage(UMLMessage relation) {
    }

    public void deleteMessagesWithClass(UMLClass deleted) {
        this.allMessages = this.allMessages.stream()
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

    public boolean messageExists(UMLClass from, UMLClass to) {
        return false;
    }
}
