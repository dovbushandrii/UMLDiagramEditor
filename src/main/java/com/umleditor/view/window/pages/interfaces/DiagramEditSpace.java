package com.umleditor.view.window.pages.interfaces;

import com.umleditor.model.common.interfaces.UMLDiagram;
import javafx.scene.layout.Pane;

public interface DiagramEditSpace {
    void updateEditSpace(UMLDiagram diagram);

    Pane getEditSpace();
}
