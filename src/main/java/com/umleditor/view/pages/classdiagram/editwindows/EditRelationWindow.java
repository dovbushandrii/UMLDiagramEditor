package com.umleditor.view.pages.classdiagram.editwindows;

import com.umleditor.model.classdiagram.UMLCDRelation;
import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.classdiagram.enums.UMLCDRelationType;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLClassAttribute;
import com.umleditor.model.common.enums.UMLElementModifier;
import com.umleditor.view.errorwindow.ErrorWindow;
import com.umleditor.view.pages.interfaces.Shortcuts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

/**
 * TODO: Comment
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class EditRelationWindow {
    private Stage window;
    private ListView<UMLCDRelation> relationList;
    private UMLClassDiagram diagram;

    public EditRelationWindow(UMLClassDiagram diagram) {
        this.diagram = diagram;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Pane mainPane = new VBox();

        relationList = constructRelationList(diagram);
        Button addClass = new Button("New Relation");
        addClass.setOnAction(e -> {
            UMLCDRelation newbie = new UMLCDRelation();
            newbie.setFrom(diagram.getClasses().get(0));
            newbie.setTo(diagram.getClasses().get(0));
            try {
                diagram.addRelation(newbie);
                relationList.getItems().setAll(diagram.getRelations());
                relationList.getSelectionModel().select(newbie);
                double height = 30.0 * diagram.getRelations().size() + 10;
                relationList.setMinHeight(height);
                relationList.setMaxHeight(height);
            } catch (Exception exception) {
                ErrorWindow.showError("Cannot add new relation", exception.getMessage());
            }
        });

        Button deleteClass = new Button("Delete Selected Relation");
        deleteClass.setOnAction(e -> {
            UMLCDRelation relation = relationList.getSelectionModel().getSelectedItem();
            diagram.deleteRelation(relation.getFrom(), relation.getTo());
            relationList.getItems().remove(relation);
            double height = 30.0 * diagram.getRelations().size() + 10;
            relationList.setMinHeight(height);
            relationList.setMaxHeight(height);
        });

        mainPane.getChildren().addAll(relationList, addClass, deleteClass);

        Scene scene = new Scene(mainPane, 630, 500);

        window.setScene(scene);
        window.setTitle("Edit Relations");
    }

    public void show() {
        window.showAndWait();
    }

    private ListView<UMLCDRelation> constructRelationList(UMLClassDiagram diagram) {
        ListView<UMLCDRelation> relationList = new ListView<>();
        relationList.getItems().setAll(diagram.getRelations());
        setRelationsCellFactory(relationList);

        relationList.setEditable(true);

        relationList.setFixedCellSize(30);

        double height = 30.0 * diagram.getRelations().size() + 10;
        relationList.setMinHeight(height);
        relationList.setMaxHeight(height);
        return relationList;
    }

    private ListView<UMLCDRelationType> constructRelationTypeList() {
        ListView<UMLCDRelationType> types = new ListView<>();
        types.getItems().setAll(UMLCDRelationType.values());
        types.setCellFactory(new Callback<ListView<UMLCDRelationType>, ListCell<UMLCDRelationType>>() {
            @Override
            public ListCell<UMLCDRelationType> call(ListView<UMLCDRelationType> umlElementModifierListView) {
                return new ListCell<UMLCDRelationType>(){
                    @Override
                    public void updateItem(UMLCDRelationType type, boolean empty) {
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
        classList.getItems().setAll(diagram.getClasses());
        setCellFactory(classList);
        classList.setEditable(false);
        classList.setFixedCellSize(30);
        Shortcuts.setFixedHeight(classList,30);
        return classList;
    }

    private Pane constructRelationCell(UMLCDRelation relation) {
        HBox cell = new HBox();

        // From Class
        ListView<UMLClass> fromClass = constructClassList(diagram);
        fromClass.getSelectionModel().select(relation.getFrom());

        // Relation type
        ListView<UMLCDRelationType> types = constructRelationTypeList();
        types.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                UMLCDRelationType select = types.getSelectionModel().getSelectedItem();
                relation.setType(select);
            }
        });
        types.getSelectionModel().select(relation.getType());

        // To Class
        ListView<UMLClass> toClass = constructClassList(diagram);
        toClass.getSelectionModel().select(relation.getTo());

        fromClass.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                UMLClass selectFrom = fromClass.getSelectionModel().getSelectedItem();
                UMLClass selectTo = toClass.getSelectionModel().getSelectedItem();
                if(diagram.relationExists(selectFrom,selectTo)) {
                    cell.setStyle("-fx-border-color: red");
                }
                else {
                    relation.setFrom(selectFrom);
                    cell.setStyle("-fx-border-color: transparent");
                }
            }
        });
        toClass.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                UMLClass selectFrom = fromClass.getSelectionModel().getSelectedItem();
                UMLClass selectTo = toClass.getSelectionModel().getSelectedItem();
                if(diagram.relationExists(selectFrom,selectTo)) {
                    cell.setStyle("-fx-border-color: red");
                }
                else {
                    relation.setTo(selectTo);
                    cell.setStyle("-fx-border-color: transparent");
                }
            }
        });

        cell.getChildren().addAll(fromClass,types,toClass);

        return cell;
    }

    private void setRelationsCellFactory(ListView<UMLCDRelation> fieldList) {
        fieldList.setCellFactory(new Callback<ListView<UMLCDRelation>, ListCell<UMLCDRelation>>() {
            @Override
            public ListCell<UMLCDRelation> call(ListView<UMLCDRelation> umlClassAttributeListView) {
                return new ListCell<UMLCDRelation>(){
                    @Override
                    public void updateItem(UMLCDRelation relation, boolean empty) {
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
