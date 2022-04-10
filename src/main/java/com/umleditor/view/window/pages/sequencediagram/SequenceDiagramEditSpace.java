package com.umleditor.view.window.pages.sequencediagram;

import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.model.sequencediagram.UMLSequenceDiagram;
import com.umleditor.view.window.pages.interfaces.DiagramEditSpace;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class SequenceDiagramEditSpace implements DiagramEditSpace {

    private final Pane editSpace;

    public SequenceDiagramEditSpace(UMLDiagram diagram) {
        if (diagram == null || diagram instanceof UMLSequenceDiagram) {
            this.editSpace = constructNewEditSpace((UMLSequenceDiagram) diagram);
        } else {
            throw new IllegalArgumentException("UMLDiagram is not Class Diagram");
        }
    }

    @Override
    public Parent getEditSpace() {
        return this.editSpace;
    }

    @Override
    public void updateEditSpace(UMLDiagram diagram) {
        if (diagram == null || diagram instanceof UMLSequenceDiagram) {
            updateCurrentEditSpace((UMLSequenceDiagram) diagram);
        } else {
            throw new IllegalArgumentException("UMLDiagram is not Sequence Diagram");
        }
    }

    private Pane constructNewEditSpace(UMLSequenceDiagram diagram) {
        return null;
    }

    private void updateCurrentEditSpace(UMLSequenceDiagram diagram) {

    }
}

