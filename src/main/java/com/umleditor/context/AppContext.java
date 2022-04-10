package com.umleditor.context;

import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.view.window.pages.interfaces.PrimitivePageBuilder;
import com.umleditor.view.window.pages.interfaces.DiagramPageBuilder;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AppContext {

    private static class DiagramAssociation {
        private final Class<? extends UMLDiagram> diagramClass;
        private final DiagramPageController controller;
        private final DiagramPageBuilder pageBuilder;

        private DiagramAssociation(Class<? extends UMLDiagram> diagramClass,
                                   Class<? extends DiagramPageController> controllerClass,
                                   Class<? extends DiagramPageBuilder> pageBuilderClass) throws ClassNotFoundException {
            this.diagramClass = diagramClass;
            try {
                this.controller= controllerClass.getDeclaredConstructor().newInstance();
                this.pageBuilder = pageBuilderClass.getDeclaredConstructor().newInstance();
            }
            catch (Exception ex) {
                throw new ClassNotFoundException("AppContext initialization fail: " + ex.getMessage());
            }
        }
    }

    private final static Map<AppPage, DiagramAssociation> diagramPageMap = new HashMap<>();
    private final static Map<AppPage, PrimitivePageBuilder> nameToPrimitivePageMap = new HashMap<>();
    private final static Properties properties = new Properties();

    public static void addDiagramPageAssociation(AppPage pageName,
                                                 Class<? extends UMLDiagram> diagramClass,
                                                 Class<? extends DiagramPageController> controllerClass,
                                                 Class<? extends DiagramPageBuilder> pageBuilderClass) throws ClassNotFoundException {
        DiagramAssociation association = new DiagramAssociation(diagramClass, controllerClass, pageBuilderClass);
        diagramPageMap.put(pageName, association);
    }

    public static void addPrimitivePageAssociation(AppPage pageName,
                                                   Class<? extends PrimitivePageBuilder> pageBuilder) throws ClassNotFoundException{
        try {
            nameToPrimitivePageMap.put(pageName, pageBuilder.getDeclaredConstructor().newInstance());
        }
        catch (Exception ex) {
            throw new ClassNotFoundException("AppContext initialization fail: " + ex.getMessage());
        }
    }

    public static Map<AppPage, Pane> getDiagramPages() {
        Map<AppPage, Pane> result = new HashMap<>();
        diagramPageMap.forEach((p, d) -> result.put(p, d.pageBuilder.build(d.controller)));
        return result;
    }

    public static Map<AppPage, Pane> getPrimitivePages() {
        Map<AppPage, Pane> result = new HashMap<>();
        nameToPrimitivePageMap.forEach((p, d) -> result.put(p, d.build()));
        return result;
    }

    public static DiagramPageController getControllerByDiagramClass(Class<? extends UMLDiagram> diagramClass) {
        return diagramPageMap.values().stream()
                .filter(da -> da.diagramClass == diagramClass)
                .map(da -> da.controller)
                .findFirst()
                .orElse(null);
    }

    public static AppPage getPageByDiagramClass(Class<? extends UMLDiagram> diagramClass) {
        return diagramPageMap.entrySet().stream()
                .filter(e -> e.getValue().diagramClass == diagramClass)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public static void loadPropertiesFrom(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        properties.load(reader);
    }

    public static String getProperty(String propName) {
        return properties.getProperty(propName);
    }
}
