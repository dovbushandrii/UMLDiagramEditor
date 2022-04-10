package com.umleditor.context;

import com.umleditor.controller.controllers.pagecontrol.impl.ClassDiagramController;
import com.umleditor.controller.controllers.pagecontrol.impl.SequenceDiagramController;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.sequencediagram.UMLSequenceDiagram;
import com.umleditor.view.window.pages.classdiagram.ClassDiagramPageBuilder;
import com.umleditor.view.window.pages.mainmenu.MainMenuPageBuilder;
import com.umleditor.view.window.pages.sequencediagram.SequenceDiagramPageBuilder;

public class AppConfig {
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

    private static void addPrimitivePageAssociations() throws ClassNotFoundException {
        AppContext.addPrimitivePageAssociation(AppPage.MAIN_MENU, MainMenuPageBuilder.class);
    }

    private static void loadProperites() throws Exception {
        AppContext.loadPropertiesFrom("src/main/resources/application.properties");
    }

    public static void initializeContext() throws Exception {
        addDiagramPageAssociations();
        addPrimitivePageAssociations();
        loadProperites();
    }
}
