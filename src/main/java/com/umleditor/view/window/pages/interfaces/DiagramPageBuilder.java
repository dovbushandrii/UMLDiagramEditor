package com.umleditor.view.window.pages.interfaces;

import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import javafx.scene.layout.Pane;

public interface DiagramPageBuilder {
    Pane build(DiagramPageController controller);
}
