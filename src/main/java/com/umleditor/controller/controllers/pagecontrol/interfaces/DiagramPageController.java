/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file DiagramPageController.java
 *
 * @brief Interface for Diagram Edit Page Controllers
 */
package com.umleditor.controller.controllers.pagecontrol.interfaces;

import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.view.pages.interfaces.DiagramEditSpace;

/**
 * Diagram Edit Page Controller Generalization.
 */
public interface DiagramPageController {
    void loadDiagram(UMLDiagram diagram);
    UMLDiagram getDiagram();
    /**
     * Sets Edit Space for diagrams.
     * After diagram load controller notifies
     * edit space to update.
     * @param editSpace diagram edit space builder and controller
     */
    void setEditSpace(DiagramEditSpace editSpace);
}
