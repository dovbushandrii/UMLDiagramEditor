/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file MainWindow.java
 *
 * @brief Main Window Pages Controller
 */
package com.umleditor.controller.controllers.window;

import com.umleditor.context.AppContext;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.view.window.anchor.AnchorFrameBuilder;
import com.umleditor.view.pages.interfaces.Shortcuts;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Main Window controller.
 * Allows to switch pages with its static methods.
 * Gets a list of pages from Application Context.
 */
public class MainWindow {
    private static AnchorPane root;
    private final static Map<AppPage, Pane> grid = new HashMap<>();
    private static Stage primaryStage = null;

    private static AppPage currentPage = AppPage.MAIN_MENU;

    private static void loadLayouts() {
        Map<AppPage, Pane> diagramPages = AppContext.getDiagramPages();
        diagramPages.forEach(grid::put);

        Map<AppPage, Pane> defaultPages = AppContext.getPrimitivePages();
        defaultPages.forEach(grid::put);
    }

    private static void bindToStageSize() {
        grid.values().forEach(p -> {
            Shortcuts.bindWidth(p, root);
            Shortcuts.bindHeight(p, root);
        });
    }

    public static void start(Stage stage) {
        // Frame for Pages
        root = new AnchorPane();
        Shortcuts.bindWidth(root, stage);
        Shortcuts.bindHeight(root, stage);

        primaryStage = stage;

        loadLayouts();

        bindToStageSize();

        // Set Primary Page
        root.getChildren().add(grid.get(currentPage));
        primaryStage.setTitle(currentPage.getStageTitle());
        Scene scene = new Scene(root,
                Double.parseDouble(AppContext.getProperty("window-default-width")),
                Double.parseDouble(AppContext.getProperty("window-default-height")));

        primaryStage.setMinWidth(
                Double.parseDouble(AppContext.getProperty("window-min-width"))
        );
        primaryStage.setMinHeight(
                Double.parseDouble(AppContext.getProperty("window-min-height"))
        );

        // Set given Stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void setPage(AppPage newPage) {
        root.getChildren().remove(grid.get(currentPage));
        root.getChildren().add(grid.get(newPage));
        primaryStage.setTitle(newPage.getStageTitle());
        currentPage = newPage;
    }
}
