package com.umleditor.controller.controllers.window;

import com.umleditor.controller.controllers.context.AppContext;
import com.umleditor.controller.controllers.pagecontrol.impl.ClassDiagramController;
import com.umleditor.controller.controllers.pagecontrol.impl.MainMenuController;
import com.umleditor.controller.controllers.pagecontrol.impl.SequenceDiagramController;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.classdiagram.impl.UMLClassDiagramImpl;
import com.umleditor.model.sequencediagram.impl.UMLSequenceDiagramImpl;
import com.umleditor.model.sequencediagram.interfaces.UMLSequenceDiagram;
import com.umleditor.view.window.anchor.AnchorFrameBuilder;
import com.umleditor.view.window.classdiagram.ClassDiagramPageBuilder;
import com.umleditor.view.window.main.MainMenuPageBuilder;
import com.umleditor.view.window.sequencediagram.SequenceDiagramPageBuilder;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class MainWindow {

    private static double defaultHeight = 600.0;
    private static double defaultWidth = 800.0;

    private static AnchorPane root;
    private static Map<AppPage, Parent> grid = new HashMap<>();

    private static AppPage currentPage = AppPage.MAIN_MENU;

    private static void prepareContext() {
        AppContext.addPageController(AppPage.MAIN_MENU, new MainMenuController());
        AppContext.addPageController(AppPage.CLASS_DIAGRAM, new ClassDiagramController());
        AppContext.addPageController(AppPage.SEQUENCE_DIAGRAM, new SequenceDiagramController());

        AppContext.mapPageToDiagram(AppPage.CLASS_DIAGRAM, UMLClassDiagramImpl.class);
        AppContext.mapPageToDiagram(AppPage.SEQUENCE_DIAGRAM, UMLSequenceDiagramImpl.class);
    }

    private static void loadLayouts() {
        grid.put(AppPage.MAIN_MENU, MainMenuPageBuilder.buildPage());
        grid.put(AppPage.CLASS_DIAGRAM, ClassDiagramPageBuilder.buildPage());
        grid.put(AppPage.SEQUENCE_DIAGRAM, SequenceDiagramPageBuilder.buildPage());
    }

    public static void start(Stage stage) {
        try {
            // Frame for Pages
            root = AnchorFrameBuilder.buildAnchor();

            loadLayouts();
            prepareContext();

            // Set Primary Page
            root.getChildren().add(grid.get(currentPage));
            Scene scene = new Scene(root, defaultWidth, defaultHeight);

            // Set given Stage
            stage.setTitle("UML Editor");
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Parent getPage(AppPage page) {
        return grid.get(page);
    }

    public static void setPage(AppPage newPage) {
        root.getChildren().remove(grid.get(currentPage));
        root.getChildren().add(grid.get(newPage));
        currentPage = newPage;
    }
}
