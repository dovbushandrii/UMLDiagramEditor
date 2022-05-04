package com.umleditor.view.pages.interfaces;

import com.umleditor.model.UMLProject;
import com.umleditor.model.common.interfaces.UMLDiagram;
import javafx.scene.layout.Pane;

/**
 * Diagram Edit Space is class that will be linked
 * with DiagramPageController. Contains final Pane elements
 * on which UML diagram will be shown.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public interface DiagramEditSpace {
    void updateEditSpace(UMLProject project);
    Pane getEditSpace();
}
