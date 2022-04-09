package com.umleditor.controller.controllers.pagecontrol.interfaces;

import com.umleditor.model.common.interfaces.UMLDiagram;
import javafx.scene.Parent;

public interface DiagramPageController {
    void loadDiagram(UMLDiagram diagram);
    UMLDiagram getDiagram();
    void setEditSpace(Parent parent);
}
