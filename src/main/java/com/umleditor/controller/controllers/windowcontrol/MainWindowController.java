package com.umleditor.controller.controllers.windowcontrol;

import com.umleditor.controller.enums.AppPage;
import com.umleditor.view.window.classdiagram.ClassDiagramEditPageBuilder;
import com.umleditor.view.window.classdiagram.ClassDiagramViewPageBuilder;
import com.umleditor.view.window.main.MainMenuPageBuilder;
import com.umleditor.view.window.sequencediagram.SequenceDiagramEditPageBuilder;
import com.umleditor.view.window.sequencediagram.SequenceDiagramViewPageBuilder;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class MainWindowController {
    private static AnchorPane root;
    private static Map<AppPage, Parent> grid = new HashMap<>();

    private static AppPage currentPage = AppPage.MAIN_MENU;

    public static void start(Stage stage) {
        try {
            grid.put(AppPage.MAIN_MENU, MainMenuPageBuilder.buildPage());
            grid.put(AppPage.CLASS_DIAGRAM_EDIT, ClassDiagramEditPageBuilder.buildPage());
            grid.put(AppPage.CLASS_DIAGRAM_VIEW, ClassDiagramViewPageBuilder.buildPage());
            grid.put(AppPage.SEQUENCE_DIAGRAM_EDIT, SequenceDiagramEditPageBuilder.buildPage());
            grid.put(AppPage.SEQUENCE_DIAGRAM_VIEW, SequenceDiagramViewPageBuilder.buildPage());

            root.getChildren().add(grid.get(currentPage));
            Scene scene = new Scene(root,800,600);
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
