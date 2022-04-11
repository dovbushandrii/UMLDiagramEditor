/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file SequenceDiagramEditSpace.java
 */
package com.umleditor.view.pages.sequencediagram;

import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.model.sequencediagram.UMLSequenceDiagram;
import com.umleditor.view.pages.interfaces.DiagramEditSpace;
import javafx.scene.layout.Pane;

/**
 * Class that control edit space for Class Diagram
 */
//TODO: Implement
public class SequenceDiagramEditSpace implements DiagramEditSpace {

    private final Pane editSpace;

    public SequenceDiagramEditSpace(UMLDiagram diagram) {
        if (diagram == null || diagram instanceof UMLSequenceDiagram) {
            this.editSpace = constructNewEditSpace();
            updateCurrentEditSpace((UMLSequenceDiagram) diagram);
        } else {
            throw new IllegalArgumentException("UMLDiagram is not Class Diagram");
        }
    }

    @Override
    public Pane getEditSpace() {
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

    private Pane constructNewEditSpace() {
        return null;
    }

    private void updateCurrentEditSpace(UMLSequenceDiagram diagram) {

    }
}

