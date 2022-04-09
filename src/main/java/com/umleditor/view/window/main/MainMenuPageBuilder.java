package com.umleditor.view.window.main;

import com.umleditor.controller.controllers.diagramload.DiagramLoadController;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenuPageBuilder {

    private static Button openButton() {
        Button button = new Button();
        button.setText("Open File");
        button.setOnAction(event ->{
            DiagramLoadController.loadDiagram();
        });
        return button;
    }

    private static Button classDiagramButton() {
        Button button = new Button();
        button.setText("Class Diagram Edit");
        button.setOnAction(event ->{
            MainWindow.setPage(AppPage.CLASS_DIAGRAM);
        });
        return button;
    }

    private static Button sequenceDiagramButton() {
        Button button = new Button();
        button.setText("Sequence Diagram Edit");
        button.setOnAction(event ->{
            MainWindow.setPage(AppPage.SEQUENCE_DIAGRAM);
        });
        return button;
    }

    public static Parent buildPage() {
        VBox root = new VBox();

        root.getChildren().add(openButton());
        root.getChildren().add(classDiagramButton());
        root.getChildren().add(sequenceDiagramButton());

        return root;
    }
}
