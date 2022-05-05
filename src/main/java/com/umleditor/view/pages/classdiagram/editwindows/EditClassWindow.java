package com.umleditor.view.pages.classdiagram.editwindows;

import com.umleditor.model.classdiagram.UMLClassDiagram;
import com.umleditor.model.common.UMLClass;
import com.umleditor.model.common.UMLClassAttribute;
import com.umleditor.model.common.UMLClassMethod;
import com.umleditor.model.common.enums.UMLElementModifier;
import com.umleditor.view.errorwindow.ErrorWindow;
import com.umleditor.view.pages.interfaces.Shortcuts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
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
 * Window to edit classes in UML Class Diagram
 *
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 */
public class EditClassWindow {

    private Stage window;
    private Pane editSpacePane;
    private ListView<UMLClass> classList;
    private UMLClassDiagram diagram;

    public EditClassWindow(UMLClassDiagram newDiagram) {

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
        selectionSpace.getChildren().addAll(classList,buildClassSelectionButtons());

        Accordion accordion = new Accordion();
        TitledPane classListAccordion = new TitledPane("Diagram Classes", selectionSpace);
        accordion.getPanes().add(classListAccordion);

        classList.getSelectionModel().selectedIndexProperty().addListener((o,ov,nv) -> {
            UMLClass selected = classList.getSelectionModel().getSelectedItem();
            if(selected != null) {
                classListAccordion.setText(selected.getName());
            }
            else {
                classListAccordion.setText("Diagram Classes");
            }
        });

        return accordion;
    }

    private Pane buildClassSelectionButtons() {
        HBox editClassListMenu = new HBox();
        Button newClass = new Button("New Class");
        newClass.setOnAction(e -> {
            UMLClass newbie = new UMLClass();
            try {
                diagram.fullAddClass(newbie);
                classList.getItems().setAll(diagram.getAllClasses());
                classList.refresh();
                classList.getSelectionModel().select(classList.getItems().size() - 1);
                Shortcuts.setFixedHeight(classList, 30 * diagram.getAllClasses().size());
            } catch (Exception exception) {
                ErrorWindow.showError("Cannot add new class", exception.getMessage());
            }
        });

        Button addClass = new Button("Add Existing Class");
        addClass.setOnAction(e -> {
            UMLClass newbie;
            try {
                newbie = SelectExistingClassWindow.selectExistingClass(diagram);
                if (newbie != null) {
                    diagram.addClass(newbie);
                    classList.getItems().setAll(diagram.getAllClasses());
                    classList.refresh();
                    classList.getSelectionModel().select(classList.getItems().size() - 1);
                    Shortcuts.setFixedHeight(classList, 30 * diagram.getAllClasses().size());
                }
            } catch (Exception exception) {
                ErrorWindow.showError("Cannot add new class", exception.getMessage());
            }
        });

        Button deleteClass = new Button("Delete Selected Class");
        deleteClass.setOnAction(e -> {
            int index = classList.getSelectionModel().getSelectedIndex();
            if(index >= 0) {
                UMLClass clazz = classList.getItems().get(index);
                diagram.deleteClass(clazz);
                classList.getItems().setAll(diagram.getAllClasses());
                classList.refresh();
                Shortcuts.setFixedHeight(classList, 30 * diagram.getAllClasses().size());
            }
        });

        Button fullDeleteClass = new Button("Full Delete Selected Class");
        fullDeleteClass.setOnAction(e -> {
            int index = classList.getSelectionModel().getSelectedIndex();
            if(index >= 0) {
                UMLClass clazz = classList.getItems().get(index);
                diagram.fullDeleteClass(clazz);
                classList.getItems().setAll(diagram.getAllClasses());
                classList.refresh();
                Shortcuts.setFixedHeight(classList, 30 * diagram.getAllClasses().size());
            }
        });

        editClassListMenu.getChildren().addAll(newClass, addClass, deleteClass, fullDeleteClass);

        return editClassListMenu;
    }

