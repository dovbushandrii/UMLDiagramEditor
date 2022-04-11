/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file DiagramEditSpace.java
 */
package com.umleditor.view.pages.interfaces;

import com.umleditor.model.common.interfaces.UMLDiagram;
import javafx.scene.layout.Pane;

public interface DiagramEditSpace {
    void updateEditSpace(UMLDiagram diagram);
    Pane getEditSpace();
}
