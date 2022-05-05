package com.umleditor.view.pages.classdiagram.editwindows;

import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.classdiagram.UMLRelation;
import com.umleditor.model.classdiagram.enums.UMLRelationType;
import com.umleditor.model.common.UMLClass;
import com.umleditor.view.errorwindow.ErrorWindow;
import com.umleditor.view.pages.interfaces.Shortcuts;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Edit window for Class Diagram relations
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class EditRelationWindow {
    private Stage window;
    private ListView<UMLRelation> relationList;
    private UMLClassDiagram diagram;

    public EditRelationWindow(UMLClassDiagram diagram) {
        this.diagram = diagram;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Pane mainPane = new VBox();

        relationList = constructRelationList(diagram);
        Button addRelation = new Button("New Relation");
        addRelation.setOnAction(e -> {
            if(diagram.getAllClasses().size() > 1) {
                UMLRelation newbie = new UMLRelation();
                newbie.setFrom(diagram.getAllClasses().get(0));
                newbie.setTo(diagram.getAllClasses().get(1));
                try {
                    diagram.addRelation(newbie);
                    relationList.getItems().setAll(diagram.getAllRelations());
                    relationList.getSelectionModel().select(newbie);
                    double height = 30.0 * diagram.getAllRelations().size();
                    relationList.setMinHeight(height);
                    relationList.setMaxHeight(height);
                } catch (Exception exception) {
                    ErrorWindow.showError("Cannot add new relation", exception.getMessage());
                }
            }
        });

        Button deleteRelation = new Button("Delete Selected Relation");
        deleteRelation.setOnAction(e -> {
            UMLRelation relation = relationList.getSelectionModel().getSelectedItem();
            if(relation != null) {
                diagram.deleteRelation(relation);
                relationList.getItems().remove(relation);
                double height = 30.0 * diagram.getAllRelations().size();
                relationList.setMinHeight(height);
                relationList.setMaxHeight(height);
            }
        });

        Button closeButton = new Button("Save and Close");
        closeButton.setOnAction(e -> {
            window.close();
        });

        HBox buttonMenu = new HBox();
        buttonMenu.getChildren().addAll(addRelation, deleteRelation);

        mainPane.getChildren().addAll(relationList, buttonMenu , closeButton);

        Scene scene = new Scene(mainPane, 670, 500);

        window.setScene(scene);
        window.setTitle("Edit Relations");
    }

    public void show() {
        window.showAndWait();
    }

    private ListView<UMLRelation> constructRelationList(UMLClassDiagram diagram) {
        ListView<UMLRelation> relationList = new ListView<>();
        if(diagram.getAllClasses().size() > 0) {
            relationList.getItems().setAll(diagram.getAllRelations());
            setRelationsCellFactory(relationList);
        }

        relationList.setEditable(true);

        relationList.setFixedCellSize(30);

        double height = 30.0 * diagram.getAllRelations().size();
        relationList.setMinHeight(height);
        relationList.setMaxHeight(height);
        return relationList;
    }

    private ListView<UMLRelationType> constructRelationTypeList() {
        ListView<UMLRelationType> types = new ListView<>();
        types.getItems().setAll(UMLRelationType.values());
        types.setCellFactory(new Callback<ListView<UMLRelationType>, ListCell<UMLRelationType>>() {
            @Override
            public ListCell<UMLRelationType> call(ListView<UMLRelationType> umlElementModifierListView) {
                return new ListCell<UMLRelationType>(){
                    @Override
                    public void updateItem(UMLRelationType type, boolean empty) {
                        super.updateItem(type, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else if (type != null) {
                            setText(null);
                            setGraphic(new Label(type.getTypeSymbol()));
                        } else {
                            setText("null");
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        types.setFixedCellSize(30);
        types.setMinHeight(30);
        types.setMaxHeight(30);
        types.setMaxWidth(140);
        return types;
    }

    private void setCellFactory(ListView<UMLClass> classList) {
        classList.setCellFactory(new Callback<ListView<UMLClass>,
                ListCell<UMLClass>>() {
            @Override
            public ListCell<UMLClass> call(ListView<UMLClass> list) {
                return new ListCell<UMLClass>(){
                    @Override
                    public void updateItem(UMLClass clazz, boolean empty) {
                        super.updateItem(clazz, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else if (clazz != null) {
                            setText(null);
                            setGraphic(new Label(clazz.getName()));
                        } else {
                            setText("null");
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }

    private ListView<UMLClass> constructClassList(UMLClassDiagram diagram) {
        ListView<UMLClass> classList = new ListView<>();
        classList.getItems().setAll(diagram.getAllClasses());
        setCellFactory(classList);
        classList.setEditable(false);
        classList.setFixedCellSize(30);
        Shortcuts.setFixedHeight(classList,30);
        return classList;
    }

    private Pane constructRelationCell(UMLRelation relation) {
        HBox cell = new HBox();

        // From Class
        ListView<UMLClass> fromClass = constructClassList(diagram);
        fromClass.getSelectionModel().select(relation.getFrom());

        // Relation type
        ListView<UMLRelationType> types = constructRelationTypeList();
        types.getSelectionModel().selectedIndexProperty().addListener((o,ov,nv) -> {
            UMLRelationType select = types.getSelectionModel().getSelectedItem();
            relation.setType(select);
        });
        types.getSelectionModel().select(relation.getType());

        // To Class
        ListView<UMLClass> toClass = constructClassList(diagram);
        toClass.getSelectionModel().select(relation.getTo());

        fromClass.getSelectionModel().selectedIndexProperty().addListener((o,ov,nv) -> {
            UMLClass selectFrom = fromClass.getSelectionModel().getSelectedItem();
            UMLClass selectTo = toClass.getSelectionModel().getSelectedItem();
            if(diagram.relationExists(selectFrom,selectTo)) {
                cell.setStyle("-fx-border-color: red");
            }
            else {
                relation.setFrom(selectFrom);
                cell.setStyle("-fx-border-color: transparent");
            }

        });
        toClass.getSelectionModel().selectedIndexProperty().addListener((o,ov,nv) -> {
            UMLClass selectFrom = fromClass.getSelectionModel().getSelectedItem();
            UMLClass selectTo = toClass.getSelectionModel().getSelectedItem();
            if(diagram.relationExists(selectFrom,selectTo)) {
                cell.setStyle("-fx-border-color: red");
            }
            else {
                relation.setTo(selectTo);
                cell.setStyle("-fx-border-color: transparent");
            }

        });

        cell.getChildren().addAll(fromClass,types,toClass);

        return cell;
    }

    private void setRelationsCellFactory(ListView<UMLRelation> fieldList) {
        fieldList.setCellFactory(new Callback<ListView<UMLRelation>, ListCell<UMLRelation>>() {
            @Override
            public ListCell<UMLRelation> call(ListView<UMLRelation> umlClassAttributeListView) {
                return new ListCell<UMLRelation>(){
                    @Override
                    public void updateItem(UMLRelation relation, boolean empty) {
                        super.updateItem(relation, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else if (relation != null) {
                            setText(null);
                            Pane cell = constructRelationCell(relation);
                            setGraphic(cell);
                        } else {
                            setText("null");
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }
}