    private Pane buildClassEditSpace(UMLClass clazz) {
        VBox newEditSpace = new VBox();
        Shortcuts.bindWidth(newEditSpace, window);
        Shortcuts.bindHeight(newEditSpace, window);

        // Name edit line
        newEditSpace.getChildren().addAll(buildNameEditField(clazz));

        // Abstract checkbox line
        newEditSpace.getChildren().addAll(buildAbstractCheckbox(clazz));

        // Fields List
        newEditSpace.getChildren().addAll(buildFieldsEdit(clazz));

        // Methods List
        newEditSpace.getChildren().addAll(buildMethodEdit(clazz));

        return newEditSpace;
    }

    private Pane buildNameEditField(UMLClass clazz) {
        HBox nameFieldBox = new HBox();
        Label nameLab = new Label("Name:");
        TextField nameField = new TextField(clazz.getName());
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!diagram.classNameExits(nameField.getText())) {
                clazz.setName(nameField.getText());
                nameField.setStyle("-fx-border-color: green");
            } else {
                nameField.setStyle("-fx-border-color: red");
            }
        });
        nameFieldBox.getChildren().addAll(nameLab, nameField);
        return nameFieldBox;
    }

    private Pane buildAbstractCheckbox(UMLClass clazz) {
        HBox abstractFieldBox = new HBox();
        Label abstractLabel = new Label("Abstract:");
        CheckBox isAbstract = new CheckBox();
        isAbstract.setSelected(clazz.isAbstract());
        isAbstract.selectedProperty().addListener((observable, oldValue, newValue) -> {
            clazz.setAbstract(isAbstract.isSelected());
        });
        abstractFieldBox.getChildren().addAll(abstractLabel, isAbstract);
        return abstractFieldBox;
    }

    private Pane buildFieldsEdit(UMLClass clazz) {
        VBox fieldsEditPane = new VBox();

        Label fieldsTitle = new Label("Fields:");

        ListView<UMLClassAttribute> fields = constructsFieldsList(clazz.getFields());

        HBox fieldEditMenu = new HBox();

        Button addField = new Button("New Field");
        addField.setOnAction(e -> {
            UMLClassAttribute newbie = new UMLClassAttribute();
            clazz.addField(newbie);
            fields.getItems().add(newbie);
            double height = 30.0 * clazz.getFields().size();
            fields.setMinHeight(height);
            fields.setMaxHeight(height);
        });

        Button deleteField = new Button("Delete Selected Field");
        deleteField.setOnAction(e -> {
            UMLClassAttribute field = fields.getSelectionModel().getSelectedItem();
            clazz.deleteField(field);
            fields.getItems().setAll(clazz.getFields());
            double height = 30.0 * clazz.getFields().size();
            fields.setMinHeight(height);
            fields.setMaxHeight(height);
        });

        fieldEditMenu.getChildren().addAll(addField, deleteField);

        fieldsEditPane.getChildren().addAll(fieldsTitle, fields, fieldEditMenu);
        return fieldsEditPane;
    }

    private Pane buildMethodEdit(UMLClass clazz) {
        VBox methodEditPane = new VBox();
        Label methodsTitle = new Label("Methods:");

        ListView<UMLClassMethod> methods = constructsMethodsList(clazz.getMethods());

        HBox methodEditMenu = new HBox();

        Button addMethod = new Button("New Method");
        addMethod.setOnAction(e -> {
            UMLClassMethod newbie = new UMLClassMethod();
            clazz.addMethod(newbie);
            methods.getItems().add(newbie);
            double height = 30.0 * clazz.getMethods().size();
            methods.setMinHeight(height);
            methods.setMaxHeight(height);
        });

        Button deleteMethod = new Button("Delete Selected Method");
        deleteMethod.setOnAction(e -> {
            UMLClassMethod method = methods.getSelectionModel().getSelectedItem();
            clazz.deleteMethod(method);
            methods.getItems().setAll(clazz.getMethods());
            double height = 30.0 * clazz.getMethods().size();
            methods.setMinHeight(height);
            methods.setMaxHeight(height);
        });

        methodEditMenu.getChildren().addAll(addMethod, deleteMethod);

        methodEditPane.getChildren().addAll(methodsTitle, methods, methodEditMenu);

        return methodEditPane;
    }

    private void setClassEditSpace(UMLClass clazz) {
        Pane classEditSpace = buildClassEditSpace(clazz);
        editSpacePane.getChildren().clear();
        editSpacePane.getChildren().addAll(classEditSpace);
    }

    private void setCellFactory(ListView<UMLClass> classList) {
        classList.setCellFactory(new Callback<ListView<UMLClass>,
                ListCell<UMLClass>>() {
            @Override
            public ListCell<UMLClass> call(ListView<UMLClass> list) {
                return new ListCell<UMLClass>() {
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

    private void setSetSelectionListener(ListView<UMLClass> classList) {
        classList.getSelectionModel().selectedIndexProperty().addListener((o,ov,nv) -> {
            UMLClass select = classList.getSelectionModel().getSelectedItem();
            if(select != null) {
                setClassEditSpace(select);
            }
        });
    }

    private ListView<UMLClass> constructClassList(UMLClassDiagram diagram) {
        ListView<UMLClass> classList = new ListView<>();
        classList.getItems().setAll(diagram.getAllClasses());
        setCellFactory(classList);
        setSetSelectionListener(classList);
        classList.setFixedCellSize(30);
        Shortcuts.setFixedHeight(classList, 30 * diagram.getAllClasses().size());
        return classList;
    }

    private ListView<UMLClassAttribute> constructsFieldsList(List<UMLClassAttribute> fields) {
        ListView<UMLClassAttribute> fieldList = new ListView<>();
        fieldList.getItems().setAll(fields);
        setFieldsCellFactory(fieldList);

        fieldList.setEditable(true);

        fieldList.setFixedCellSize(30);

        double height = 30.0 * fields.size();
        fieldList.setMinHeight(height);
        fieldList.setMaxHeight(height);
        return fieldList;
    }

    private ListView<UMLElementModifier> constructModifierList() {
        ListView<UMLElementModifier> modifiers = new ListView<>();
        modifiers.getItems().setAll(UMLElementModifier.values());
        modifiers.setCellFactory(new Callback<ListView<UMLElementModifier>, ListCell<UMLElementModifier>>() {
            @Override
            public ListCell<UMLElementModifier> call(ListView<UMLElementModifier> umlElementModifierListView) {
                return new ListCell<UMLElementModifier>() {
                    @Override
                    public void updateItem(UMLElementModifier modifier, boolean empty) {
                        super.updateItem(modifier, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else if (modifier != null) {
                            setText(null);
                            setGraphic(new Label(modifier.getModifierSymbol()));
                        } else {
                            setText("null");
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        modifiers.setFixedCellSize(30);
        modifiers.setMinHeight(30);
        modifiers.setMaxHeight(30);
        modifiers.setMaxWidth(60);
        return modifiers;
    }

    private Pane constructFieldCell(UMLClassAttribute field) {
        HBox cell = new HBox();
        // Modifier
        ListView<UMLElementModifier> modifiers = constructModifierList();
        modifiers.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                UMLElementModifier select = modifiers.getSelectionModel().getSelectedItem();
                field.setModifier(select);
            }
        });
        modifiers.getSelectionModel().select(field.getModifier());

        // Field Name
        TextField name = new TextField(field.getName());
        name.textProperty().addListener((o, ov, nv) -> {
            field.setName(name.getText());
        });

        Label semiCol = new Label(" : ");

        // Field Type
        TextField type = new TextField(field.getType());
        type.textProperty().addListener((o, ov, nv) -> {
            field.setType(type.getText());
        });
        cell.getChildren().addAll(modifiers, name, semiCol, type);

        return cell;
    }

    private void setFieldsCellFactory(ListView<UMLClassAttribute> fieldList) {
        fieldList.setCellFactory(new Callback<ListView<UMLClassAttribute>, ListCell<UMLClassAttribute>>() {
            @Override
            public ListCell<UMLClassAttribute> call(ListView<UMLClassAttribute> umlClassAttributeListView) {
                return new ListCell<UMLClassAttribute>() {
                    @Override
                    public void updateItem(UMLClassAttribute field, boolean empty) {
                        super.updateItem(field, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else if (field != null) {
                            setText(null);
                            Pane cell = constructFieldCell(field);
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

    private ListView<UMLClassMethod> constructsMethodsList(List<UMLClassMethod> methods) {
        ListView<UMLClassMethod> methodList = new ListView<>();
        methodList.getItems().setAll(methods);
        setMethodsCellFactory(methodList);

        methodList.setEditable(true);

        methodList.setFixedCellSize(30);

        double height = 30.0 * methods.size();
        methodList.setMinHeight(height);
        methodList.setMaxHeight(height);
        return methodList;
    }

    private Pane constructMethodCell(UMLClassMethod method) {
        HBox cell = new HBox();
        // Modifier
        ListView<UMLElementModifier> modifiers = constructModifierList();
        modifiers.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                UMLElementModifier select = modifiers.getSelectionModel().getSelectedItem();
                method.setModifier(select);
            }
        });
        modifiers.getSelectionModel().select(method.getModifier());

        // Field Type
        TextField type = new TextField(method.getType());
        type.textProperty().addListener((o, ov, nv) -> {
            method.setType(type.getText());
        });

        // Field Name
        TextField name = new TextField(method.getName());
        name.textProperty().addListener((o, ov, nv) -> {
            method.setName(name.getText());
        });

        Label lbr = new Label("(");

        // Arguments
        Pane args = constructArgs(method);

        Label rbr = new Label(")");

        cell.getChildren().addAll(modifiers, type, name, lbr, args, rbr);
        return cell;
    }

    private Pane constructArgs(UMLClassMethod method) {
        HBox argsBox = new HBox();

        HBox args = new HBox();

        List<String> argsVals = method.getArgumentTypes();

        for (int i = 0; i < argsVals.size(); i++) {
            TextField argument = new TextField(argsVals.get(i));
            argument.setMaxWidth(70);
            args.getChildren().add(argument);
            argument.textProperty().addListener((o, ov, nv) -> {
                int index = args.getChildren().indexOf(argument);
                method.getArgumentTypes().set(index, argument.getText());
            });
        }

        Button addButton = new Button("+");
        addButton.setOnAction(e -> {
            TextField argument = new TextField("");
            argument.setMaxWidth(70);
            args.getChildren().add(argument);
            method.getArgumentTypes().add("");
            argument.textProperty().addListener((o, ov, nv) -> {
                int index = args.getChildren().indexOf(argument);
                method.getArgumentTypes().set(index, argument.getText());
            });
        });
        Button removeButton = new Button("-");
        removeButton.setOnAction(e -> {
            args.getChildren().remove(args.getChildren().size() - 1);
            method.getArgumentTypes().remove(method.getArgumentTypes().size() - 1);
            System.out.println(method.getArgumentTypes().size());
        });

        argsBox.getChildren().addAll(args, addButton, removeButton);
        return argsBox;
    }

    private void setMethodsCellFactory(ListView<UMLClassMethod> fieldList) {
        fieldList.setCellFactory(new Callback<ListView<UMLClassMethod>, ListCell<UMLClassMethod>>() {
            @Override
            public ListCell<UMLClassMethod> call(ListView<UMLClassMethod> umlClassAttributeListView) {
                return new ListCell<UMLClassMethod>() {
                    @Override
                    public void updateItem(UMLClassMethod method, boolean empty) {
                        super.updateItem(method, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else if (method != null) {
                            setText(null);
                            Pane cell = constructMethodCell(method);
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
