package com.umleditor.view.pages.sequencediagram;

import com.umleditor.context.AppContext;
import com.umleditor.model.sequencediagram.UMLSequenceDiagram;
import com.umleditor.view.pages.interfaces.Shortcuts;
import com.umleditor.view.pages.sequencediagram.editwindows.EditMessagesWindow;
import com.umleditor.view.pages.sequencediagram.editwindows.EditObjectsWindow;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Parent for all exceptions defined in this project
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class SequenceDiagramEditTab {
    private final Pane editTab;
    private Pane editTabPane;
    private UMLSequenceDiagram diagram;

    public SequenceDiagramEditTab(UMLSequenceDiagram diagram) {
        this.editTab = new VBox();
        this.diagram = diagram;
        constructNewEditSpace();
        updateCurrentEditSpace();
    }

    public Pane getEditTab() {
        return this.editTab;
    }

    private void constructNewEditSpace() {
        constructEditMenu();
        constructEditSpace();
    }

    private TextField constructEditSequenceDiagramNameField() {
        TextField name = new TextField(this.diagram.getName());
        name.textProperty().addListener((o, ov, nv) -> {
            diagram.setName(name.getText());
        });
        return name;
    }

    private Button constructEditObjectsButton() {
        Button editButton = new Button("Edit Objects");
        editButton.setOnAction(e -> {
            EditObjectsWindow modalWindow = new EditObjectsWindow(diagram);
            modalWindow.show();
            updateCurrentEditSpace();
        });
        return editButton;
    }

    private Button constructEditMessagesButton() {
        Button editButton = new Button("Edit Messages");
        editButton.setOnAction(e -> {
            EditMessagesWindow modalWindow = new EditMessagesWindow(diagram);
            modalWindow.show();
            updateCurrentEditSpace();
        });
        return editButton;
    }

    private void constructEditMenu() {
        HBox editMenu = new HBox();
        Shortcuts.bindWidth(editMenu, editTab);
        editMenu.setStyle("-fx-background-color:" + AppContext.getProperty("edit-menu-color"));

        editMenu.getChildren().add(constructEditSequenceDiagramNameField());
        editMenu.getChildren().add(constructEditObjectsButton());
        editMenu.getChildren().add(constructEditMessagesButton());

        editTab.getChildren().add(editMenu);
    }

    private void constructEditSpace() {
        Pane editSpacePane = new Pane();
        Shortcuts.bindWidth(editSpacePane, editTab);
        Shortcuts.bindHeight(editSpacePane, editTab);

        this.editTabPane = editSpacePane;
        editTab.getChildren().add(editSpacePane);
    }

    private void updateCurrentEditSpace() {
        if (diagram != null) {
            Pane editSpace = (Pane) this.editTab.getChildren().get(1);
            // Delete previous diagram
            editSpace.getChildren().clear();
            // Create new Diagram
            Node diagramShowSpace = new VBox();
            //TODO: Construction of Sequence Diagram Node
            //diagramShowSpace = constructSequenceDiagramNode(diagram);

            editSpace.getChildren().addAll(diagramShowSpace);
        }
    }
}
