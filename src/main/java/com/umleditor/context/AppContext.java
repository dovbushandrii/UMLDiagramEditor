/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file AppContext.java
 */
package com.umleditor.context;

import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.UMLProject;
import com.umleditor.view.pages.interfaces.ProjectDependentPage;
import com.umleditor.view.pages.interfaces.ProjectIndependentPage;
import javafx.scene.layout.Pane;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Application Context Class.
 * Contains loaded properties and page name to builder map for primitive pages,
 * and page name to diagram,controller,builder association map.
 * Also contains UML Project.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class AppContext {

    private final static Map<AppPage, ProjectIndependentPage> nameToPrimitivePageMap = new HashMap<>();
    private final static Properties properties = new Properties();

    private static UMLProject project = null;

    public static void loadUMLProject(UMLProject umlproject) {
        project = umlproject;
    }

    public static UMLProject getUMLProject() {
        return project;
    }

    public static void addPageAssociation(AppPage pageName,
                                          Class<? extends ProjectIndependentPage> pageBuilder) throws ClassNotFoundException {
        try {
            nameToPrimitivePageMap.put(pageName, pageBuilder.getDeclaredConstructor().newInstance());
        } catch (Exception ex) {
            throw new ClassNotFoundException("AppContext initialization fail: " + ex.getMessage());
        }
    }

    public static Map<AppPage, Pane> getPages() {
        Map<AppPage, Pane> result = new HashMap<>();
        nameToPrimitivePageMap.forEach((p, d) -> result.put(p, d.build()));
        return result;
    }

    public static void refreshProjectDependentPages() {
        nameToPrimitivePageMap.forEach((n,p) -> {
            if(p instanceof ProjectDependentPage) {
                ProjectDependentPage toUpdate = (ProjectDependentPage)p;
                toUpdate.updateProject(project);
            }
        });
    }

    public static void loadPropertiesFrom(String fineName) throws Exception {
        // The class loader that loaded the class
        ClassLoader classLoader = AppContext.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fineName);

        //FileReader reader = new FileReader(fineName);
        properties.load(inputStream);
    }

    public static String getProperty(String propName) {
        return properties.getProperty(propName);
    }
}
