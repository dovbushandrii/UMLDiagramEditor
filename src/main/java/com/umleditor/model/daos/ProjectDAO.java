package com.umleditor.model.daos;

import com.umleditor.model.UMLProject;
import com.umleditor.model.common.exceptions.UMLException;
import com.umleditor.model.daos.exceptions.DiagramFileReadException;
import com.umleditor.model.daos.exceptions.DiagramFileWriteException;
import com.umleditor.model.daos.json.JsonToProjectConverter;
import com.umleditor.model.daos.json.ProjectToJsonConverter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Data Access Object for UML Project.
 * Provides save and load functionality.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class ProjectDAO {
    public static UMLProject loadProject(File loadFrom) throws IOException {
        try {
            String json = Files.readString(loadFrom.toPath());
            return JsonToProjectConverter.jsonToProject(json);
        } catch (UMLException umlEx) {
            throw new DiagramFileReadException(umlEx.getMessage());
        } catch (Exception ex) {
            throw new DiagramFileReadException("File does not contain UML Project or it is broken");
        }
    }

    public static void saveProject(UMLProject project, File saveTo) throws IOException {
        try {
            String json = ProjectToJsonConverter.projectToJson(project);
            Files.writeString(saveTo.toPath(), json);
        } catch (IOException ex) {
            throw new DiagramFileWriteException(ex.getMessage());
        }
    }
}
