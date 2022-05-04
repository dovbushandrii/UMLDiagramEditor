package com.umleditor.context;

import com.umleditor.controller.enums.AppPage;
import com.umleditor.view.pages.classdiagram.ClassDiagramPage;
import com.umleditor.view.pages.editproject.EditProjectPage;
import com.umleditor.view.pages.mainmenu.MainMenuPage;
import com.umleditor.view.pages.sequencediagram.SequenceDiagramPage;

/**
 * Application start config class
 * Has encoded start mappings and properties files.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class AppConfig {

    /**
     * Defines Page Name to Page Builder for 'primitive' pages context association
     * @throws ClassNotFoundException
     */
    private static void addPageAssociations() throws ClassNotFoundException {
        AppContext.addPageAssociation(AppPage.MAIN_MENU, MainMenuPage.class);
        AppContext.addPageAssociation(AppPage.EDIT_CLASS_DIAGRAMS, ClassDiagramPage.class);
        AppContext.addPageAssociation(AppPage.EDIT_SEQUENCE_DIAGRAMS, SequenceDiagramPage.class);
        AppContext.addPageAssociation(AppPage.EDIT_PROJECT, EditProjectPage.class);
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
        addPageAssociations();
        loadProperites();
    }
}
