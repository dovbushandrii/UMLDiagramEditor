package com.umleditor.view.window.classdiagram;

import com.umleditor.controller.controllers.diagramload.DiagramLoadController;
import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.common.interfaces.UMLDiagram;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ClassDiagramPageBuilder {

    private static DiagramPageController diagramPageController;

    private static Button saveButton() {
        Button button = new Button();
        button.setText("Save File");
        button.setOnAction(event -> {
            UMLDiagram diagram = diagramPageController.getDiagram();
            if (diagram != null) {
                DiagramLoadController.saveDiagram(diagram);
            }
        });
        return button;
    }

    private static Button openButton() {
        Button button = new Button();
        button.setText("Open File");
        button.setOnAction(event -> {
            DiagramLoadController.loadDiagram();
        });
        return button;
    }

    private static Button backButton() {
        Button button = new Button();
        button.setText("Back");
        button.setOnAction(event -> {
            MainWindow.setPage(AppPage.MAIN_MENU);
        });
        return button;
    }

    public static Parent buildPage(DiagramPageController controller) {
        diagramPageController = controller;

        VBox root = new VBox();

        root.getChildren().add(backButton());
        root.getChildren().add(openButton());
        root.getChildren().add(saveButton());

        return root;
    }
}
