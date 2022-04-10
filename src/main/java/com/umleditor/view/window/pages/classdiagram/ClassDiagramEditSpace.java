package com.umleditor.view.window.pages.classdiagram;

import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.view.window.pages.interfaces.DiagramEditSpace;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ClassDiagramEditSpace implements DiagramEditSpace {

    private final Pane editSpace;

    public ClassDiagramEditSpace(UMLDiagram diagram) {
        if(diagram == null || diagram instanceof UMLClassDiagram) {
            this.editSpace = constructNewEditSpace((UMLClassDiagram)diagram);
        }
        else {
            throw new IllegalArgumentException("UMLDiagram is not Class Diagram");
        }
    }

    @Override
    public Parent getEditSpace() {
        return this.editSpace;
    }

    @Override
    public void updateEditSpace(UMLDiagram diagram) {
        if(diagram == null || diagram instanceof UMLClassDiagram) {
            updateCurrentEditSpace((UMLClassDiagram)diagram);
        }
        else {
            throw new IllegalArgumentException("UMLDiagram is not Class Diagram");
        }
    }

    private Pane constructNewEditSpace(UMLClassDiagram diagram) {
        VBox root = new VBox();
        if(diagram != null) {
            List<UMLClass> classes = diagram.getClasses();
            classes.forEach(c -> root.getChildren().add(new Button(c.getName())));
        }
        return root;
    }

    private void updateCurrentEditSpace(UMLClassDiagram diagram) {
        if(diagram != null) {
            this.editSpace.getChildren().removeAll();
            List<UMLClass> classes = diagram.getClasses();
            classes.forEach(c -> this.editSpace.getChildren().add(new Button(c.getName())));
        }
    }
}
