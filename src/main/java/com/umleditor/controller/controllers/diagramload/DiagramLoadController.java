package com.umleditor.controller.controllers.diagramload;

import com.umleditor.controller.controllers.context.AppContext;
import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.view.errorwindow.ErrorWindow;
import com.umleditor.view.filemanager.FileManager;

import java.io.IOException;

public class DiagramLoadController {
    public static void loadDiagram() {
        try {
            UMLDiagram diagram = FileManager.openDiagramFile();
            if (diagram != null) {
                // Get mapped to diagram page from context
                AppPage diagramPage = AppContext.getPageByDiagram(diagram.getClass());
                // Get diagram page controller from context
                DiagramPageController controller = (DiagramPageController) AppContext.getPageController(diagramPage);

                controller.loadDiagram(diagram);
                MainWindow.setPage(diagramPage);
            }
        }
        catch (IOException ex){
            ErrorWindow.showError("File open fail: " + ex.getMessage());
        }
        catch (ClassCastException ex) {
            ErrorWindow.showError("App initialization fail: wrong page mapping");
        }

    }

    public static void saveDiagram(UMLDiagram diagram) {
        try{
            FileManager.saveDiagramFile(diagram);
        }
        catch (IOException ex) {
            ErrorWindow.showError("File save fail: " + ex.getMessage());
        }
    }
}
