package com.umleditor.loaders.daos.interfaces;

import com.umleditor.common.interfaces.UMLDiagram;
import com.umleditor.loaders.daos.exceptions.DiagramFileReadException;

public interface DiagramDAO {

    UMLDiagram loadDiagram(String fileName) throws DiagramFileReadException;

    boolean saveDiagram(UMLDiagram diagram, String fileName);
}
