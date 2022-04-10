package com.umleditor.view.window.pages.mainmenu;

import com.umleditor.controller.controllers.diagramload.DiagramLoadController;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.view.window.pages.interfaces.PrimitivePageBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenuPageBuilder implements PrimitivePageBuilder {

    private Button openButton() {
        Button button = new Button();
        button.setText("Open File");
        button.setOnAction(event ->{
            DiagramLoadController.loadDiagram();
        });
        return button;
    }

    private Button classDiagramButton() {
        Button button = new Button();
        button.setText("Class Diagram Edit");
        button.setOnAction(event ->{
            MainWindow.setPage(AppPage.CLASS_DIAGRAM);
        });
        return button;
    }

    private Button sequenceDiagramButton() {
        Button button = new Button();
        button.setText("Sequence Diagram Edit");
        button.setOnAction(event ->{
            MainWindow.setPage(AppPage.SEQUENCE_DIAGRAM);
        });
        return button;
    }

    @Override
    public Parent build() {
        VBox root = new VBox();
        HBox menuBar = new HBox();

        menuBar.getChildren().add(openButton());
        menuBar.getChildren().add(classDiagramButton());
        menuBar.getChildren().add(sequenceDiagramButton());

        root.getChildren().add(menuBar);

        return root;
    }
}
