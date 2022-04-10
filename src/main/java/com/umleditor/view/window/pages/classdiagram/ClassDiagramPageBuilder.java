package com.umleditor.view.window.pages.classdiagram;

import com.umleditor.controller.controllers.diagramload.DiagramLoadController;
import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.view.window.pages.interfaces.DiagramEditSpace;
import com.umleditor.view.window.pages.interfaces.DiagramPageBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ClassDiagramPageBuilder implements DiagramPageBuilder {

    private DiagramPageController diagramPageController = null;

    private Button saveButton() {
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

    private Button openButton() {
        Button button = new Button();
        button.setText("Open File");
        button.setOnAction(event -> {
            DiagramLoadController.loadDiagram();
        });
        return button;
    }

    private Button backButton() {
        Button button = new Button();
        button.setText("Back");
        button.setOnAction(event -> {
            MainWindow.setPage(AppPage.MAIN_MENU);
        });
        return button;
    }

    @Override
    public Parent build(DiagramPageController controller) {
        diagramPageController = controller;

        DiagramEditSpace editSpace = new ClassDiagramEditSpace(controller.getDiagram());
        diagramPageController.setEditSpace(editSpace);

        VBox root = new VBox();
        HBox menuBar = new HBox();

        menuBar.getChildren().add(backButton());
        menuBar.getChildren().add(openButton());
        menuBar.getChildren().add(saveButton());

        root.getChildren().add(menuBar);
        root.getChildren().add(editSpace.getEditSpace());

        return root;
    }
}