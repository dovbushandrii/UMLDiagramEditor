package com.umleditor.loaders.daos.impl;

import com.umleditor.classdiagram.interfaces.UMLClassDiagram;
import com.umleditor.common.interfaces.UMLDiagram;
import com.umleditor.loaders.daos.exceptions.DiagramFileReadException;
import com.umleditor.loaders.daos.exceptions.DiagramFileWriteException;
import com.umleditor.loaders.daos.interfaces.DiagramDAO;

import java.io.*;

public class ClassDiagramDAO implements DiagramDAO {
    @Override
    public UMLDiagram loadDiagram(String fileName) throws DiagramFileReadException {
        UMLDiagram result;
        File file = new File(fileName + ".bat");
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // Read object from file
            result = (UMLDiagram) ois.readObject();
        } catch (Exception ex) {
            throw new DiagramFileReadException(ex.getCause());
        }
        return result;
    }

    @Override
    public boolean saveDiagram(UMLDiagram diagram, String fileName) {
        if (diagram instanceof UMLClassDiagram) {
            try {
                File file = new File(fileName + ".bat");
                if (!file.exists()) {
                    file.createNewFile();
                }
                try (FileOutputStream fos = new FileOutputStream(file);
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    // Write object to file
                    oos.writeObject(diagram);
                }
                catch (Exception ex) {
                    throw ex;
                }
            }
            catch (IOException ex) {
                throw new DiagramFileWriteException(ex.getCause());
            }
            return true;
        } else {
            throw new IllegalArgumentException("Wrong UMLDiagram type was given instead of UMLClassDiagram: " + diagram.getClass().getName());
        }
    }
}
