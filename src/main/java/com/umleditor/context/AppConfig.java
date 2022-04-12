package com.umleditor.context;

import com.umleditor.controller.controllers.pagecontrol.impl.ClassDiagramController;
import com.umleditor.controller.controllers.pagecontrol.impl.SequenceDiagramController;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.sequencediagram.UMLSequenceDiagram;
import com.umleditor.view.pages.classdiagram.ClassDiagramPageBuilder;
import com.umleditor.view.pages.mainmenu.MainMenuPageBuilder;
import com.umleditor.view.pages.sequencediagram.SequenceDiagramPageBuilder;

/**
 * Application start config class
 * Has encoded start mappings and properties files.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class AppConfig {

    /**
     * Defines Page Name to (Diagram, Controller, Page Builder) context association
     * @throws ClassNotFoundException
     */
    private static void addDiagramPageAssociations() throws ClassNotFoundException {
        AppContext.addDiagramPageAssociation(AppPage.CLASS_DIAGRAM,
                UMLClassDiagram.class,
                ClassDiagramController.class,
                ClassDiagramPageBuilder.class);
        AppContext.addDiagramPageAssociation(AppPage.SEQUENCE_DIAGRAM,
                UMLSequenceDiagram.class,
                SequenceDiagramController.class,
                SequenceDiagramPageBuilder.class);
    }

    /**
     * Defines Page Name to Page Builder for 'primitive' pages context association
     * @throws ClassNotFoundException
     */
    private static void addPrimitivePageAssociations() throws ClassNotFoundException {
        AppContext.addPrimitivePageAssociation(AppPage.MAIN_MENU, MainMenuPageBuilder.class);
    }

    /**
     * Loads properties to application context
     * @throws Exception
     */
    private static void loadProperites() throws Exception {
        AppContext.loadPropertiesFrom("application.properties");
    }

    /**
     * Initializes application context, must be called before application start
     * @throws Exception
     */
    public static void initializeContext() throws Exception {
        addDiagramPageAssociations();
        addPrimitivePageAssociations();
        loadProperites();
    }
}
