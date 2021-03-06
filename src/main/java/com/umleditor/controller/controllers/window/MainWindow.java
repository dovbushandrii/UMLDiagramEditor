package com.umleditor.controller.controllers.window;

import com.umleditor.context.AppContext;
import com.umleditor.controller.enums.AppPage;
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
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class MainWindow {
    /**
     * Root node for all pages. Switching pages - replacing child nodes of root
     */
    private static AnchorPane root;
    /**
     * Contains pre-built pages and their names
     */
    private final static Map<AppPage, Pane> pageMap = new HashMap<>();
    private static Stage primaryStage = null;

    private static AppPage currentPage = AppPage.MAIN_MENU;

    /**
     * Loads pages from Application Context
     */
    private static void loadLayouts() {
        Map<AppPage, Pane> diagramPages = AppContext.getPages();
        diagramPages.forEach(pageMap::put);
    }

    /**
     * Binds size of pages to size of window
     */
    private static void bindToStageSize() {
        pageMap.values().forEach(p -> {
            Shortcuts.bindWidth(p, root);
            Shortcuts.bindHeight(p, root);
        });
    }

    /**
     * Basic method to start application.
     * Constructs window with pages.
     */
    public static void start(Stage stage) {
        // Frame for Pages
        root = new AnchorPane();
        Shortcuts.bindWidth(root, stage);
        Shortcuts.bindHeight(root, stage);

        primaryStage = stage;

        loadLayouts();

        bindToStageSize();

        // Set Primary Page
        root.getChildren().add(pageMap.get(currentPage));
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

    /**
     * Switches current page to newPage
     * @param newPage Next page name
     */
    public static void setPage(AppPage newPage) {
        root.getChildren().remove(pageMap.get(currentPage));
        root.getChildren().add(pageMap.get(newPage));
        primaryStage.setTitle(newPage.getStageTitle());
        currentPage = newPage;
    }
}
