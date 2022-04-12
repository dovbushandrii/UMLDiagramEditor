package com.umleditor.model.daos;

import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.model.daos.exceptions.DiagramFileReadException;
import com.umleditor.model.daos.exceptions.DiagramFileWriteException;

import java.io.*;

/**
 * Data Access Object for UMLDiagrams class.
 * Provides save and load functionality.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class DiagramDAO {
    public static UMLDiagram loadDiagram(File loadFrom) throws IOException {
        UMLDiagram result;
        try (FileInputStream fis = new FileInputStream(loadFrom);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (UMLDiagram) ois.readObject();
        }
        catch (Exception ex) {
            throw new DiagramFileReadException("File does not contain UML Diagram or it is broken");
        }
        return result;
    }

    public static void saveDiagram(UMLDiagram diagram, File saveTo) throws IOException {
        try {
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
