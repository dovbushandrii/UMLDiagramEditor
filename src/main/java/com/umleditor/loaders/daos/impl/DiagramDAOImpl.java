package com.umleditor.loaders.daos.impl;

import com.umleditor.common.interfaces.UMLDiagram;
import com.umleditor.loaders.daos.exceptions.DiagramFileReadException;
import com.umleditor.loaders.daos.exceptions.DiagramFileWriteException;
import com.umleditor.loaders.daos.interfaces.DiagramDAO;

import java.io.*;

public class DiagramDAOImpl implements DiagramDAO {
    @Override
    public UMLDiagram loadDiagram(String fileName) {
        UMLDiagram result;
        File file = new File(fileName + ".bat");
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // Read object from file
            result = (UMLDiagram) ois.readObject();
        } catch (Exception ex) {
            throw new DiagramFileReadException(ex.getMessage());
        }
        return result;
    }

    @Override
    public void saveDiagram(UMLDiagram diagram, String fileName) {
        try {
            File file = new File(fileName + ".bat");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(diagram);

            oos.close();
            fos.close();
        } catch (IOException ex) {
            throw new DiagramFileWriteException(ex.getMessage());
        }
    }
}
