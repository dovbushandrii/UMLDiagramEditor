package com.umleditor.controller.controllers.pagecontrol.interfaces;

import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.view.window.pages.interfaces.DiagramEditSpace;

public interface DiagramPageController {
    void loadDiagram(UMLDiagram diagram);
    UMLDiagram getDiagram();
    void setEditSpace(DiagramEditSpace editSpace);
}
