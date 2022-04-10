package com.umleditor.view.window.pages.classdiagram;

import com.umleditor.context.AppContext;
import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.view.window.pages.interfaces.DiagramEditSpace;
import com.umleditor.view.window.pages.interfaces.Shortcuts;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.List;
import java.util.stream.Collectors;

public class ClassDiagramEditSpace implements DiagramEditSpace {

    private final Pane editSpace;
    private UMLClassDiagram diagram;

    private double dragStartX;
    private double dragStartY;

    public ClassDiagramEditSpace(UMLDiagram diagram) {
        if (diagram == null || diagram instanceof UMLClassDiagram) {
            this.editSpace = new VBox();
            constructNewEditSpace();
            this.diagram = (UMLClassDiagram) diagram;
            updateCurrentEditSpace();
        } else {
            throw new IllegalArgumentException("UMLDiagram is not Class Diagram");
        }
    }

    @Override
    public Pane getEditSpace() {
        return this.editSpace;
    }

    @Override
    public void updateEditSpace(UMLDiagram diagram) {
        if (diagram == null || diagram instanceof UMLClassDiagram) {
            this.diagram = (UMLClassDiagram) diagram;
            updateCurrentEditSpace();
        } else {
            throw new IllegalArgumentException("UMLDiagram is not Class Diagram");
        }
    }

    private void constructNewEditSpace() {
        constructEditMenu();
        constructEditSpace();
    }

    private void constructEditMenu() {
        HBox editMenu = new HBox();
        Shortcuts.bindWidth(editMenu,editSpace);
        editMenu.setStyle("-fx-background-color:" + AppContext.getProperty("edit-menu-color"));

        editMenu.getChildren().add(new Button("Add Class"));
        editMenu.getChildren().add(new Button("Add Relation"));
        editMenu.getChildren().add(new Button("Delete Class"));
        editMenu.getChildren().add(new Button("Delete Relation"));

        editSpace.getChildren().add(editMenu);
    }

    private void constructEditSpace() {
        Pane editSpacePane = new Pane();
        Shortcuts.bindWidth(editSpacePane,editSpace);
        Shortcuts.bindHeight(editSpacePane,editSpace);

        editSpace.getChildren().add(editSpacePane);
    }

    private void updateCurrentEditSpace() {
        if (diagram != null) {
            Pane editSpace = (Pane) this.editSpace.getChildren().get(1);
            // Delete previous diagram
            editSpace.getChildren().removeAll();
            // Create new Diagram
            List<Node> classes = diagram.getClasses()
                    .stream()
                    .map(ClassElementBuilder::constructClassElement)
                    .collect(Collectors.toList());
            classes.forEach(this::makeDraggable);
            classes.forEach(c -> editSpace.getChildren().add(c));
        }
    }

    private void makeDraggable(Node node) {
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
            node.setLayoutX(e.getSceneX() + dragStartX);
            node.setLayoutY(e.getSceneY() + dragStartY);
        });
    }


}
