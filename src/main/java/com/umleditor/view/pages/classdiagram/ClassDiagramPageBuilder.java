package com.umleditor.view.pages.classdiagram;

import com.umleditor.controller.controllers.diagramload.DiagramLoadController;
import com.umleditor.controller.controllers.pagecontrol.interfaces.DiagramPageController;
import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.view.pages.interfaces.DiagramEditSpace;
import com.umleditor.view.pages.interfaces.DiagramPageBuilder;
import com.umleditor.view.pages.interfaces.Shortcuts;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Constructs non-primitive page for Class Diagram editing
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class ClassDiagramPageBuilder implements DiagramPageBuilder {

    private DiagramPageController diagramPageController = null;

    private Button newButton() {
        Button button = new Button();
        button.setText("New");
        button.setOnAction(event -> {
            UMLDiagram diagram = new UMLClassDiagram();
            diagramPageController.loadDiagram(diagram);
        });
        return button;
    }

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
    public Pane build(DiagramPageController controller) {
        diagramPageController = controller;

        DiagramEditSpace editSpace = new ClassDiagramEditSpace(controller.getDiagram());
        diagramPageController.setEditSpace(editSpace);

        VBox root = new VBox();

        HBox menuBar = new HBox();
        Shortcuts.bindWidth(menuBar, root);
        menuBar.setStyle("-fx-background-color: #9d9dcd");

        menuBar.getChildren().add(backButton());
        menuBar.getChildren().add(newButton());
        menuBar.getChildren().add(openButton());
        menuBar.getChildren().add(saveButton());

        root.getChildren().add(menuBar);

        Pane editSpacePane = editSpace.getEditSpace();
        Shortcuts.bindWidth(editSpacePane, root);
        Shortcuts.bindHeight(editSpacePane, root);
        root.getChildren().add(editSpace.getEditSpace());

        return root;
    }
}