/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file DiagramPageBuilder.java
 */
package com.umleditor.view.pages.interfaces;

import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import javafx.scene.layout.Pane;

/**
 * Diagram Page is not 'primitive' - needs controller,
 * that can load diagrams
 */
public interface DiagramPageBuilder {
    Pane build(DiagramPageController controller);
}
