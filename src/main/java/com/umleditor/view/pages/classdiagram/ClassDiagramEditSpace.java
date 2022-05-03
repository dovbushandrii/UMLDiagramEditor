package com.umleditor.view.pages.classdiagram;

import com.umleditor.context.AppContext;
import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.interfaces.UMLDiagram;
import com.umleditor.view.pages.classdiagram.editwindows.EditClassWindow;
import com.umleditor.view.pages.classdiagram.relations.RelationElementBuilder;
import com.umleditor.view.pages.interfaces.DiagramEditSpace;
import com.umleditor.view.pages.interfaces.Shortcuts;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that control edit space for Class Diagram
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class ClassDiagramEditSpace implements DiagramEditSpace {

    private final Pane editSpace;
    private Pane editSpacePane;
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

    private Button constructEditClassButton() {
        Button editButton = new Button("Edit Classes");
        editButton.setOnAction(e -> {
            EditClassWindow modalWindow = new EditClassWindow(diagram);
            modalWindow.show();
            updateCurrentEditSpace();
        });
        return editButton;
    }

    private void constructEditMenu() {
        HBox editMenu = new HBox();
        Shortcuts.bindWidth(editMenu, editSpace);
        editMenu.setStyle("-fx-background-color:" + AppContext.getProperty("edit-menu-color"));

        editMenu.getChildren().add(constructEditClassButton());
        editMenu.getChildren().add(new Button("Edit Relations"));

        editSpace.getChildren().add(editMenu);
    }

    private void constructEditSpace() {
        Pane editSpacePane = new Pane();
        Shortcuts.bindWidth(editSpacePane, editSpace);
        Shortcuts.bindHeight(editSpacePane, editSpace);

        this.editSpacePane = editSpacePane;
        editSpace.getChildren().add(editSpacePane);
    }

    private void updateCurrentEditSpace() {
        if (diagram != null) {
            Pane editSpace = (Pane) this.editSpace.getChildren().get(1);
            // Delete previous diagram
            editSpace.getChildren().clear();
            // Create new Diagram
            Map<UMLClass, Node> elementMap = new HashMap<>();
            diagram.getClasses().forEach(c -> {
                Node classElement = ClassElementBuilder.constructClassElement(c);
                makeDraggable(classElement);
                editSpace.getChildren().add(classElement);
                elementMap.put(c, classElement);
            });
            diagram.getRelations().forEach(r -> {
                Node classFrom = elementMap.get(r.getFrom());
                Node classTo = elementMap.get(r.getTo());
                Node relation = RelationElementBuilder.constructRelation(classFrom, classTo, r.getType());
                editSpace.getChildren().add(relation);
            });
        }
    }

    /**
     * Sets listeners to node that allows to drag it
     * in between Edit Space borders
     */
    private void makeDraggable(Node node) {
        final Pane edSpace = this.editSpacePane;
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
            if(isInXBorders(node,edSpace,newPosX)) {
                node.setLayoutX(e.getSceneX() + dragStartX);
            }
            if(isInYBorders(node,edSpace,newPosY)) {
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
