package com.umleditor.view.pages.classdiagram.editwindows;

import com.umleditor.context.AppContext;
import com.umleditor.model.UMLProject;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.interfaces.UMLDiagram;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SelectExistingClassWindow {

    public static UMLClass selectExistingClass(UMLDiagram currentDiagram) {
        return selectExistingClass(currentDiagram.getAllClasses());
    }

    private static UMLClass selectExistingClass(List<UMLClass> notFromHere) {
        UMLProject project = AppContext.getUMLProject();
        if (project == null) {
            constructBlankWindow();
            return null;
        } else {
            return selectClassFromWindow(substractClasses(project.getAllClasses(), notFromHere));
        }
    }

    private static List<UMLClass> substractClasses(List<UMLClass> from, List<UMLClass> substract) {
        List<UMLClass> result = new ArrayList<>(from);
        for(UMLClass toDelete : substract) {
            result = result.stream()
                    .filter(c -> !c.getName().equals(toDelete.getName()))
                    .collect(Collectors.toList());
        }
        return result;
    }

    private static void constructBlankWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Pane mainPane = new VBox();

        Label noClasses = new Label("No Classes were found in Project");

        Scene scene = new Scene(mainPane, 500, 500);

        window.setScene(scene);
        window.setTitle("Select Class From Project");
        window.showAndWait();
    }

    private static UMLClass selectClassFromWindow(List<UMLClass> classes) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Pane mainPane = new VBox();

        ListView<UMLClass> existingClasses = constructClassList(classes);

        HBox buttons = new HBox();

        Button selectButton = new Button("Add Selected");
        selectButton.setOnAction(e -> {
            window.close();
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            existingClasses.getItems().clear();
            window.close();
        });

        buttons.getChildren().addAll(selectButton,cancelButton);
        mainPane.getChildren().addAll(existingClasses,buttons);

        Scene scene = new Scene(mainPane, 500, 500);

        window.setScene(scene);
        window.setTitle("Select Class From Project");
        window.showAndWait();
        if(existingClasses.getItems().size() > 0){
            return existingClasses.getSelectionModel().getSelectedItem();
        }
        return null;
    }

    private static void setCellFactory(ListView<UMLClass> classList) {
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

    private static ListView<UMLClass> constructClassList(List<UMLClass> classes) {
        ListView<UMLClass> classList = new ListView<>();
        classList.getItems().setAll(classes);
        setCellFactory(classList);
        classList.setEditable(false);
        classList.setFixedCellSize(30);
        return classList;
    }
}
