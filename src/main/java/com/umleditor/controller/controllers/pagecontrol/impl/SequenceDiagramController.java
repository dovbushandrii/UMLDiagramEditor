package com.umleditor.controller.controllers.pagecontrol.impl;

import com.umleditor.controller.controllers.pagecontrol.annotations.PageController;
import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import com.umleditor.model.common.interfaces.UMLDiagram;
import javafx.scene.Parent;

@PageController
public class SequenceDiagramController implements DiagramPageController {

    private UMLDiagram loadedDiagram = null;

    @Override
    public void loadDiagram(UMLDiagram diagram) {
        this.loadedDiagram = diagram;
    }

    @Override
    public UMLDiagram getDiagram() {
        return loadedDiagram;
    }

    @Override
    public void setEditSpace(Parent parent) {

    }
}
