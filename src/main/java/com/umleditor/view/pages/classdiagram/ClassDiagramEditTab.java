package com.umleditor.view.pages.classdiagram;

import com.umleditor.context.AppContext;
import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.common.UMLClass;
import com.umleditor.view.pages.classdiagram.editwindows.EditClassWindow;
import com.umleditor.view.pages.classdiagram.editwindows.EditRelationWindow;
import com.umleditor.view.pages.classdiagram.relations.RelationElementBuilder;
import com.umleditor.view.pages.interfaces.Shortcuts;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class ClassDiagramEditTab {
    private final Pane editTab;
    private Pane editTabPane;
    private UMLClassDiagram diagram;

    private double dragStartX;
    private double dragStartY;

    public ClassDiagramEditTab(UMLClassDiagram diagram) {
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

    private TextField constructEditClassDiagramNameField() {
        TextField name = new TextField(this.diagram.getName());
        name.textProperty().addListener((o,ov,nv) -> {
            diagram.setName(name.getText());
        });
        return name;
    }

    private Button constructEditClassButton() {
        Button editButton = new Button("Edit Classes");
        editButton.setOnAction(e -> {
            EditClassWindow modalWindow = new EditClassWindow(diagram);
            modalWindow.show();
            updateCurrentEditSpace();
        });
        return editButton;
    }

    private Button constructEditRelationButton() {
        Button editButton = new Button("Edit Relations");
        editButton.setOnAction(e -> {
            EditRelationWindow modalWindow = new EditRelationWindow(diagram);
            modalWindow.show();
            updateCurrentEditSpace();
        });
        return editButton;
    }

    private void constructEditMenu() {
        HBox editMenu = new HBox();
        Shortcuts.bindWidth(editMenu, editTab);
        editMenu.setStyle("-fx-background-color:" + AppContext.getProperty("edit-menu-color"));

        editMenu.getChildren().add(constructEditClassDiagramNameField());
        editMenu.getChildren().add(constructEditClassButton());
        editMenu.getChildren().add(constructEditRelationButton());

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
            Map<UMLClass, Node> elementMap = new HashMap<>();
            diagram.getAllClasses().forEach(c -> {
                Node classElement = ClassElementBuilder.constructClassElement(c);
                makeDraggable(classElement);
                editTabPane.getChildren().add(classElement);
                elementMap.put(c, classElement);
            });
            diagram.getAllRelations().forEach(r -> {
                Node classFrom = elementMap.get(r.getFrom());
                Node classTo = elementMap.get(r.getTo());
                Node relation = RelationElementBuilder.constructRelation(classFrom, classTo, r.getType());
                editTabPane.getChildren().add(relation);
            });
        }
    }

    /**
     * Sets listeners to node that allows to drag it
     * in between Edit Space borders
     */
    private void makeDraggable(Node node) {
        final Pane edSpace = this.editTabPane;
        node.setOnMousePressed(e -> {
            dragStartX = node.getLayoutX() - e.getSceneX();
            dragStartY = node.getLayoutY() - e.getSceneY();
            node.setCursor(Cursor.MOVE);
            node.toFront();
        });
        node.setOnMouseReleased(e -> {
            node.setCursor(Cursor.HAND);
        });
        node.setOnMouseDragged(e -> {
            double newPosX = e.getSceneX() + dragStartX;
            double newPosY = e.getSceneY() + dragStartY;
            if (isInXBorders(node, edSpace, newPosX)) {
                node.setLayoutX(e.getSceneX() + dragStartX);
            }
            if (isInYBorders(node, edSpace, newPosY)) {
                node.setLayoutY(e.getSceneY() + dragStartY);
            }
        });
    }

    private boolean isInXBorders(Node node, Pane pane, double newPos) {
        double paneWidth = pane.getWidth();
        double nodeWidth = node.getBoundsInParent().getWidth();
        double maxX = paneWidth - nodeWidth;
        return (newPos > 0 && newPos < maxX);
    }

    private boolean isInYBorders(Node node, Pane pane, double newPos) {
        double paneHeight = pane.getHeight();
        double nodeHeight = node.getBoundsInParent().getHeight();
        double maxY = paneHeight - nodeHeight;
        return (newPos > 0 && newPos < maxY);
    }
}
