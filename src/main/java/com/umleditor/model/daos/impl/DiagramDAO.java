package com.umleditor.model.daos.impl;

import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.model.daos.exceptions.DiagramFileReadException;
import com.umleditor.model.daos.exceptions.DiagramFileWriteException;

import java.io.*;

public class DiagramDAO {
    public static UMLDiagram loadDiagram(File loadFrom) {
        UMLDiagram result;
        try (FileInputStream fis = new FileInputStream(loadFrom);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (UMLDiagram) ois.readObject();
        }
        catch (ClassCastException ex) {
            throw new DiagramFileReadException("File does not contain UML Diagram or it is broken");
        }
        catch (Exception ex) {
            throw new DiagramFileReadException(ex.getMessage());
        }
        return result;
    }

    public static void saveDiagram(UMLDiagram diagram, File saveTo) {
        try {
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(saveTo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(diagram);

            oos.close();
            fos.close();
        } catch (IOException ex) {
            throw new DiagramFileWriteException(ex.getMessage());
        }
    }
}
