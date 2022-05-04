package com.umleditor.controller.controllers.diagramload;

import com.umleditor.context.AppContext;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.UMLProject;
import com.umleditor.view.errorwindow.ErrorWindow;
import com.umleditor.view.filemanager.FileManager;

import java.io.IOException;

/**
 * UI Diagram Load Controller.
 * Loads diagram from file with FileManager and loads it to connected edit page.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class ProjectLoadController {

    public static void loadProject() {
        try {
            UMLProject project = FileManager.openProjectFile();
            if (project != null) {
                AppContext.loadUMLProject(project);
                AppContext.refreshProjectDependentPages();
                MainWindow.setPage(AppPage.EDIT_PROJECT);
            }
        } catch (IOException ex) {
            ErrorWindow.showError("File open fail", ex.getMessage());
        }

    }

    public static void saveProject(UMLProject project) {
        try {
            FileManager.saveProjectFile(project);
        } catch (IOException ex) {
            ErrorWindow.showError("File save fail", ex.getMessage());
        }
    }
}
