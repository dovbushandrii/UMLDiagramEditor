package com.umleditor.view.filemanager;

import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.model.daos.impl.DiagramDAO;
import javafx.stage.FileChooser;

import java.io.File;

public class FileManager {

    private static FileChooser initializeOpenChooser() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open UML Diagram");
        return chooser;
    }

    private static FileChooser initializeSaveChooser() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save UML Diagram");
        chooser.setInitialFileName("mydiagram.bat");
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("BAT file (*.bat)", "*.bat");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("UML file (*.uml)", "*.uml");
        chooser.getExtensionFilters().add(ext1);
        chooser.getExtensionFilters().add(ext2);
        return chooser;
    }

    public static UMLDiagram openDiagramFile() {
        File selectedFile = initializeOpenChooser().showOpenDialog(null);
        if(selectedFile != null) {
            return DiagramDAO.loadDiagram(selectedFile);
        }
        return null;
    }

    public static void saveDiagramFile(UMLDiagram diagram) {
        File savedFile = initializeSaveChooser().showSaveDialog(null);
        if(savedFile  != null) {
            DiagramDAO.saveDiagram(diagram, savedFile);
        }
    }
}
