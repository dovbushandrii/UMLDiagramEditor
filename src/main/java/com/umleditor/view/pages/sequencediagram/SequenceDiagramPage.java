package com.umleditor.view.pages.sequencediagram;

import com.umleditor.controller.controllers.window.MainWindow;
import com.umleditor.controller.enums.AppPage;
import com.umleditor.model.UMLProject;
import com.umleditor.view.pages.classdiagram.ClassDiagramEditSpace;
import com.umleditor.view.pages.interfaces.DiagramEditSpace;
import com.umleditor.view.pages.interfaces.ProjectDependentPage;
import com.umleditor.view.pages.interfaces.Shortcuts;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Constructs page for Sequence Diagram editing.
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class SequenceDiagramPage implements ProjectDependentPage {

    private UMLProject project;
    private DiagramEditSpace editSpace;

    private Button backButton() {
        Button button = new Button();
        button.setText("Back");
        button.setOnAction(event -> {
            MainWindow.setPage(AppPage.EDIT_PROJECT);
        });
        return button;
    }

    @Override
    public Pane build() {
        VBox root = new VBox();

        HBox menuBar = new HBox();
        Shortcuts.bindWidth(menuBar, root);
        menuBar.setStyle("-fx-background-color: #9d9dcd");

        menuBar.getChildren().add(backButton());
        root.getChildren().add(menuBar);

        editSpace = new SequenceDiagramEditSpace(project);
        Pane editSpacePane = editSpace.getEditSpace();
        Shortcuts.bindWidth(editSpacePane, root);
        Shortcuts.bindHeight(editSpacePane, root);
        root.getChildren().add(editSpace.getEditSpace());

        return root;
    }

    @Override
    public void updateProject(UMLProject project) {
        editSpace.updateEditSpace(project);
    }
}
