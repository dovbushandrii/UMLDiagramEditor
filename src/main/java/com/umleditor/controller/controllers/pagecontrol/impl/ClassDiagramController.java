package com.umleditor.controller.controllers.pagecontrol.impl;

import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.view.pages.interfaces.DiagramEditSpace;

/**
 * Implementation of DiagramPageController for Class Diagrams
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class ClassDiagramController implements DiagramPageController {

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
