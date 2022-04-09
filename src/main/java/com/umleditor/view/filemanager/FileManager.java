package com.umleditor.view.filemanager;

import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.model.daos.DiagramDAO;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static FileChooser initializeOpenChooser() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open UML Diagram");
        return chooser;
    }

    private static FileChooser initializeSaveChooser() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save UML Diagram");
        chooser.setInitialFileName("my_diagram.uml");
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("UML file (*.uml)", "*.uml");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("BAT file (*.bat)", "*.bat");
        chooser.getExtensionFilters().add(ext1);
        chooser.getExtensionFilters().add(ext2);
        return chooser;
    }

    public static UMLDiagram openDiagramFile() throws IOException {
        File selectedFile = initializeOpenChooser().showOpenDialog(null);
        if (selectedFile != null) {
            return DiagramDAO.loadDiagram(selectedFile);
        }
        return null;
    }

    public static void saveDiagramFile(UMLDiagram diagram) throws IOException {
        File savedFile = initializeSaveChooser().showSaveDialog(null);
        if (savedFile != null) {
            DiagramDAO.saveDiagram(diagram, savedFile);
        }
    }
}
