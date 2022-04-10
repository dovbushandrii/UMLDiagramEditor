package com.umleditor.controller.controllers.window;

import com.umleditor.context.AppContext;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.view.window.anchor.AnchorFrameBuilder;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class MainWindow {

    private final static double defaultHeight = 600.0;
    private final static double defaultWidth = 800.0;

    private static AnchorPane root;
    private final static Map<AppPage, Parent> grid = new HashMap<>();
    private static Stage primaryStage = null;

    private static AppPage currentPage = AppPage.MAIN_MENU;

    private static void loadLayouts() {
        Map<AppPage, Parent> diagramPages = AppContext.getDiagramPages();
        diagramPages.forEach((k, v) -> grid.put(k, v));

        Map<AppPage, Parent> defaultPages = AppContext.getPrimitivePages();
        defaultPages.forEach((k, v) -> grid.put(k, v));
    }

    public static void start(Stage stage) {
        // Frame for Pages
        root = AnchorFrameBuilder.buildAnchor();
        primaryStage = stage;

        loadLayouts();

        // Set Primary Page
        root.getChildren().add(grid.get(currentPage));
        primaryStage.setTitle(currentPage.getStageTitle());
        Scene scene = new Scene(root, defaultWidth, defaultHeight);

        // Set given Stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Parent getPage(AppPage page) {
        return grid.get(page);
    }

    public static void setPage(AppPage newPage) {
        root.getChildren().remove(grid.get(currentPage));
        root.getChildren().add(grid.get(newPage));
        primaryStage.setTitle(newPage.getStageTitle());
        currentPage = newPage;
    }

    public static void setWindowTitle(String title) {
        if (primaryStage != null) {
            primaryStage.setTitle(title);
        }
    }
}
