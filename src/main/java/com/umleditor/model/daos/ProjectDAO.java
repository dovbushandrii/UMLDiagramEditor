package com.umleditor.model.daos;

import com.umleditor.model.UMLProject;
import com.umleditor.model.daos.exceptions.DiagramFileReadException;
import com.umleditor.model.daos.exceptions.DiagramFileWriteException;

import java.io.*;

/**
 * Data Access Object for UML Project.
 * Provides save and load functionality.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class ProjectDAO {
    public static UMLProject loadProject(File loadFrom) throws IOException {
        try (FileInputStream fis = new FileInputStream(loadFrom);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            //TODO: IMPLEMENT FILE LOAD
        }
        catch (Exception ex) {
            throw new DiagramFileReadException("File does not contain UML Project or it is broken");
        }
        return null;
    }

    public static void saveProject(UMLProject project, File saveTo) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(saveTo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //TODO: IMPLEMENT FILE SAVE
            oos.writeObject(project);

            oos.close();
            fos.close();
        } catch (IOException ex) {
            throw new DiagramFileWriteException(ex.getMessage());
        }
    }
}
