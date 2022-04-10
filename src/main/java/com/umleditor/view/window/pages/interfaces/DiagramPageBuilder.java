package com.umleditor.view.window.pages.interfaces;

import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import javafx.scene.Parent;

public interface DiagramPageBuilder {
    Parent build(DiagramPageController controller);
}
