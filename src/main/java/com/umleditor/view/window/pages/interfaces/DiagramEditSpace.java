package com.umleditor.view.window.pages.interfaces;

import com.umleditor.model.common.interfaces.UMLDiagram;
import javafx.scene.Parent;

public interface DiagramEditSpace {
    void updateEditSpace(UMLDiagram diagram);
    Parent getEditSpace();
}
