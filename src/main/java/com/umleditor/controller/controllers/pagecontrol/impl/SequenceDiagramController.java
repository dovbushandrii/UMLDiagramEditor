package com.umleditor.controller.controllers.pagecontrol.impl;

import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.view.window.pages.interfaces.DiagramEditSpace;

public class SequenceDiagramController implements DiagramPageController {
    private UMLDiagram loadedDiagram = null;
    private DiagramEditSpace editSpace = null;

    @Override
    public void loadDiagram(UMLDiagram diagram) {
        this.loadedDiagram = diagram;
        if (editSpace != null) {
            editSpace.updateEditSpace(diagram);
        }
    }

    @Override
    public UMLDiagram getDiagram() {
        return loadedDiagram;
    }

    @Override
    public void setEditSpace(DiagramEditSpace editSpace) {
        this.editSpace = editSpace;
    }
}
