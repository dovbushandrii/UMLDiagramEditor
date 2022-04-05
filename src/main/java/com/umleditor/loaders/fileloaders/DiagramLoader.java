package com.umleditor.loaders.fileloaders;

import com.umleditor.common.interfaces.UMLDiagram;
import com.umleditor.loaders.daos.interfaces.DiagramDAO;

import java.util.HashMap;
import java.util.Map;

public class DiagramLoader {

    Map<Class<? extends UMLDiagram>, DiagramDAO> daos = new HashMap<>();

    public DiagramLoader() {
    }

    public DiagramLoader(Map<Class<? extends UMLDiagram>, DiagramDAO> daos) {
        this.daos = daos;
    }

    public void addDiagramDAO(Class<? extends UMLDiagram> clazz, DiagramDAO dao) {
        this.daos.put(clazz, dao);
    }

    public <T extends UMLDiagram> T readDiagram(String fileName, Class<T> a) {
        DiagramDAO dao = daos.get(a);
        return (T)dao.loadDiagram(fileName);
    }

    public boolean saveDiagram(UMLDiagram diagram, String fileName) {
        DiagramDAO dao = daos.get(diagram.getClass());
        return dao.saveDiagram(diagram,fileName);
    }
}
