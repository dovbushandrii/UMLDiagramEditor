package com.umleditor.view.pages.sequencediagram.editwindows;

import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLElement;
import com.umleditor.model.sequencediagram.UMLActor;
import com.umleditor.model.sequencediagram.UMLMessage;
import com.umleditor.model.sequencediagram.UMLSequenceDiagram;
import com.umleditor.view.errorwindow.ErrorWindow;
import com.umleditor.view.pages.classdiagram.editwindows.SelectExistingClassWindow;
import com.umleditor.view.pages.interfaces.Shortcuts;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Window to edit objects in UML Sequence Diagram
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class EditObjectsWindow {

    private Stage window;
    private Pane editSpacePane;
    private ListView<UMLElement> classList;
    private UMLSequenceDiagram diagram;

    public EditObjectsWindow(UMLSequenceDiagram newDiagram) {

        this.diagram = newDiagram;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Pane mainPane = new VBox();

        editSpacePane = new Pane();

        mainPane.getChildren().addAll(buildClassSelectionAccordion(), editSpacePane, buildCloseWindowButton());

        Scene scene = new Scene(mainPane, 500, 500);

        window.setScene(scene);
        window.setTitle("Edit Diagram Classes");

        //Select first item to edit
        classList.getSelectionModel().select(0);
    }

    public void show() {
        window.showAndWait();
    }

    private Node buildCloseWindowButton() {
        Button closeButton = new Button("Save and Close");
        closeButton.setOnAction(e -> {
            window.close();
        });
        return closeButton;
    }

    private Node buildClassSelectionAccordion() {
        classList = constructClassList(diagram);

        VBox selectionSpace = new VBox();
        selectionSpace.getChildren().addAll(classList, buildObjectSelectionButtons());

        Accordion accordion = new Accordion();
        TitledPane classListAccordion = new TitledPane("Diagram Classes", selectionSpace);
        accordion.getPanes().add(classListAccordion);

        classList.getSelectionModel().selectedIndexProperty().addListener((o,ov,nv) -> {
            UMLElement selected = classList.getSelectionModel().getSelectedItem();
            if(selected != null) {
                classListAccordion.setText(selected.getName());
            }
            else {
                classListAccordion.setText("Diagram Classes");
            }
        });

        return accordion;
    }

    private Pane buildObjectSelectionButtons() {
        HBox editClassListMenu = new HBox();
        Button newClass = new Button("New Class");
        newClass.setOnAction(e -> {
            UMLClass newbie = new UMLClass();
            try {
                diagram.fullAddClass(newbie);
                classList.getItems().setAll(diagram.getAllObjects());
                classList.refresh();
                classList.getSelectionModel().select(classList.getItems().size() - 1);
                Shortcuts.setFixedHeight(classList, 30 * diagram.getAllObjects().size());
            } catch (Exception exception) {
                ErrorWindow.showError("Cannot add new class", exception.getMessage());
            }
        });

        Button newActor = new Button("New Actor");
        newActor.setOnAction(e -> {
            UMLActor newbie = new UMLActor();
            try {
                diagram.addObject(newbie);
                classList.getItems().setAll(diagram.getAllObjects());
                classList.refresh();
                classList.getSelectionModel().select(classList.getItems().size() - 1);
                Shortcuts.setFixedHeight(classList, 30 * diagram.getAllObjects().size());
            } catch (Exception exception) {
                ErrorWindow.showError("Cannot add new actor", exception.getMessage());
            }
        });

        Button addClass = new Button("Add Existing Class");
        addClass.setOnAction(e -> {
            UMLClass newbie;
            try {
                newbie = SelectExistingClassWindow.selectExistingClass(diagram);
                if (newbie != null) {
                    diagram.addObject(newbie);
                    classList.getItems().setAll(diagram.getAllObjects());
                    classList.refresh();
                    classList.getSelectionModel().select(classList.getItems().size() - 1);
                    Shortcuts.setFixedHeight(classList, 30 * diagram.getAllObjects().size());
                }
            } catch (Exception exception) {
                ErrorWindow.showError("Cannot add new class", exception.getMessage());
            }
        });

        Button deleteClass = new Button("Delete Selected");
        deleteClass.setOnAction(e -> {
            int index = classList.getSelectionModel().getSelectedIndex();
            if(index >= 0) {
                UMLElement umlelement = classList.getItems().get(index);
                diagram.deleteObject(umlelement);
                classList.getItems().setAll(diagram.getAllObjects());
                classList.refresh();
                Shortcuts.setFixedHeight(classList, 30 * diagram.getAllObjects().size());
            }
        });

        Button fullDeleteClass = new Button("Full Delete Selected");
        fullDeleteClass.setOnAction(e -> {
            int index = classList.getSelectionModel().getSelectedIndex();
            if(index >= 0) {
                UMLElement umlelement = classList.getItems().get(index);
                if(umlelement instanceof UMLClass){
                    diagram.fullDeleteClass((UMLClass) umlelement);
                }
                else {
                    diagram.deleteObject(umlelement);
                }
                classList.getItems().setAll(diagram.getAllObjects());
                classList.refresh();
                Shortcuts.setFixedHeight(classList, 30 * diagram.getAllObjects().size());
            }
        });
        editClassListMenu.getChildren().addAll(newClass, newActor, addClass, deleteClass, fullDeleteClass);

        Button moveUp = new Button("Move Up Selected");
        moveUp.setOnAction(e -> {
            UMLElement element = classList.getSelectionModel().getSelectedItem();
            if (element != null) {
                diagram.moveUpObject(element);
                classList.getItems().setAll(diagram.getAllObjects());
                classList.getSelectionModel().select(element);
                double height = 30.0 * diagram.getAllObjects().size();
                classList.setMinHeight(height);
                classList.setMaxHeight(height);
            }
        });

        Button moveDown = new Button("Move Down Selected");
        moveDown.setOnAction(e -> {
            UMLElement element = classList.getSelectionModel().getSelectedItem();
            if (element != null) {
                diagram.moveDownObject(element);
                classList.getItems().setAll(diagram.getAllObjects());
                classList.getSelectionModel().select(element);
                double height = 30.0 * diagram.getAllObjects().size();
                classList.setMinHeight(height);
                classList.setMaxHeight(height);
            }
        });

        HBox moveButtons = new HBox();
        moveButtons.getChildren().addAll(moveUp, moveDown);

        VBox menu = new VBox();

        menu.getChildren().addAll(editClassListMenu,moveButtons);

        return menu;
    }

    private Pane buildObjectEditSpace(UMLElement element) {
        VBox newEditSpace = new VBox();
        Shortcuts.bindWidth(newEditSpace, window);
        Shortcuts.bindHeight(newEditSpace, window);

        // Type of object (Class of Actor)
        newEditSpace.getChildren().addAll(buildObjectTypeField(element));

        // Name edit line
        newEditSpace.getChildren().addAll(buildNameEditField(element));

        return newEditSpace;
    }

    private Pane buildObjectTypeField(UMLElement element) {
        HBox typeLine = new HBox();
        Label typeName = new Label("Type: ");
        typeLine.getChildren().addAll(typeName);
        if(element instanceof UMLClass) {
            Label type = new Label("Class");
            typeLine.getChildren().addAll(type);
        }
        else {
            Label type = new Label("Actor");
            typeLine.getChildren().addAll(type);
        }
        return typeLine;
    }

    private Pane buildNameEditField(UMLElement element) {
        HBox nameFieldBox = new HBox();
        Label nameLab = new Label("Name:");
        TextField nameField = new TextField(element.getName());
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!diagram.objectNameExits(nameField.getText())) {
                element.setName(nameField.getText());
                nameField.setStyle("-fx-border-color: green");
            } else {
                nameField.setStyle("-fx-border-color: red");
            }
        });
        nameFieldBox.getChildren().addAll(nameLab, nameField);
        return nameFieldBox;
    }

    private void setObjectEditSpace(UMLElement element) {
        Pane objectEditSpace = buildObjectEditSpace(element);
        editSpacePane.getChildren().clear();
        editSpacePane.getChildren().addAll(objectEditSpace);
    }

    private void setCellFactory(ListView<UMLElement> objectList) {
        objectList.setCellFactory(new Callback<ListView<UMLElement>,
                ListCell<UMLElement>>() {
            @Override
            public ListCell<UMLElement> call(ListView<UMLElement> list) {
                return new ListCell<UMLElement>() {
                    @Override
                    public void updateItem(UMLElement object, boolean empty) {
                        super.updateItem(object, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else if (object != null) {
                            setText(null);
                            setGraphic(new Label(object.getName()));
                        } else {
                            setText("null");
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }

    private void setSetSelectionListener(ListView<UMLElement> objectList) {
        objectList.getSelectionModel().selectedIndexProperty().addListener((o,ov,nv) -> {
            UMLElement select = objectList.getSelectionModel().getSelectedItem();
            if(select != null) {
                setObjectEditSpace(select);
            }
        });
    }

    private ListView<UMLElement> constructClassList(UMLSequenceDiagram diagram) {
        ListView<UMLElement> objectList = new ListView<>();
        objectList.getItems().setAll(diagram.getAllObjects());
        setCellFactory(objectList);
        setSetSelectionListener(objectList);
        objectList.setFixedCellSize(30);
        Shortcuts.setFixedHeight(objectList, 30 * diagram.getAllObjects().size());
        return objectList;
    }
}
