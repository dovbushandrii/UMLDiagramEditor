package com.umleditor.view.filemanager;

import com.umleditor.model.UMLProject;
import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.model.daos.ProjectDAO;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

/**
 * UI basic FileChooser implementation
 * for UML Diagrams. Constructs basic file chooser/saver window.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class FileManager {

    private static FileChooser initializeOpenChooser() {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("UML file (*.uml)", "*.uml");
        chooser.getExtensionFilters().add(ext1);
        chooser.setTitle("Open UML Project");
        return chooser;
    }

    private static FileChooser initializeSaveChooser() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save UML Project");
        chooser.setInitialFileName("my_project.uml");
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("UML file (*.uml)", "*.uml");
        chooser.getExtensionFilters().add(ext1);
        return chooser;
    }

    public static UMLProject openProjectFile() throws IOException {
        File selectedFile = initializeOpenChooser().showOpenDialog(null);
        if (selectedFile != null) {
            return ProjectDAO.loadProject(selectedFile);
        }
        return null;
    }

    public static void saveProjectFile(UMLProject project) throws IOException {
        File savedFile = initializeSaveChooser().showSaveDialog(null);
        if (savedFile != null) {
            ProjectDAO.saveProject(project, savedFile);
        }
    }
}
